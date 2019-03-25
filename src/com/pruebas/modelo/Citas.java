package com.pruebas.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Citas {
	int id;
	private LocalDate fechaCreacion;
	private LocalDateTime horaInicio;
	private LocalDateTime horaFin;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public java.time.LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(java.time.LocalDate feaCreacion) {
		this.fechaCreacion = feaCreacion;
	}
	public java.time.LocalDateTime getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(java.time.LocalDateTime horaInicio) {
		this.horaInicio = horaInicio;
	}
	public java.time.LocalDateTime getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(java.time.LocalDateTime horaFin) {
		this.horaFin = horaFin;
	}
	@Override
	public String toString() {
		return "Citas [id=" + id + ", feaCreacion=" + fechaCreacion + ", horaInicio=" + horaInicio + ", horaFin="
				+ horaFin + "]";
	}
	
}
