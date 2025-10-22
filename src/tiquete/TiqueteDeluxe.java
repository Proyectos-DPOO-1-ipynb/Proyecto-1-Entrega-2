package tiquete;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import evento.Evento;
import evento.venue.Localidad;

public class TiqueteDeluxe extends TiqueteSimple{

	private List<Object> beneficios;
	private List<Object> mercancia;
	
	
	public TiqueteDeluxe(String idTiquete, double precio, LocalDate fecha, int hora, Evento eventoAsociado,
			Localidad localidadTiquete, List<Object> beneficios, List<Object> mercancia) {
		super(idTiquete, precio, fecha, hora, eventoAsociado, localidadTiquete);
		this.beneficios = beneficios;
		this.mercancia = mercancia;
	}

	public boolean esTransferible() {
	    return false; // Bloqueo de transferencia para paquetes Deluxe
	}
	
}
