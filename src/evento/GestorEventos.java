package evento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import evento.venue.Venue;
import usuario.Administrador;
import usuario.comprador.Organizador;

public class GestorEventos {
	
	
	public static String proponerVenue(Organizador organizador,String VenueId,  String direccion, int maxCapacidad, List<String> restricciones) {
		
		Venue propuesta = new Venue(VenueId, direccion, maxCapacidad, restricciones, false);
		
		propuesta.addPropuestaVenue();
		
		return "El Venue" + propuesta.getVenueId() + "ha sido propuesto. Espere la confirmación del Administrador";
		
		
	}
	
	public static String aprobarVenue(Administrador administrador, String adminId, Venue venue) throws Exception {
		
		if (!adminId.equals(administrador.getAdminId())) {
			
			throw new Exception("No eres el administrador!");
			
		} else {
				
			venue.cambiarEstadoAprobado();
			
			return "El Venue ha sido aprobado y añadido al catálogo";
				
				
		}		
	}
	
	
	
	
	public static String crearBorradorEvento(Organizador organizador, String tipo, String idEvento, Venue venue, 
			LocalDate fecha, int hora) throws Exception {
		
		if(venue.getEstado() != true) {
			throw new Exception("El Venue no ha sido aprobado. Busque otro o contacte al Administrador");
		}
		
		if(venue.buscarDisponibilidad(fecha) != true) {
			
			throw new Exception("El venue está ocupado para esa fecha. Busque otro");	
		}
		
		
		Evento eventoNuevo = new Evento(tipo, fecha, hora, idEvento, venue, organizador);
		eventoNuevo.addEventoBorrador();
		
		return "Se ha creado el siguiente evento:" + eventoNuevo.getIdEvento();
			
			
		}
	
	
	public List<String> validarEvento(Evento evento) throws Exception {
		
		
		List<String> problemas = new ArrayList<>();
		
		if (evento.getVenueAsignado().getEstado() != true) {
			
			throw new Exception("El venue no ha sido aprobado");
			
		} else if {
		
		
		
		
		
		
		
		
		
	}
		
		
	}
		
		
		
		
	
	

	
	
		
	
	
	
	


