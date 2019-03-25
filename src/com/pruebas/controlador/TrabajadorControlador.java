package com.pruebas.controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.pruebas.modelo.Trabajador;



/**
 * Servlet implementation class TrabajadorControlador
 */
@WebServlet("/TrabajadorControlador")
public class TrabajadorControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/conexion")
	private DataSource poolConexion;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrabajadorControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps=null;
		Trabajador trabajador;
		
		List<Trabajador> listaTrabajador= new ArrayList<Trabajador>(); 
		
		try {
			con = poolConexion.getConnection();
			ps = con.prepareStatement("SELECT * FROM trabajador");
			rs =ps.executeQuery();
			
			while (rs.next()) {
				trabajador = new Trabajador();
				trabajador.setId(rs.getInt("id"));
				trabajador.setNombre(rs.getString("nombre"));
				trabajador.setSueldo(rs.getDouble("sueldo"));
				listaTrabajador.add(trabajador);
				//System.out.println(trabajador.toString());			
				
			}
			
			request.setAttribute("lista",listaTrabajador);
			request.getRequestDispatcher("trabajador.jsp").forward(request, response);
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
