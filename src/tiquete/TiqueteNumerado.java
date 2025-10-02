package tiquete;

import java.time.LocalDateTime;

import evento.Evento;
import evento.venue.Localidad;

public class TiqueteNumerado extends TiqueteSimple {
	
	private String idAsiento;
	
	
	public TiqueteNumerado(String idTiquete, double precio, Evento evento, Localidad localidad, LocalDateTime fecha, String idAsiento) {
		super(idTiquete, precio, evento, localidad, fecha);
		this.idAsiento = idAsiento;
		
	}
	
	
		
}
