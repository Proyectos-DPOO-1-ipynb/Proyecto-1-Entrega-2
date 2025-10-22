package tiquete;

import java.util.Optional;

import evento.venue.Descuento;

public abstract class Tiquete {
	
	private String idTiquete;
	private double precio;
	
	public Tiquete(String idTiquete, double precio){
		
		this.idTiquete = idTiquete;
		this.precio = precio;	
	}
	
	public double calcularPrecioFinal(double cargoServicioPct, double costoEmisionFijo, Descuento dcto) {
		double base = this.precio;
		
		if (dcto != null) {
			double porcentaje = dcto.getPorcentaje();
			
			// El descuento se calcula sobre el precio base 
			base = base - (porcentaje * base);
		}
		double cargoServicio = base * cargoServicioPct;
		return base + cargoServicio + costoEmisionFijo;
	}
	
	public double getPrecio() {
		return this.precio;
	}
	
}
