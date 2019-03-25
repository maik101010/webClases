package com.pruebas.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.pruebas.modelo.Usuario;

/**
 * Servlet implementation class LoginControlador
 */
@WebServlet("/LoginControlador")
public class LoginControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/conexion")
	private DataSource poolConexion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginControlador() {
    	
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		usuario.setNick(request.getParameter("nick"));
		usuario.setPassword(request.getParameter("password"));
		PrintWriter mensaje = response.getWriter();
		
		try {
			Connection conexion = poolConexion.getConnection();
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM usuario WHERE nick = ? AND password = ?");
			ps.setString(1, usuario.getNick());
			ps.setString(2, usuario.getPassword());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {

				HttpSession sesion = request.getSession();
				sesion.setAttribute("usuarioSesion", usuario);
				sesion.setMaxInactiveInterval(60*5); // 5 minutes
				response.sendRedirect("principal.jsp");				
				
			}else {
				//mensaje.println("usuario o contraseña incorrectos");
				mensaje.print("<html>");
				mensaje.print("<script>alert('Usuario o contraseña incorrectos');"
						+ "window.location.href = 'http://localhost:8081/test/login.jsp';</script>");
				mensaje.print("</html>");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*if (usuario.getNick().equals("test@gmail.com") && usuario.getPassword().equals("12345")) {
			//mensaje.print("Usted se ha logueado");
			request.setAttribute("variable", usuario);
			request.getRequestDispatcher("principal.jsp").forward(request, response);
		}else {
			mensaje.print("Usuario y/o contraseña incorrectos");
		}*/
	}
	

}
