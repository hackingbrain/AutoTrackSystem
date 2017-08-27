/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sijie
 */
public class Buyer extends Customer {
    private String role;
    
    public Buyer(int cID, String cName, int cPhone){
        super(cID, cName, cPhone);
        setRole("B");
    }
    public String getRole(){
        return role;
    }
    public void setRole(String r){
        role = r;
    }
}
