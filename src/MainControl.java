
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sijie
 */
public class MainControl {

    ResultSet rs = null;
    PreparedStatement pst = null;
    Connection con = (new databaseConnection()).getConnected();
    double total = 0.0;
    int orderID = 0;

    public int createOrder(String dayField, String monthField, String yearField) {
        String d = dayField;
        String m = monthField;
        String y = yearField;

        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR, Integer.parseInt(y));
        cal.set(Calendar.MONTH, Integer.parseInt(m) - 1);
        cal.set(Calendar.DATE, Integer.parseInt(d));
        // normalize the object
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        java.sql.Date javaSqlDate = new java.sql.Date(cal.getTime().getTime());

        try {
            pst = con.prepareStatement("SELECT orderID FROM orders WHERE orderID =(SELECT max(orderID) FROM orders)");
            rs = pst.executeQuery();
            if (rs.next()) {
                orderID = rs.getInt(1) + 1;
            } else if (rs.next() != true) {
                orderID = 1;
            }

        } catch (SQLException ex) {
            Logger.getLogger(MenuJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        String query = " insert into orders (orderID, orderDate)"
                + " values (?,?)";
        try {
            pst = con.prepareStatement(query);
            pst.setInt(1, orderID);
            pst.setDate(2, javaSqlDate);
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MenuJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderID;
    }

    public ResultSet goSearch(String searchbyField) {
        String searchInput = searchbyField;
        try {
            pst = con.prepareStatement("SELECT inventory.albumName, inventory.amountInStock FROM inventory WHERE inventory.albumName = ?");

            pst.setString(1, searchInput);
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MenuJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public void addInv(int upcNum, String alName) {

        int invNum = 0;
        int newAlbID = 0;
        try {
            //generate a new albumID
            pst = con.prepareStatement("SELECT albumID FROM albumdata WHERE albumID =(SELECT max(albumID) FROM albumdata)");
            rs = pst.executeQuery();

            if (rs.next()) {
                newAlbID = rs.getInt(1) + 1;

            }

            pst = con.prepareStatement("SELECT COUNT(*) FROM inventory WHERE UPC = ?");
            pst.setInt(1, upcNum);
            rs = pst.executeQuery();

            if (rs.next()) {
                if (rs.getInt(1) == 0) {
                    int stock = 1;
                    String inStr = "INSERT INTO `bongodatabase`.`inventory` (`UPC`, `albumName`, `amountInStock`) VALUES (";
                    inStr = inStr + "\'" + upcNum + "\', \'" + alName + "\', \'" + stock + ");";

                    pst = con.prepareStatement(inStr);
                    pst.executeUpdate();

                } else {
                    pst = con.prepareStatement("SELECT amountInStock FROM inventory WHERE UPC = ?");
                    pst.setInt(1, upcNum);
                    rs = pst.executeQuery();

                    if (rs.next()) {
                        pst = con.prepareStatement("UPDATE inventory set amountInStock = ? WHERE UPC = ? ");
                        pst.setInt(1, invNum + 1);
                        pst.setInt(2, upcNum);
                        pst.executeUpdate();
                    }

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(MenuJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void substractInv(int albumID) {
        int upcNum = 0;
        int invNum = 0;
        try {

            pst = con.prepareStatement("SELECT UPC FROM albumdata WHERE albumID = ?");
            pst.setInt(1, albumID);
            rs = pst.executeQuery();
            if (rs.next()) {
                upcNum = rs.getInt(1);
                pst = con.prepareStatement("SELECT * FROM inventory WHERE UPC = ?");
                pst.setInt(1, upcNum);
                rs = pst.executeQuery();
                if (rs.next()) {
                    invNum = rs.getInt(3);
                }

                pst = con.prepareStatement("UPDATE inventory set amountInStock = ? WHERE UPC = ? ");
                pst.setInt(1, invNum - 1);
                pst.setInt(2, upcNum);
                pst.executeUpdate();

            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String[] showReceipt(String albumIDField, int orderID) {
        String[] res = new String[2];
        double p = 0;
        String alID = albumIDField;
        int albID = Integer.parseInt(alID);
        int upcNum = 0;
        int invNum = 0;
        JTextArea receiptArea = null;
        String item = null;

        String query2 = "UPDATE albumdata SET orderID = ? WHERE albumID = ?";
        try {
            pst = con.prepareStatement(query2);
            pst.setInt(1, orderID);
            pst.setInt(2, albID);
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MenuJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            pst = con.prepareStatement("SELECT * FROM albumdata WHERE albumID = ?");
            pst.setString(1, alID);
            rs = pst.executeQuery();

            if (rs.next()) {
                p = rs.getDouble(3);
                item = rs.getString(2) + "          $" + rs.getDouble(3) + "\n";
                upcNum = rs.getInt(6);

                System.out.println(total);

             
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        res[0] = item;
        res[1] = Double.toString(p);

        return res;

    }

    public void addNewMember(String custNameField, String phoneNumField) {

        String newCustName = custNameField;
        String newCustPhone = phoneNumField;
        int newCustID = 0;
        String insertStr;

        Customer cus = new Customer(newCustID, newCustName, Integer.parseInt(newCustPhone));

        try {
            pst = con.prepareStatement("SELECT custID FROM customerdata WHERE custID =(SELECT max(custID) FROM customerdata)");
            rs = pst.executeQuery();

            if (rs.next()) {
                newCustID = rs.getInt(1) + 1;
                cus.setcID(newCustID);
            }

            insertStr = "INSERT INTO `bongodatabase`.`customerdata` (`custID`, `custName`, `phoneNum`, `sellerRole`, `buyerRole`) VALUES (";
            insertStr = insertStr + "\'" + cus.getcID() + "\', \'" + cus.getcName() + "\', \'" + cus.getcPhone() + "\', \'\', \'\');";

            pst = con.prepareStatement(insertStr);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(MenuJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
