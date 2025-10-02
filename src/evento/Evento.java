package evento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import evento.venue.Localidad;
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
	
	private List<Localidad> localidades = new ArrayList<>();
	
	private List<Transaccion> comprasRealizadas = new ArrayList<>();
	private List<TiqueteSimple> tiquetesMax = new ArrayList<>();
	
	
	public Evento(String tipo, LocalDate fecha, int hora, String idEvento, Venue venueAsignado, Organizador organizadorAsignado) {
		
		this.tipo = tipo;
		this.fecha = fecha;
		this.hora = hora;
		this.idEvento = idEvento;
		this.venueAsignado = venueAsignado;
		this.organizadorAsignado = organizadorAsignado;


	}
	

}
