package com.pruebas.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.pruebas.modelo.Usuario;

/**
 * Servlet implementation class UsuarioControlador
 */
@WebServlet("/UsuarioControlador")
public class UsuarioControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/conexion")
	private DataSource poolConexion;

	/**
	 * Traemos el modelo
	 */

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioControlador() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * Texto plano la respuesta
		 */
		response.setContentType("text/plain");
		PrintWriter mensajePantalla = response.getWriter();
		/*
		 * Retorna un objeto PrintWriter que puede enviar caracteres de texto al cliente
		 */
		Usuario usuario = null;
		Connection conexion = null;
		ResultSet rs = null;
		Statement st = null;
		List<Usuario> listaUsuario = new ArrayList<>();

		try {
			conexion = poolConexion.getConnection();
			st = conexion.createStatement();
			rs = st.executeQuery("SELECT * FROM usuario");
			while (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id_usuario"));
				usuario.setNick(rs.getString("nick"));
				usuario.setPassword(rs.getString("password"));
				listaUsuario.add(usuario);

			}
			request.setAttribute("miLista", listaUsuario);
			request.getRequestDispatcher("usuario.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * PrintWriter objeto = response.getWriter(); objeto.println("Hola mundo");
		 */

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter mensaje = resp.getWriter();
		PreparedStatement ps = null;
		
		//int ciudadId = Integer.parseInt(req.getParameter("ciudad"));
		
		//mensaje.println(ciudadId + " Seleccionado");
		Connection con = null;
		
		try {
			Usuario usuario = new Usuario();
			usuario.setNick(req.getParameter("nick"));
			usuario.setPassword(req.getParameter("password"));
			String fecha = req.getParameter("fecha");
			usuario.setFecha(Date.valueOf(fecha));
			
			int ciudadId = Integer.parseInt(req.getParameter("ciudad"));
			
			con = poolConexion.getConnection();
			ps = con.prepareStatement("INSERT INTO usuario (nick, password, ciudad_id, fecha) VALUES (?,?,?,?)");
			ps.setString(1, usuario.getNick());
			ps.setString(2, usuario.getPassword());
			ps.setInt(3, ciudadId);
			ps.setDate(4, usuario.getFecha());
			
			int resultado = ps.executeUpdate();
			if (resultado == 1) {
				mensaje.println("Guardado el registro");
			} else {
				mensaje.println("No guardado el registro");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
