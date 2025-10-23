package tiquete;


import evento.venue.Descuento;
import usuario.comprador.Comprador;

public abstract class Tiquete {
	
	private String idTiquete;
	private double precio;
	
	public enum EstadoTiquete { EMITIDO, ANULADO, TRANSFERIDO }
	
	protected EstadoTiquete estado = EstadoTiquete.EMITIDO;   
	protected Comprador propietarioActual;    
	
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

	public EstadoTiquete getEstado() { 
		return estado; 
	}       
	
	public Comprador getPropietarioActual() { 
		return propietarioActual; 
	} 
	
	public void setPropietarioActual(Comprador c) { 
		this.propietarioActual = c; 
	}
	
	protected void marcarAnulado() { 
		this.estado = EstadoTiquete.ANULADO; 
	}   
	
	protected void marcarTransferido() { 
		this.estado = EstadoTiquete.TRANSFERIDO; 
	} 
	
}
