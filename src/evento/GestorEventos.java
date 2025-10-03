package evento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import evento.venue.Localidad;
import evento.venue.LocalidadNumerada;
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
	
	
	public static List<String> validarEvento(Evento evento) {
		
		
		List<String> problemas = new ArrayList<>();
		
		if (evento.getVenueAsignado().getEstado() != true) {
			
			
			problemas.add("Venue no aprobado");
			
		} else if (evento.getVenueAsignado().buscarDisponibilidad(evento.getFecha())==false) {
			
			problemas.add("Venue no disponible");
			
		} else if(evento.getLocalidades().size() == 0) {
			
			
			problemas.add("No hay localidades asignadas");
			
			
		} else {
			
			List<Localidad> localidadese = evento.getLocalidades();
			
			for(Localidad loc:localidadese) {
				
				if(loc.getTipo().equals("NUMERADA")) {
				
					LocalidadNumerada local = (LocalidadNumerada) loc;
					
					List<String> asientoscontrol = local.getAsientosTotales();
					List<String> asientos = local.getAsientosTotales();
					
					for(String asiento:asientoscontrol) {
						
						asientos.remove(asiento);
						if(asientos.contains(asiento)) {
							
							
							problemas.add("Hay asientos repetidos en la localidad " + loc.getIdLocalidad());
							break;
							
						}
					}	
				}
				
				break;
			}
			
		}
		
		return problemas;	
		
		
	}
	
	
	public static String publicarEvento(Evento evento) {
		
		
		if(validarEvento(evento).size() == 0) {
			
			evento.cambiarEstado();
			evento.getVenueAsignado().addEventotoVenue(evento);
			
			
			
		}
		
		return "Ha sido publicado el siguiente evento: " + evento.getIdEvento();
		
		
		
	}
		
		
	}
		
		
		
		
	
	

	
	
		
	
	
	
	


