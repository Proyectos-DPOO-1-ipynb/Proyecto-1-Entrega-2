package usuario.comprador;

import java.time.LocalDate;
import java.util.List;

import evento.Evento;
import tiquete.Tiquete;

public class Reembolso extends Transaccion {
	
	private String estado;
	private double cantidad;
	
	public Reembolso(LocalDate fecha, String tipoPago, double montoTotal, Comprador usuario, List<Evento> eventos,
			List<Tiquete> tiquetesComprados) {
		super(fecha, tipoPago, montoTotal, usuario, eventos, tiquetesComprados);
		// TODO Auto-generated constructor stub
	}
	
}

