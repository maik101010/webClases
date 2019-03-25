package com.pruebas.controlador;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.sql.DataSource;
/**
 *
 * @author michael
 */
public class Conexion {
    private static Connection cn = null;
    @Resource(name = "jdbc/conexion")
	private DataSource poolConexion;

  
    public static Connection getConnection(){
         try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nueva","root","");
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cn;
    }
    
    public Connection getConnectionDataSource() {
    try {
		cn = poolConexion.getConnection();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    return cn;
    }
    public static void desconectar(){
        cn=null;
    }   
}