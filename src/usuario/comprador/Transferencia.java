package usuario.comprador;

import java.time.LocalDate;
import java.util.List;

import evento.Evento;
import tiquete.Tiquete;

public class Transferencia extends Transaccion{
	
	private Comprador emisor;
	private Comprador receptor;
	
	public Transferencia(LocalDate fecha, String tipoPago, double montoTotal, Comprador usuario, List<Evento> eventos,
			List<Tiquete> tiquetesComprados) {
		super(fecha, tipoPago, montoTotal, usuario, eventos, tiquetesComprados);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
