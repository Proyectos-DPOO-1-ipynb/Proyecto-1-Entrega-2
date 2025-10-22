package tiquete;

import java.time.LocalDate;

import evento.Evento;
import evento.venue.Localidad;

public class TiqueteNumerado extends TiqueteSimple {
	
	private String idAsiento;
	
	public TiqueteNumerado(String idTiquete, double precio, LocalDate fecha, int hora, Evento eventoAsociado,
			Localidad localidadTiquete, String idAsiento) {
		
		super(idTiquete, precio, fecha, hora, eventoAsociado, localidadTiquete);
		this.idAsiento = idAsiento;
	}

	public String getIdAsiento() {
		return idAsiento;
	}
	
		
}
