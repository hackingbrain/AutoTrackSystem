
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sijie
 */
public class databaseConnection {
    // Info. to store url, username, and password
    private static final String DBLINK = "jdbc:mysql://localhost:3306/bongodatabase";
    private static final String USER = "root";
    private static final String PASSWORD = "1123456";
    
    /**
     * Connect to db 
     * @return 
     */
    public Connection getConnected() {
        Connection conn = null;
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bongodatabase","root","123456");
        }catch(SQLException e){
            e.printStackTrace();
        } 
        
        
        
        return conn;
    }
    
    


    
}
