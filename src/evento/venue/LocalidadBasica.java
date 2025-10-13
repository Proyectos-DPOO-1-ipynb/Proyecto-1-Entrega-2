package evento.venue;

import java.util.ArrayList;
import java.util.List;

import evento.Evento;
import tiquete.Tiquete;
import tiquete.TiqueteSimple;

public class LocalidadBasica extends Localidad {
	
	
	private int cuposTotales;
	private int cuposDisponibles;
	private List<Tiquete> tiquetes = new ArrayList<>();
	
	
	public LocalidadBasica(String idLocalidad, double precio, Evento eventoAsociado, int capacidad) throws Exception {
		
		super(idLocalidad, precio, eventoAsociado, "BASICA");
		
		if(capacidad < 0) {
			throw new Exception("La capacidad no puede ser negativa");
		}
		
		this.cuposTotales = capacidad;
		this.cuposDisponibles = capacidad;
	}

	public int getCuposDisponibles() {
		return cuposDisponibles;
	}
	

	public int getCuposTotales() {
		return cuposTotales;
	}


	public boolean verificarCupo(int numero) throws Exception {
		
		if(numero < 0) {
			throw new Exception("No se puede verificar un número negativo de cupos");
		}
		
		if(numero > this.cuposDisponibles) {
			return false;
		} else {
			return true;
		}
	}
	
	
	public void reservar(int numero) throws Exception {

		if(numero < 0) {
			throw new Exception("No se puede reservar un número negativo de tiquetes");
		}
		this.cuposDisponibles -= numero;
	}
	
	
	public void liberar(int numero) throws Exception {
		
		if(numero < 0) {
			throw new Exception("No se puede liberar un número negativo de tiquetes");
		}

		this.cuposDisponibles += numero;	
		
		if (this.cuposDisponibles > this.cuposTotales) {
			this.cuposDisponibles = this.cuposTotales;
		}
	}
		
	
	public void addTiqueteSimple(TiqueteSimple tiquete) {
		this.tiquetes.add(tiquete);
	}
		
}




	
	
	
	


