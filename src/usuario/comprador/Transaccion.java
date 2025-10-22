package usuario.comprador;

import java.time.LocalDate;
import java.util.List;

import evento.Evento;
import finanzas.GestorFinanciero;
import tiquete.Tiquete;

public class Transaccion {

	private LocalDate fecha;	
	private String tipoPago;
	private double montoTotal;
	
	private Comprador usuario;
    private Evento evento;        
	private List<Tiquete> tiquetesComprados;
	
	public Transaccion(LocalDate fecha, String tipoPago, double montoTotal,
            Comprador usuario, Evento evento, List<Tiquete> tiquetesComprados) {
		this.fecha = fecha;
		this.tipoPago = tipoPago;
		this.montoTotal = montoTotal;
		this.usuario = usuario;
		this.evento = evento;
		this.tiquetesComprados = tiquetesComprados;
		GestorFinanciero.registrarTransaccion(this);
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

	public Evento getEvento() {
		return evento;
	}

	public List<Tiquete> getTiquetesComprados() {
		return tiquetesComprados;
	}
}
