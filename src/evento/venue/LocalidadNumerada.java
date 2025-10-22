package evento.venue;

import java.util.ArrayList;
import java.util.List;

import evento.Evento;
import tiquete.Tiquete;
import tiquete.TiqueteNumerado;
import tiquete.TiqueteSimple;

public class LocalidadNumerada extends Localidad {
	
	
	private List<String> asientosTotales; //Cada asiento es un idAsiento str
	private List<String> asientosOcupados;
	private List<Tiquete> tiquetesVendidos;

	
	public LocalidadNumerada(String idLocalidad, double precio, Evento eventoAsociado, List<String> asientos, int capacidad) throws Exception {
		
		super(idLocalidad, precio, eventoAsociado, "NUMERADA", capacidad);
		
		if(asientos == null) {
			throw new Exception("Inserte bien la lista de asientos");
		}
		
		if(asientos.isEmpty()) {	
			throw new Exception("No puede tener un catálogo de asientos vacío");
		}
		
		if(asientos.size() != capacidad) {
			throw new Exception ("La cantidad de asientos no concuerdad con la cantidad ingresada");
		}
		
		this.asientosTotales = asientos;
		this.asientosOcupados = new ArrayList<>();
		this.tiquetesVendidos = new ArrayList<>();
		
	}
	
	public int getDisponibles() {
		return this.asientosTotales.size() - this.asientosOcupados.size();
	}
	
	public boolean asientoLibre(String asiento) throws Exception {	
		if(!this.asientosTotales.contains(asiento)) {
			throw new Exception("Este asiento " + asiento + "no se encuentra en esta localidad");
		}
		if(this.asientosOcupados.contains(asiento)) {
			return false;
		} else {
			return true;
		}	
		
	}
	
	
	public void ocuparAsiento(String asiento) throws Exception {
		
		if(!this.asientosTotales.contains(asiento)) {		
			throw new Exception("Este asiento no se encuentra en esta localidad");
		}
		if (this.asientoLibre(asiento) != true) {
			throw new Exception("El asiento ya se encuentra ocupado");
		}
		this.asientosOcupados.add(asiento);
		
	}
	
	public void liberarAsiento(String asiento) throws Exception {
		
		if(!this.asientosTotales.contains(asiento)) {
			throw new Exception("Este asiento no se encuentra en esta localidad");	
		}
		if(this.asientoLibre(asiento) == true) {	
			throw new Exception("El asiento no está ocupado");
		}
		this.asientosOcupados.remove(asiento);
		
	}
	
	public void reservarAsientos(List<String> asientos) throws Exception {
		for(String silla:asientos) { 
			this.ocuparAsiento(silla);
		}
	}

	public void addTiquetesVendidos(TiqueteNumerado tiquete) {
		this.tiquetesVendidos.add(tiquete);	
	}
	
	
	public List<String> getAsientosTotales() {
		return asientosTotales;
	}


	public List<String> getAsientosOcupados() {
		return asientosOcupados;
	}
	
}
