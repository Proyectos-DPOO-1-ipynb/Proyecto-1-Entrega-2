package evento.venue;

import java.util.ArrayList;
import java.util.List;

import evento.Evento;
import tiquete.Tiquete;
import tiquete.TiqueteNumerado;
import tiquete.TiqueteSimple;

public class LocalidadNumerada extends Localidad {
	
	
	private List<String> asientosTotales; //Cada asiento es un idAsiento str
	private List<String> asientosOcupados = new ArrayList<>();
	private List<Tiquete> tiquetes = new ArrayList<>();

	
	public LocalidadNumerada(String idLocalidad, double precio, Evento eventoAsociado, List<String> asientos) throws Exception {
		
		super(idLocalidad, precio, eventoAsociado, "NUMERADA");
		
		if(asientos == null) {
			
			throw new Exception("Inserte bien la lista de asientos");
			
		}
		
		if(asientos.isEmpty()) {
			
			throw new Exception("No puede tener un catálogo de asientos vacío");
		}
		

		
		
		this.asientosTotales = asientos;
		
	}
	
	
	public int disponibles() {
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
		
		if(this.asientoLibre(asiento) != true) {
			
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


	public List<String> getAsientosTotales() {
		return asientosTotales;
	}


	public List<String> getAsientosOcupados() {
		return asientosOcupados;
	}


	public void addTiqueteNumerado(TiqueteNumerado tiquete) {
		
		
		this.tiquetes.add(tiquete);
		
	}
	
	
	

}
