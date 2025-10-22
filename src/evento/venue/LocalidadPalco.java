package evento.venue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import evento.Evento;
import tiquete.Tiquete;

public class LocalidadPalco extends Localidad{

	private int cantidadPalcos;
	private int tiquetesXPalco;
	private Map<Integer, Integer> palcosDisponibles;
	private Map<Integer, Integer> palcosVendidos;
	private List<Tiquete> tiquetesVendidos;
	
	public LocalidadPalco(String idLocalidad, double precio, Evento eventoAsociado, String tipo, int capacidad, int cantidadPalcos, int tiquetesXPalco, Map<Integer, Integer> asientos)
			throws Exception {
		
		super(idLocalidad, precio, eventoAsociado, tipo, capacidad);
		// TODO Auto-generated constructor stub
		
		if (cantidadPalcos != asientos.size()) {
			throw new Exception ("La cantidad de palcos disponibles debe ser igual al número ingresado");
		}
		
		if (tiquetesXPalco < 0) {
			throw new Exception ("Los tiquetes por palco no pueden ser negativos");
		}
		
		if (cantidadPalcos < 0) {
			throw new Exception ("EL catalogo de palcos debe ser un número positivo");
		}	
		
		int contador = 0;
		
		for (Integer tam: asientos.values()) {
			contador += tam;
			if (tam != tiquetesXPalco) {
				throw new Exception("Los asientos de algún palco no coincide con los tiquetesXPalco");
			} 
		}
		
		if (contador != capacidad) {
			throw new Exception ("La localidad no tiene la misma capacidad que los asientos disponibles");
		}
		
		
		this.cantidadPalcos = cantidadPalcos;
		this.tiquetesXPalco = tiquetesXPalco;
		this.palcosDisponibles = asientos;
		this.palcosVendidos = new HashMap<Integer, Integer>();
		this.tiquetesVendidos = new ArrayList<Tiquete>();
	}

	public int disponibles(){
		return palcosDisponibles.size();
	} 

	public boolean disponibilidadPalco (int numeroPalco) {
		if (palcosDisponibles.containsKey(numeroPalco)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void reservarPalco (int numeroPalco) {
		if (disponibilidadPalco(numeroPalco)) {
			Integer asientos = palcosDisponibles.get(numeroPalco);
			palcosVendidos.put(numeroPalco, asientos);
			palcosDisponibles.remove(numeroPalco);
		}
	}
	
	public void liberarPalco (int numeroPalco) {
		if (palcosVendidos.containsKey(numeroPalco)) {
			Integer asientos = palcosVendidos.get(numeroPalco);
			palcosDisponibles.put(numeroPalco, asientos);
			palcosVendidos.remove(numeroPalco);
		}
	}
	
	public void addTiquetesVendidos(Tiquete tiquete) {
		tiquetesVendidos.add(tiquete);
	}

	public int getCantidadPalcos() {
		return cantidadPalcos;
	}

	public int getTiquetesXPalco() {
		return tiquetesXPalco;
	}

	public Map<Integer, Integer> getpalcosDisponibles() {
		return palcosDisponibles;
	}

	public Map<Integer, Integer> getPalcosVendidos() {
		return palcosVendidos;
	}

	public List<Tiquete> getTiquetesVendidos() {
		return tiquetesVendidos;
	}
	
	
	
}
