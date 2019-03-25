package com.pruebas.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.pruebas.modelo.Citas;
import com.pruebas.modelo.Usuario;


public class PruebaHoraInicio {
	Connection conexion = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	Statement st = null;

	protected void seleccionar() {
		// TODO Auto-generated method stub

		List<Citas> lista = new ArrayList<>();
		Citas citas = null;

		try {
			conexion = Conexion.getConnection();
			st = conexion.createStatement();
			rs = st.executeQuery("SELECT * FROM citas_inicio");
			while (rs.next()) {
				citas = new Citas();
				citas.setId(rs.getInt("id"));
				citas.setFechaCreacion(rs.getDate("fecha").toLocalDate());
				citas.setHoraInicio(rs.getTimestamp("hora_inicio").toLocalDateTime());
				citas.setHoraFin(rs.getTimestamp("hora_fin").toLocalDateTime());
				lista.add(citas);

			}

			for (Citas citas2 : lista) {
				System.out.println(citas2);
				// mensaje.println(citas2);
			}

			/*
			 * request.setAttribute("listaCiudad", lista);
			 * request.getRequestDispatcher("form_usuario.jsp").forward(request, response);
			 */
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void insertar() {

		// int ciudadId = Integer.parseInt(req.getParameter("ciudad"));

		// mensaje.println(ciudadId + " Seleccionado");
		Connection con = null;

		try {
			LocalDate fecha = LocalDate.of(2019, 03, 11);
			LocalDateTime horaInicio = LocalDateTime.of(2019, 03, 10, 13, 30);
			Citas cita = new Citas();
			cita.setFechaCreacion(fecha);
			cita.setHoraInicio(horaInicio);
			con = Conexion.getConnection();

			int respuestaFecha = validarFecha(fecha);
			/**
			 * Si tenemos uno o más regisros con la fecha indicada, entonces entramos a
			 * validar las horas
			 */
			if (respuestaFecha >= 1) {
				// int respuesta = validarHoraIgual();
				// validarRegistrosHoras();

			} else {
				ps = con.prepareStatement("INSERT INTO citas_inicio (fecha, hora_inicio) VALUES (?,?)");

				ps.setDate(1, java.sql.Date.valueOf(cita.getFechaCreacion()));
				ps.setTimestamp(2, java.sql.Timestamp.valueOf(cita.getHoraInicio()));

				int resultado = ps.executeUpdate();
				if (resultado == 1) {
					System.out.println("Insertado");
				} else {
					System.out.println("No Insertado");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private int validarHoraIgual(LocalDate fecha, LocalDateTime horaInicio) {
		int cont = 0;
		try {
			conexion = Conexion.getConnection();
			ps = conexion.prepareStatement("SELECT * FROM citas where fecha = ? order by hora_inicio");
			ps.setObject(1, fecha);
			rs = ps.executeQuery();

			int horaDadaInicio = horaInicio.getHour();
			int minutoDadoInicio = horaInicio.getMinute();

			while (rs.next()) {
				LocalDateTime horaInicioBd = rs.getTimestamp("hora_inicio").toLocalDateTime();

				if (horaInicioBd.getHour() == horaDadaInicio && horaInicioBd.getMinute() == minutoDadoInicio) {
					cont++;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cont;
	}

	private void validarRegistroHoras(LocalDate fecha, LocalDateTime horaInicio) {
		try {
			conexion = Conexion.getConnection();

			ps = conexion.prepareStatement(
					"SELECT * FROM citas_inicio where fecha = ? and hora_inicio < ? order by hora_inicio desc limit 1");
			ps.setObject(1, fecha);
			ps.setObject(2, horaInicio);
			rs = ps.executeQuery();

			int horaDadaInicio = horaInicio.getHour();
			int minutoDadoInicio = horaInicio.getMinute();

			while (rs.next()) {
				LocalDateTime horaInicioBd = rs.getTimestamp("hora_inicio").toLocalDateTime();

				int hourBd = horaInicioBd.getHour();
				int minuteBd = horaInicioBd.getMinute();
				/**
				 * Si la hora es mayor que la que tenemos en los registros
				 */
/*				if (horaDadaInicio > hourBd) {
					int diferenciHoras = 0;
					for (int i = hourBd; i < horaDadaInicio; i++) {
						diferenciHoras++;

					}
					System.out.println("La diferencia es de " + diferenciHoras + " horas" + " podemos registrar");

				}*/
				/**
				 * Si la hora es igual a la que tenemos en nuestros registros
				 */
				int diferenciaMinutos = 0;
				if (hourBd == horaDadaInicio || horaDadaInicio>hourBd) {
					
					/**
					 * Si el minuto dado no es igual a cero, entramos al ciclo para recorrer la
					 * diferencia de minutos
					 */
					if (minutoDadoInicio != 0) {
						for (int i = minuteBd; i < minutoDadoInicio; i++) {
							diferenciaMinutos++;
						}
						if (diferenciaMinutos < 30) {
							System.out.println("No se puede registrar, hay una diferencia de " + diferenciaMinutos + " minutos");
						}

					}else {
						minutoDadoInicio=60;
						for (int i = minuteBd; i < minutoDadoInicio; i++) {
							diferenciaMinutos++;
						}
						if (diferenciaMinutos>=30) {
							System.out.println("La diferencia es de  "+diferenciaMinutos + " y puede registrar");	
						}
						
					}

				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private int validarFecha(LocalDate fecha) {
		int cont = 0;
		try {
			conexion = Conexion.getConnection();
			ps = conexion.prepareStatement("SELECT * FROM citas WHERE fecha = ?");
			ps.setObject(1, fecha);
			rs = ps.executeQuery();

			while (rs.next()) {
				cont++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cont;
	}

	public static void main(String arg[]) {
		PruebaHoraInicio prueba = new PruebaHoraInicio();
		// prueba.insertar();
		/**
		 * --- Validamos si la fecha no tiene registros, entonces dejamos registrar
		 * normalmente
		 */

//				int respuesta = prueba.validarFecha(fecha);
//				System.out.println("cantidad de fechas "+ respuesta);

		/**
		 * ---Validamos que la hora no sea la misma a la de inicio
		 */

//		int respuesta= prueba.validarHoraIgual(fecha, horaInicio);
//		if(respuesta > 0)
//			System.out.println("La hora inicio es igual no se puede registrar");
//		else
//			System.out.println("Puede seguir");

		/**
		 * Parametros
		 */
		LocalDate fecha = LocalDate.of(2019, 03, 11);
		LocalDateTime horaInicio = LocalDateTime.of(2019, 04, 11, 8, 40, 00);

		prueba.validarRegistroHoras(fecha, horaInicio);

	}
}
