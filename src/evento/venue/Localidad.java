package evento.venue;

import java.time.LocalDate;

import evento.Evento;

public abstract class Localidad {
	
	private String idLocalidad;
	private double precio;
	private Descuento descuento;
	private Evento eventoAsociado;
	private String tipo;
	
	
	
	public Localidad(String idLocalidad, double precio, Evento eventoAsociado, String tipo) throws Exception{
		
		
		if (precio < 0) {
			throw new Exception("El precio no puede ser negativo");
		}
		
		if(eventoAsociado == null) {
			throw new Exception("Falla al ingresar el evento");
		}
		
		this.idLocalidad = idLocalidad;
		this.precio = precio;
		this.eventoAsociado = eventoAsociado;
		this.tipo = tipo;
		this.descuento = null;
		
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

	public double getDescuento() {
		return descuento.getPorcentaje();
	}

	public boolean verificadorAsociacionEvento(Evento evento) {
		
		if(this.eventoAsociado.getIdEvento().equals(evento.getIdEvento())) {
			return true;
		} else {
			return false;
		}
	}
	
	public void agregarDescuento(Descuento descuento) {
		this.descuento = descuento;
	}
	
	
	public void cancelarDescuento() {
		this.descuento = null;
	}
	

}
