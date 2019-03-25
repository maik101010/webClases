package com.pruebas.conexionSingleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
	private static Connection conexion;
	private static Conexion dataSource;

	static int contador = 0;
	public static Conexion getInstance() {
		if (dataSource == null) {
			dataSource = new Conexion();
			contador++;
			System.out.println("Se inicializa la conexion número " + contador);
		}
		return dataSource;
	}
	
	private Connection getConnection() {
		
		conexion = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/nueva", "root", "");
			} catch (SQLException ex) {
				Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
			}

		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
		}
		/*contador++;
		System.out.println("Se inicializa la conexion número " + contador);*/
		return conexion;
	}

	public static void main(String[] args) {
		try {
			//Conexion conexion = new Conexion();
			
			Connection con = Conexion.getInstance().getConnection();
			//Connection con = conexion.getConnection();
			PreparedStatement ps = con.prepareStatement("Select * from usuario");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String nick = rs.getString("nick");
				String password = rs.getString("password");

				System.out.println("El nick es " + nick + " y la contraseña es " + password);
				
			}
			System.out.println("------------------FINALIZA TRANSACCION-----------------");
			Connection con2 = Conexion.getInstance().getConnection();
			//Connection con2 = conexion.getConnection();
			PreparedStatement ps2 = con2.prepareStatement("Select * from ciudad");
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next()) {
				int id = rs2.getInt("id");
				String nombre = rs2.getString("nombre");
				System.out.println("El id de la ciudad es" + id + " y nombre de la ciudad es " + nombre);
				
			}
			System.out.println("------------------FINALIZA TRANSACCION-----------------");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
