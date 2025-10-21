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
	private Map<Integer, List<Integer>> palcosDisponibles;
	private Map<Integer, List<Integer>> palcosVendidos;
	private List<Tiquete> tiquetesVendidos;
	
	public LocalidadPalco(String idLocalidad, double precio, Evento eventoAsociado, String tipo, int capacidad, int cantidadPalcos, int tiquetesXPalco, Map<Integer, List<Integer>> asientos)
			throws Exception {
		
		super(idLocalidad, precio, eventoAsociado, tipo, capacidad);
		// TODO Auto-generated constructor stub
		
		if (tiquetesXPalco < 0) {
			throw new Exception ("Los tiquetes por palco no pueden ser negativos");
		}
		
		if (cantidadPalcos < 0) {
			throw new Exception ("EL catalogo de palcos debe ser un número positivo");
		}	
		
		for (List<Integer> tam: asientos.values()) {
			if (tam.size() != tiquetesXPalco) {
				throw new Exception("Los asientos de algún palco no coincide con los tiquetesXPalco");
			}
		}
		
		this.cantidadPalcos = cantidadPalcos;
		this.tiquetesXPalco = tiquetesXPalco;
		this.palcosDisponibles = asientos;
		this.palcosVendidos = new HashMap<Integer, List<Integer>>();
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
			List<Integer> asientos = palcosDisponibles.get(numeroPalco);
			palcosVendidos.put(numeroPalco, asientos);
			palcosDisponibles.remove(numeroPalco);
		}
	}
	
	public void liberarPalco (int numeroPalco) {
		if (palcosVendidos.containsKey(numeroPalco)) {
			List<Integer> asientos = palcosVendidos.get(numeroPalco);
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

	public Map<Integer, List<Integer>> getpalcosDisponibles() {
		return palcosDisponibles;
	}

	public Map<Integer, List<Integer>> getPalcosVendidos() {
		return palcosVendidos;
	}

	public List<Tiquete> getTiquetesVendidos() {
		return tiquetesVendidos;
	}
	
	
	
	
}
