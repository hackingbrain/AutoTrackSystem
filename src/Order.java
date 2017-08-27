/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sijie
 */
public class Order {
    private int orderID;
    private String orderStatus;
    private int custID;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }
    
    public Order(int orderID, String orderStatus, int custID){
        this.orderID = orderID;
        this.orderStatus = orderStatus;
        this.custID = custID;
    }
    
    
    public Order(){
    
    
    }
}
