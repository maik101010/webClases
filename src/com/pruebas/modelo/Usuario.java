package com.pruebas.modelo;

import java.sql.Date;

public class Usuario {
	
	private int id;
	private String nick;
	private String password;
	private Date fecha;
	public Usuario() {
		
	}
	
	
	public Usuario(int id, String nick, String password) {
		this.id = id;
		this.nick = nick;
		this.password = password;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nick=" + nick + ", password=" + password + "]";
	}
	
	
	
	
	

}
