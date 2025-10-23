package usuario.comprador;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import evento.Evento;
import finanzas.GestorFinanciero;
import tiquete.Tiquete;
import java.util.UUID;

public class Transaccion {

	private LocalDate fecha;	
	private String tipoPago;
	private double montoTotal;
	
	private Comprador usuario;
    private List<Evento> eventos;        
	private List<Tiquete> tiquetesComprados;
	
	protected String idTransaccion;
	protected LocalDateTime fechaHora;
	
	public Transaccion(LocalDate fecha, String tipoPago, double montoTotal, Comprador usuario, List<Evento> eventos, List<Tiquete> tiquetesComprados) {
		this.fecha = fecha;
		this.tipoPago = tipoPago;
		this.montoTotal = montoTotal;
		this.usuario = usuario;
		this.eventos = eventos;
		this.tiquetesComprados = tiquetesComprados;
		GestorFinanciero.registrarTransaccion(this);
	}
	
	protected static String generarIdTx() { 
		return UUID.randomUUID().toString();
	}
	
	public LocalDate getFecha() {
		return fecha;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public double getMontoTotal() {
		return montoTotal;
	}

	public Comprador getUsuario() {
		return usuario;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public List<Tiquete> getTiquetesComprados() {
		return tiquetesComprados;
	}
}
