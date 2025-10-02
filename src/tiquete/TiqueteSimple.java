package tiquete;

import java.time.LocalDate;
import java.time.LocalDateTime;

import evento.Evento;
import evento.venue.Localidad;

public class TiqueteSimple extends Tiquete {
	
	
	private Evento evento;
	private Localidad localidad;
	
	private LocalDateTime fecha;
	
	
	public TiqueteSimple(String idTiquete, double precio, Evento evento, Localidad localidad, LocalDateTime fecha) {
		
		super(idTiquete, precio);
		
		this.evento = evento;
		this.localidad = localidad;
		
		this.fecha = fecha;
		
	}
	

}
