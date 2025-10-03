package evento.venue;

import java.time.LocalDate;

import evento.Evento;

public abstract class Localidad {
	
	private String idLocalidad;
	private double precio;
	private Evento eventoAsociado;
	private String tipo;
	
	
	
	public Localidad(String idLocalidad, double precio, Evento eventoAsociado, String tipo){
		
		this.idLocalidad = idLocalidad;
		this.precio = precio;
		this.eventoAsociado = eventoAsociado;
		this.tipo = tipo;
		
	}



	public String getTipo() {
		return tipo;
	}



	public String getIdLocalidad() {
		return idLocalidad;
	}
	
	
	
	
	
	public double getPrecio() {
		return precio;
	}



	public boolean verificadorAsociacionEvento(Evento evento) {
		
		
		if(this.eventoAsociado.equals(evento)) {
			
			return true;
			
		} else {
			return false;
		}
	}
	
	
	

}
