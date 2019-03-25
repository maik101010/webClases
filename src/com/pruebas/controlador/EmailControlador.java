package com.pruebas.controlador;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.pruebas.mail.Mail;

/**
 * Servlet implementation class EmailControlador
 */
@WebServlet("/EmailControlador")
public class EmailControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/conexion")
	private DataSource poolConexion;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailControlador() {
    	
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");		
		String sql = "SELECT nick FROM usuario WHERE nick = ?";
		try {
			PreparedStatement ps = poolConexion.getConnection().prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			String emailBd ="";
			while(rs.next()) {
				emailBd= rs.getString("nick");
			}
			
			if(emailBd.equals(email)) {
				Mail javaEmail = new Mail();
				System.out.println("El email esta en la bd y se enviará el código");

				try {
					javaEmail.setMailServerProperties();
					int codigoValidado = javaEmail.createEmailMessage(email);
					javaEmail.sendEmail();
					HttpSession sesion = request.getSession();
					sesion.setAttribute("codigoValidacionSesion", codigoValidado);
					sesion.setMaxInactiveInterval(60*5); // 5 minutes
					response.sendRedirect("validacion_codigo.jsp");	
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				//
			}else {
				System.out.println("No es valido es email");
				response.sendRedirect("recoverypassword.jsp");	
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		
	

		
	}
	

}
