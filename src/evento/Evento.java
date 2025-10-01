package evento;

import java.time.LocalDate;
import java.util.List;

import evento.venue.Venue;
import tiquete.TiqueteSimple;
import usuario.comprador.Organizador;
import usuario.comprador.Transaccion;

public class Evento {
	
	private String tipo;
	private LocalDate fecha;
	private int hora;
	private String idEvento;
	
	private Venue venueAsignado;
	private Organizador organizadorAsignado;
	private List<Transaccion> comprasRealizadas;
	private List<TiqueteSimple> tiquetesMax;
	

}
