/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sijie
 */
public class Customer  {
    private String cName;
    private int cID;
    private int cPhone;
    private String cRole;

    public String getcRole() {
        return cRole;
    }

    public void setcRole(String cRole) {
        this.cRole = cRole;
    }
    
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }


    public int getcPhone() {
        return cPhone;
    }

    public void setcPhone(int cPhone) {
        this.cPhone = cPhone;
    }

    public Customer(int cID, String cName, int cPhone){
        this.cID = cID;
        this.cName = cName;
        this.cPhone = cPhone;
    }
    
    public Customer(int cID, String cName, int cPhone, String cRole){
        this.cID = cID;
        this.cName = cName;
        this.cPhone = cPhone;
        this.cRole = cRole;
    }
    
    
    
    public Customer(){
    
    }
    
   // abstract public String getRole(); 
    
    
    
}
