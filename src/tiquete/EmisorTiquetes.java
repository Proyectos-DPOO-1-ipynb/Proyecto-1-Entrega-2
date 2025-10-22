package tiquete;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import evento.Evento;
import evento.venue.Localidad;
import evento.venue.LocalidadBasica;
import evento.venue.LocalidadNumerada;
import evento.venue.LocalidadPalco;

public class EmisorTiquetes {

	private static Set<String> codigos = new HashSet<>();
	
	// Tiquetes Simples
	public static List<TiqueteSimple> generarTiqueteSimple(Evento evento, Localidad localidad, int cantidad) throws Exception {
		// TODO modificar tamaño eventos al comprar
		if(cantidad <= 0) {
			throw new Exception("No se ha especificado un número de tiquetes");
		}
		
		List<TiqueteSimple> generados = new ArrayList<>();
		// Verificación correspondencia localidad-Evento
		if(localidad.verificadorAsociacionEvento(evento) != true) {	
			throw new Exception("La localidad no está asociada al evento ingresado por parámetro");	
		}
		
		if(localidad.getTipo().equals("NUMERADA")) {
			throw new Exception("Si la localidad es numerada, se deben generar tiquetes simples-numerados");
		}
		
		LocalidadBasica local = (LocalidadBasica) localidad;
		
		if(local.verificarCupo(cantidad) == false) {
			throw new Exception("La cantidad de tiquetes que desea crear supera el cupo de la Localidad. Intente otro valor");
		}
	
		// Generación de Id único del tiquete
		
		for(int i=0;i<cantidad;i++) {
		
			String codigo;
			
	    	int numero = ( int ) ( Math.random( ) * 100000000 );   
			codigo = String.format( "%08d", numero );
	        
	        while( codigos.contains( codigo ) )
	        {
	        	int numeroe = ( int ) ( Math.random( ) * 100000000 );   
				codigo = String.format( "%08d", numeroe );
	        }
	
	        codigos.add(codigo);
			
			TiqueteSimple tiket = new TiqueteSimple(codigo, local.getPrecio(), evento.getFecha(), evento.getHora(), evento, localidad);
			local.addTiquetesVendidos(tiket);
			generados.add(tiket);
			
		}
		
		local.reservar(cantidad);
		evento.reservarAsientos(cantidad);
		
		return generados;

	}
	
