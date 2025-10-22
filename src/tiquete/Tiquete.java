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
			double desc = dcto.getPorcentaje();
			
			double valorDescuento = base * desc;
			base = base - valorDescuento;
			
		}
		double cargoServicio = base * cargoServicioPct;
		return base + cargoServicio + costoEmisionFijo;
	}

	public String getIdTiquete() {
		return idTiquete;
	}

	public double getPrecio() {
		return precio;
	}

	
	public double getPrecio() {
		return this.precio;
	}
	
}
