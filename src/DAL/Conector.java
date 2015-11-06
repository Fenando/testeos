/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sistemas
 */
public class Conector {
    Connection conn;

    public Conector() {
        this.conect();
    }
    
    public void conect(){
    String bd = "cine";
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/"+bd,"root","");
            System.out.println("work");
                    } catch (SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("doesnt work");
            }
    }
    public Statement getStatement(){
         
        try {
            Statement s = conn.createStatement();
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    
}
