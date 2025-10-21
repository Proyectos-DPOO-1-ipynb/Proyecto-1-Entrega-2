package tiquete;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import evento.Evento;
import evento.venue.Localidad;
import evento.venue.LocalidadBasica;
import evento.venue.LocalidadNumerada;

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

	
	
	
	
}
