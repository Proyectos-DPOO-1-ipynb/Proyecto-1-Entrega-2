package evento.venue;

import java.util.ArrayList;
import java.util.List;

import evento.Evento;

public class LocalidadBasica extends Localidad {
	
	
	private int cuposTotales;
	private int cuposDisponibles;
	
	
	
	
	
	public LocalidadBasica(String idLocalidad, double precio, Evento eventoAsociado, int capacidad) {
		
		super(idLocalidad, precio, eventoAsociado, "BASICA");
		
		this.cuposTotales = capacidad;
		this.cuposDisponibles = capacidad;
		
	}




	public int getCuposDisponibles() {
		return cuposDisponibles;
	}
	
	
	
	
	public int getCuposTotales() {
		return cuposTotales;
	}




	public boolean verificarCupo(int numero) {
		
		if(numero > this.cuposDisponibles) {
			
			return false;
		} else {
			
			return true;
		}
		
	}
	
	
	public void reservar(int numero) {
		
		this.cuposDisponibles -= numero;
		
	}
	
	
	public void liberar(int numero) {
		
		
		
		this.cuposDisponibles += numero;
		
		
		if (this.cuposDisponibles > this.cuposTotales) {
			this.cuposDisponibles = this.cuposTotales;
			
		}
		
		
		
	}




	
	
	
	

}
