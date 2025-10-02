package tiquete;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import evento.Evento;
import evento.venue.Localidad;

public class EmisorTiquetes {
	
	private static Set<String> codigos = new HashSet<>();
	
	
	public static Tiquete generarTiqueteSimple(Evento evento, Localidad localidad, double precio, LocalDate fecha, int hora) {
		
		String codigo;
		
    	int numero = ( int ) ( Math.random( ) * 100000000 );   
		codigo = String.format( "%08d", numero );

        
        while( codigos.contains( codigo ) )
        {
        	int numeroe = ( int ) ( Math.random( ) * 100000000 );   
			codigo = String.format( "%08d", numeroe );
        }

       
        codigos.add(codigo);
		
		return new TiqueteSimple(codigo, precio, fecha, hora, evento, localidad);
		
	}
	
	
	
	public static Tiquete generarTiqueteNumerado(Evento evento, Localidad localidad, double precio, LocalDate fecha, int hora, String idAsiento) {
		
		String codigo;
		
    	int numero = ( int ) ( Math.random( ) * 10000000 );   
		codigo = String.format( "%07d", numero );

        
        while( codigos.contains("N" + codigo ) )
        {
        	int numeroe = ( int ) ( Math.random( ) * 10000000 );   
			codigo = String.format( "%07d", numeroe );
        }

       
        codigos.add("N" + codigo);
		
		return new TiqueteNumerado("N" + codigo, precio, fecha, hora, evento, localidad, idAsiento);
	
	
	
	
	
	}
	
	

}
