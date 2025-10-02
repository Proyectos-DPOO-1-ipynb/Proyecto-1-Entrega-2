package tiquete;

import java.time.LocalDate;
import java.time.LocalDateTime;

import evento.Evento;
import evento.venue.Localidad;

public class TiqueteSimple extends Tiquete {
	
	
	private LocalDate fecha;
	private int hora;
	
	private Evento eventoAsociado;
	private Localidad localidadTiquete;
	
	public TiqueteSimple(String idTiquete, double precio, LocalDate fecha, int hora, Evento eventoAsociado,
			Localidad localidadTiquete) {
		
		super(idTiquete, precio);
		this.fecha = fecha;
		this.hora = hora;
		this.eventoAsociado = eventoAsociado;
		this.localidadTiquete = localidadTiquete;
	}
	
	
}