	// Tiquetes Numerados
	public static List<TiqueteNumerado> generarTiqueteNumerado(Evento evento, Localidad localidad, List<String> asientos) throws Exception {
		// TODO modificar tamaño eventos
	    if (asientos == null || asientos.isEmpty()) {
	        throw new Exception("No se ha especificado un catálogo de asientos");
	    }
	        
	    if (!localidad.verificadorAsociacionEvento(evento)) {
	    	throw new Exception("La localidad no está asociada al evento ingresado por parámetro");
	    }
	    
	    if (localidad.getTipo().equals("BASICA")) {
	        throw new Exception("Si la localidad es básica, se deben generar tiquetes simples solamente");
	    }
	    
	    LocalidadNumerada local = (LocalidadNumerada) localidad;

	    Set<String> unicos = new HashSet<>(asientos);
	    
	    if (unicos.size() != asientos.size()) {
	        throw new Exception("Hay asientos repetidos");
	    }
	        
	    for (String asiento: asientos) {
	        if (!local.asientoLibre(asiento)) {
	            throw new Exception("El asiento " + asiento + " no está libre");
	        	}
	    }

	    for (String asiento : asientos) {
	        local.ocuparAsiento(asiento);
	    }

	    List<TiqueteNumerado> generados = new ArrayList<>(asientos.size());
	    for (String asiento : asientos) {
	    	
	        String codigo;
	        
	        int numero = (int) (Math.random() * 10000000);
	        codigo = String.format("%07d", numero);
	        
	        while (codigos.contains("N" + codigo)) {
	        	
	            int numeroe = (int) (Math.random() * 10000000);
	            codigo = String.format("%07d", numeroe);
	        }
	        
	        codigos.add("N" + codigo);

	        TiqueteNumerado tiket = new TiqueteNumerado("N" + codigo, local.getPrecio(), evento.getFecha(), evento.getHora(),
	                evento, localidad, asiento);
	        local.addTiquetesVendidos(tiket);
	        generados.add(tiket);
	    }
	    
	    evento.reservarAsientos(asientos.size());
	    return generados;
	}

	
	public static TiqueteEntradaMultipleLugar emitirPalco(Evento evento, LocalidadPalco localidad, int idPalco, double precioPropio) throws Exception {
	    
	    if (!localidad.disponibilidadPalco(idPalco)) {
	    		throw new Exception ("El palco no se encuentra disponible");
	    }
	    
	    if (!localidad.verificadorAsociacionEvento(evento)) {
	    		throw new Exception("La localidad no está asociada al evento ingresado por parámetro");
	    }

	    int n = localidad.getTiquetesXPalco();
	    List<TiqueteSimple> generados = new ArrayList<>();

	    for(int i=0;i<n;i++) {
			
			String codigo;
			
	    	int numero = ( int ) ( Math.random( ) * 100000000 );   
			codigo = String.format( "%08d", numero );
	        
	        while( codigos.contains( codigo ) )
	        {
	        	int numeroe = ( int ) ( Math.random( ) * 100000000 );   
				codigo = String.format( "%08d", numeroe );
	        }
	
	        codigos.add(codigo);
			
			TiqueteSimple tiket = new TiqueteSimple(codigo, localidad.getPrecio(), evento.getFecha(), evento.getHora(), evento, localidad);
			localidad.addTiquetesVendidos(tiket);
			generados.add(tiket);
			
		}

	    // Crea el paquete del palco con su precio propio (puede diferir de la suma)
	    String idPaquete = generarIdPaquete();
	    
	    TiqueteEntradaMultipleLugar paquete = new TiqueteEntradaMultipleLugar(idPaquete, precioPropio, generados, localidad);

	    evento.reservarAsientos(n);
	    localidad.reservarPalco(idPalco);
	    

	    return paquete;
	}
	
	
	public static TiqueteEntradaMultipleEvento emitirPaseTemporada(List<Evento> eventos, Map<Evento, Integer> cantidadesPorEvento, double precioPropio) throws Exception {
		
	    if (eventos == null || eventos.isEmpty()) {
	    		throw new Exception("Se deben seleccionar al menos 2 eventos");
	    }
	    
	    List<TiqueteSimple> tiquetes = new ArrayList<>();
	    List<String> idEventos = new ArrayList<>();

	    for (Evento e : eventos) {
	        int cant = cantidadesPorEvento.getOrDefault(e, 2); //se emite 2 por default
	        List <Localidad> localidades = e.getLocalidades();
	        boolean encontro = false;
	        while (encontro == false) {
		        for (Localidad loc: localidades) {
		        		if (loc.getTipo() == "BASICA") {
		        			LocalidadBasica local = (LocalidadBasica) loc;
		        			if (local.getCuposDisponibles() >= cant) {
		        				List<TiqueteSimple> tiks = generarTiqueteSimple(e, loc, cant);
		        				for (TiqueteSimple tik: tiks) {
		        					tiquetes.add(tik);
		        				}
		        				idEventos.add(e.getIdEvento());
		        				encontro = true;
		        			}
		        		} else {
		        			LocalidadNumerada local = (LocalidadNumerada) loc;
		        			if (local.getDisponibles() >= cant) {
		        				
		        				int contador = cant;
		        				List<String> seleccionados = new ArrayList<>();
		        				
		        				for (String asiento: local.getAsientosTotales()) {
		        					if (!local.getAsientosOcupados().contains(asiento)) {
		        						seleccionados.add(asiento);
		        						contador -= 1;
		        						if (contador == 0) {
			        						break;
			        					}
		        					}
		        				}	
		        				if (contador == 0) {
		        					List<TiqueteNumerado> tiks = generarTiqueteNumerado(e, loc, seleccionados);
		        					for (TiqueteNumerado tik: tiks) {
		        						tiquetes.add(tik);
		        					}
		        					idEventos.add(e.getIdEvento());
		        					encontro = true;
		        				}
		        			}	
		        		}
		        }
	        }
	        if (idEventos.getLast() != e.getIdEvento()) {
	        		throw new Exception ("El evento " + e + " no tiene dispoibilidad");
	        }
	    }
	    
	    String idPaquete = generarIdPaquete();
        
	    return new TiqueteEntradaMultipleEvento(idPaquete, precioPropio, tiquetes, idEventos, idEventos.size());
	} 
	
	public static TiqueteDeluxe emitirDeluxe(TiqueteSimple base, List<Object> beneficios, List<Object> mercancia, double precioDeluxe) throws Exception {
	    if (base == null) {
	    		throw new Exception("Debe haber un tiquete base sobre el cual se hace el paquete Deluxe");
	    }

	    	String idPaquete = generarIdPaquete();

        codigos.add(idPaquete);
	    TiqueteDeluxe deluxe = new TiqueteDeluxe(idPaquete, precioDeluxe, base.getFecha(), base.getHora(), base.getEventoAsociado(), base.getLocalidadTiquete(), beneficios, mercancia);

	    return deluxe;
	}
	
	public static String generarIdPaquete () {
		String idPaquete;
	    
	    int numero = ( int ) ( Math.random( ) * 100000000 );   
		idPaquete = String.format( "%08d", numero );
        
        while( codigos.contains( idPaquete ) )
        {
        	int idPaquetee = ( int ) ( Math.random( ) * 100000000 );   
        		idPaquete = String.format( "%08d", idPaquetee );
        }

        codigos.add(idPaquete);
        return "P" + idPaquete;
	}
	
}
