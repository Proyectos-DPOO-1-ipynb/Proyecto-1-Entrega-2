package evento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import evento.venue.Localidad;
import evento.venue.LocalidadBasica;
import evento.venue.LocalidadNumerada;
import evento.venue.Venue;
import tiquete.Tiquete;
import tiquete.TiqueteNumerado;
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
	
	private String Estado;
	
	private List<Localidad> localidades = new ArrayList<>();
	
	private List<Transaccion> comprasRealizadas = new ArrayList<>();
	private List<Tiquete> tiquetesMax = new ArrayList<>();
	
	
	private static List<Evento> eventosBorrador = new ArrayList<>();
	private static List<Evento> eventosPublicados = new ArrayList<>();
	
	
	public Evento(String tipo, LocalDate fecha, int hora, String idEvento, Venue venueAsignado, Organizador organizadorAsignado) {
		
		this.tipo = tipo;
		this.fecha = fecha;
		this.hora = hora;
		this.idEvento = idEvento;
		this.venueAsignado = venueAsignado;
		this.organizadorAsignado = organizadorAsignado;
		this.Estado = "BORRADOR";


	}


	public LocalDate getFecha() {
		return fecha;
	}


	public String getIdEvento() {
		return idEvento;
	}
	
	
	


	public String getEstado() {
		return Estado;
	}


	public int getHora() {
		return hora;
	}


	public List<Localidad> getLocalidades() {
		return localidades;
	}


	public Venue getVenueAsignado() {
		return venueAsignado;
	}


	public static List<Evento> getEventosBorrador() {
		return eventosBorrador;
	}
	
	
	public void addEventoBorrador() throws Exception {
		
		if(eventosBorrador.contains(this)) {
			
			throw new Exception("Ya este evento está en borrador");
			
		}
		eventosBorrador.add(this);
		
	}
	
	
	
	public void agregarLocalidad(Localidad localidad) throws Exception {
		
		
		String idlocal = localidad.getIdLocalidad();
		
		for(Localidad localidads:this.localidades) {
			
			if(idlocal.equals(localidads.getIdLocalidad())) {
			
			throw new Exception("Ese id ya está registrado. Pruebe otro");
			
		}
		}
		
		this.localidades.add(localidad);	
	}
	
	
	public void removerLocalidad(Localidad localidad) {
		
		this.localidades.remove(localidad);
		
	}
	
	
	public void cambiarEstado() throws Exception {
		
		
		if (eventosPublicados.contains(this)) {
			
			throw new Exception("Este evento ya fue publicado");
			
		} 
		
		
		
		
		
		this.getVenueAsignado().addEventotoVenue(this);
		
		this.Estado = "PUBLICADO";
		eventosBorrador.remove(this);
		eventosPublicados.add(this);
	}
	
	
	public List<Map<String, Object>> resumenEvento(){
		
		
		Map<String, Object> localidadesBasicas = new HashMap<>();
		Map<String, Object> localidadesNumeradas = new HashMap<>();
		
		for(Localidad localidad:localidades) {
			
			if (localidad.getTipo().equals("BASICA")) {
				
				LocalidadBasica localidadB = (LocalidadBasica) localidad;
				
				int cuposDisponibles = localidadB.getCuposDisponibles();
				int cuposTotales = localidadB.getCuposTotales();
				
				int [] cupos = {cuposDisponibles, cuposTotales};
				
				localidadesBasicas.put(localidadB.getIdLocalidad(), cupos);
				
			} else  {
				
				LocalidadNumerada localidadN = (LocalidadNumerada) localidad;
				
				int totalAsientos = localidadN.getAsientosTotales().size();
				int ocupadas = localidadN.getAsientosOcupados().size();
				int disponibles = localidadN.disponibles();
				
				int [] info = {totalAsientos, ocupadas, disponibles};
				
				localidadesNumeradas.put(localidadN.getIdLocalidad(), info);	
			}	
			
		}
		
		
		List<Map<String, Object>> resumen = new ArrayList<>();
		
		resumen.add(localidadesNumeradas);
		resumen.add(localidadesBasicas);
		
		return resumen;
		
		
		
		
	}
	
	public void addTiqueteSimple(TiqueteSimple tiquete) {
		
		this.tiquetesMax.add(tiquete);
		
		
	}
	
	
	public void addTiqueteNumerado(TiqueteNumerado tiquete) {
		
		this.tiquetesMax.add(tiquete);
	}
	
	
	public String getTipo() { return tipo; }
	public Organizador getOrganizadorAsignado() { return organizadorAsignado; }

}
