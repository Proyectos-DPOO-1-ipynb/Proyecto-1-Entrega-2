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
	
	
	public static List<TiqueteSimple> generarTiqueteSimple(Evento evento, Localidad localidad, LocalDate fecha, int hora,
			int cantidad) throws Exception {
		
		
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
			
			TiqueteSimple tiket = new TiqueteSimple(codigo, local.getPrecio(), fecha, hora, evento, localidad);
			evento.addTiqueteSimple(tiket);
			local.addTiqueteSimple(tiket);
			generados.add(tiket);
			
		}
		
		local.reservar(cantidad);
		
		return generados;
		
	 
		
	}
	
	
	
	public static List<TiqueteNumerado> generarTiqueteNumerado(Evento evento, Localidad localidad, LocalDate fecha, 
			int hora, List<String> asientos) throws Exception {
		
		
		List<TiqueteNumerado> generados = new ArrayList<>();
		
		if(localidad.verificadorAsociacionEvento(evento) != true) {
			
			throw new Exception("La localidad no está asociada al evento ingresado por parámetro");
			
		}
		
		if(localidad.getTipo().equals("BASICA")) {
			
			throw new Exception("Si la localidad es básica, se deben generar tiquetes simples solamente");
		}
		
		
		LocalidadNumerada local = (LocalidadNumerada) localidad;
		
		for(String asiento:asientos) {
			
			if(local.asientoLibre(asiento) != false) {
				
				throw new Exception("El asiento "+ asiento + "no está libre");
				
			}			
		}
		
		local.reservarAsientos(asientos);
		
		
		
		for(String asiento:asientos) {
		
			String codigo;
			
	    	int numero = ( int ) ( Math.random( ) * 10000000 );   
			codigo = String.format( "%07d", numero );
	
	        
	        while( codigos.contains("N" + codigo ) )
	        {
	        	int numeroe = ( int ) ( Math.random( ) * 10000000 );   
				codigo = String.format( "%07d", numeroe );
	        }
	
	       
	        codigos.add("N" + codigo);
			
			TiqueteNumerado tiket = new TiqueteNumerado("N" + codigo, local.getPrecio(), fecha, hora, evento, localidad, asiento);
			evento.addTiqueteNumerado(tiket);
			local.addTiqueteNumerado(tiket);
			generados.add(tiket);
			
			
		}
		
		return generados;
	
	
	
	}
	
	

}
