package evento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import evento.venue.Localidad;
import evento.venue.LocalidadBasica;
import evento.venue.LocalidadNumerada;
import evento.venue.Venue;
import usuario.Administrador;
import usuario.comprador.Organizador;

public class GestorEventos {
	
	
	public static String proponerVenue(Organizador organizador,String VenueId,  String direccion, int maxCapacidad, List<String> restricciones) throws Exception {
		
		Venue propuesta = new Venue(VenueId, direccion, maxCapacidad, restricciones);
		
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
	
	public static String crearVenue(Administrador administrador, String adminId, String VenueId,  String direccion, int maxCapacidad, List<String> restricciones) throws Exception {
		
		if (!adminId.equals(administrador.getAdminId())) {
			throw new Exception("No eres el administrador!");
		}
		
		Venue nuevoVenue = new Venue(VenueId, direccion, maxCapacidad, restricciones);
		
		nuevoVenue.addPropuestaVenue();
		nuevoVenue.cambiarEstadoAprobado();
		
		return "El Venue ha sido creado y añadido al catálogo";
	}
	
	
	public static String crearBorradorEvento(Organizador organizador, String tipo, String idEvento, Venue venue, 
			LocalDate fecha, int hora, int capacidadEvento) throws Exception {
		
		if(venue.getEstado() != true) {
			throw new Exception("El Venue no ha sido aprobado. Busque otro o contacte al Administrador");
		}
		
		if(venue.buscarDisponibilidad(fecha) != true) {
			throw new Exception("El venue está ocupado para esa fecha. Busque otro");	
		}
		
		
		Evento eventoNuevo = new Evento(tipo, fecha, hora, idEvento, venue, organizador, capacidadEvento);
		eventoNuevo.addEventoBorrador();
		
		return "Se ha creado el siguiente evento en borrador:" + eventoNuevo.getIdEvento();

		}
	
	
	public static String asociarLocalidadBasica(String idLocalidad, double precio, Evento eventoAsociado, int capacidad) throws Exception {
		
		int capacidadLocs = verificarAgregarLocalidad(eventoAsociado, capacidad);
		
		if (capacidadLocs > 0) {
			LocalidadBasica localidadprop = new LocalidadBasica(idLocalidad, precio, eventoAsociado, capacidad);
			eventoAsociado.agregarLocalidad(localidadprop);
			return "La localidad con id: " + localidadprop.getIdLocalidad() + " fue añadida al evento con id " + eventoAsociado.getIdEvento();
		} else {
			throw new Exception("La localidad que se intenta asociar debe tener " + Math.abs(capacidadLocs) + " cupos o menos");
		}
	}
	

	
	public static String asociarLocalidadNumerada(String idLocalidad, double precio, Evento eventoAsociado, List<String> asientos, int capacidad) throws Exception {
		
		int capacidadLocs = verificarAgregarLocalidad(eventoAsociado, capacidad);
		
		if (capacidadLocs > 0) {
			LocalidadNumerada localidadprop = new LocalidadNumerada(idLocalidad, precio, eventoAsociado, asientos, capacidad);
			eventoAsociado.agregarLocalidad(localidadprop);	
			return "La localidad con id: " + localidadprop.getIdLocalidad() + " fue añadida al evento con id " + eventoAsociado.getIdEvento();
		} else {
			throw new Exception("La localidad que se intenta asociar debe tener " + Math.abs(capacidadLocs) + " cupos o menos");
		}
	}
	
	
	
	public static List<String> validarEvento(Evento evento) {
		
		int capacidadnom = 0;
		
		List<String> problemas = new ArrayList<>();
		
		if (evento.getVenueAsignado().getEstado() != true) {			
			problemas.add("Venue no aprobado");	
		}
		
		if (evento.getVenueAsignado().buscarDisponibilidad(evento.getFecha())==false) {
			problemas.add("Venue no disponible");
		}  
		
		if(evento.getLocalidades().size() == 0) {	
			problemas.add("No hay localidades asignadas");
		} //Se puede considerar que la siguiente funcinalidad quede incrustada en el constructor de LocalidadNumerada
			
			List<Localidad> localidadese = evento.getLocalidades();
			
			for(Localidad loc:localidadese) {
				
				if(loc.getTipo().equals("BASICA")) {			
					LocalidadBasica local = (LocalidadBasica) loc;
					capacidadnom += local.getCuposTotales();
				}
				
				if(loc.getTipo().equals("NUMERADA")) {
					LocalidadNumerada local = (LocalidadNumerada) loc;
					capacidadnom += local.getAsientosTotales().size();
					List<String> asientoscontrol = new ArrayList<>(local.getAsientosTotales());
					List<String> asientos = new ArrayList<>(local.getAsientosTotales());
					
					for(String asiento:asientoscontrol) {
						asientos.remove(asiento);
						if(asientos.contains(asiento)) {
							problemas.add("Hay asientos repetidos en la localidad " + loc.getIdLocalidad());
							break;
						}
					}	
				}	
			}
			
			if(capacidadnom > evento.getVenueAsignado().getMaxCapacidad()) {
				problemas.add("Los cupos de las localidades superan la capacidad del venue");
			}
			
		return problemas;	
	}
	
	
	public static String publicarEvento(Evento evento) throws Exception {
		
		
		if(validarEvento(evento).size() == 0) {
			evento.cambiarEstado();
			if(evento.getEstado().equals("PUBLICADO")) {
				return "Ha sido publicado el siguiente evento: " + evento.getIdEvento();
			} else {
				return "Intente nuevamente";
			}
		} else {
			return "El evento no ha sido publicado, pues ha fallado la validación";
		}
	}	

	public static int verificarAgregarLocalidad(Evento evento, int capacidad) {
		
		List<Localidad> locs = evento.getLocalidades();
		int totales = evento.getTiquetesMax();
		totales -= capacidad;
		
		if (!locs.isEmpty()) { //verifica que no haya mas cupo en localidades que en la totalidad del evento
			for (Localidad loc: locs) {
				if (loc.getTipo().equals("BASICA")) {
					LocalidadBasica locb = (LocalidadBasica) loc;
					totales -= locb.getCuposTotales();
				} else {
					LocalidadNumerada locn = (LocalidadNumerada) loc;
					totales -= locn.getAsientosTotales().size();
				}
			}
			return totales;
		} else {
			return totales;
		}
	}
		
}
		
		
		
		
	
	

	
	
		
	
	
	
	


