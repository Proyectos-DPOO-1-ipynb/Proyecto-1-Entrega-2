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
	
	private List<Evento> eventosAsociados;
	
	
	private static List<Venue> VenuesAprobados = new ArrayList<>(); 
	private static List<Venue> VenuesPendientes = new ArrayList<>();
	
	
	public Venue(String VenueId, String direccion, int maxCapacidad, List<String> restricciones, boolean estado){
		
		this.VenueId = VenueId;
		this.direccion = direccion;
		this.maxCapacidad = maxCapacidad;
		this.restricciones = restricciones;
		this.estado = estado;
		
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





	public void cambiarEstadoAprobado() {
		
		this.estado = true;
		VenuesAprobados.add(this);
	}
	
	
	
	public void addPropuestaVenue() {
		
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
	
	
	public void addEventotoVenue(Evento evento) {
		
		this.eventosAsociados.add(evento);
		
		
		
	}
	
	
	
}
