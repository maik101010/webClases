package com.pruebas.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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

import com.pruebas.modelo.Ciudad;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/conexion")
	private DataSource poolConexion;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Ciudad ciudad = null;
		Connection conexion = null;
		ResultSet rs = null;
		Statement st = null;
		List<Ciudad> lista = new ArrayList<>();
		
		PrintWriter mensaje = response.getWriter();

		try {
			conexion = poolConexion.getConnection();
			st = conexion.createStatement();
			rs = st.executeQuery("SELECT * FROM ciudad");
			while (rs.next()) {
				ciudad = new Ciudad();
				ciudad.setId(rs.getInt("id"));
				ciudad.setNombre(rs.getString("nombre"));
				lista.add(ciudad);

			}
			
			request.setAttribute("listaCiudad", lista);
			request.getRequestDispatcher("form_usuario.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
