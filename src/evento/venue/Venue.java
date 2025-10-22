package evento.venue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import evento.Evento;

public class Venue {

	
	private String VenueId;
	private String direccion;
	private int maxCapacidad;
	private List<String> restricciones;
	private boolean estado; //false, si no ha sido aprobado
	
	private List<Evento> eventosAsociados = new ArrayList<>();
	
	private static List<Venue> VenuesAprobados = new ArrayList<>(); 
	private static List<Venue> VenuesPendientes = new ArrayList<>();
	
	//Lista con id de venues?
	
	public Venue(String VenueId, String direccion, int maxCapacidad, List<String> restricciones) throws Exception{
		
		if (maxCapacidad <= 0) {
			throw new Exception("La capacidad no puede ser negativa ni nula");
		}
		this.VenueId = VenueId;
		this.direccion = direccion;
		this.maxCapacidad = maxCapacidad;
		this.restricciones = restricciones;
		this.estado = false;
	}

	public String getVenueId() {
		return VenueId;
	}

	public String getDireccion() {
		return direccion;
	}


	public int getMaxCapacidad() {
		return maxCapacidad;
	}


	public List<String> getRestricciones() {
		return restricciones;
	}


	public boolean getEstado() {
		return estado;
	}
	
	
	public List<Evento> getEventosAsociados() {
		return eventosAsociados;
	}
	
	
	public static List<Venue> getVenuesAprobados() {
		return VenuesAprobados;
	}


	public static List<Venue> getVenuesPendientes() {
		return VenuesPendientes;
	}


	public void cambiarEstadoAprobado() throws Exception {
		
		if(this.estado == true) {
			throw new Exception("YUa se encuentra aprobado");
		}
		
		this.estado = true;
		VenuesAprobados.add(this);
		VenuesPendientes.remove(this);
	}
	
	
	public void addPropuestaVenue() throws Exception {
		
		if(this.estado == true) {
			throw new Exception("Ya fue aprobado! No hay necesidad de proponerlo otra vez");
		}
		
		if(VenuesPendientes.contains(this)) {
			throw new Exception("Ya está siendo considerado este venue para su aprobación");
			
		}
		
		VenuesPendientes.add(this);
	}
	
	
	public boolean buscarDisponibilidad(LocalDate fecha) {
		
		boolean verificador = true;
		
		for(Evento evento : this.eventosAsociados) {	
			if(evento.getFecha().isEqual(fecha)) {
				verificador = false;
			} 	
		}
		
		return verificador;

	}
	
	
	public void addEventotoVenue(Evento evento) throws Exception {
		
		if(!evento.getVenueAsignado().getVenueId().equals(this.getVenueId())) {
			throw new Exception("Estás tratando de formalizar un venue que no corresponde al borrador de este evento");			
		}
		
		if(eventosAsociados.contains(evento)) {
			throw new Exception("El evento ya está registrado en este venue");
		}
		
		this.eventosAsociados.add(evento);
		
	}
	
}
