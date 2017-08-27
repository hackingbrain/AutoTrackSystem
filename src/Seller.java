/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sijie
 */
public class Seller extends Customer {
    private String role;
    
    public Seller(int cID, String cName, int cPhone){
        super(cID, cName, cPhone);
        setRole("S");
    }
    public String getRole(){
        return role;
    }
    public void setRole(String r){
        role = r;
    }
    
}
