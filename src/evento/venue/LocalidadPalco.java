package evento.venue;

import java.util.List;
import java.util.Map;

import evento.Evento;
import tiquete.Tiquete;

public class LocalidadPalco extends Localidad{

	private int cantidadPalcos;
	private int tiquetesXPalco;
	private Map<Integer, List<Tiquete>> palcosVendidos;
	private String tipoTiquete;
	
	public LocalidadPalco(String idLocalidad, double precio, Evento eventoAsociado, String tipo, int capacidad, int cantidadPalcos, int tiquetesXPalco, String tipoTiquete)
			throws Exception {
		super(idLocalidad, precio, eventoAsociado, tipo, capacidad);
		// TODO Auto-generated constructor stub
		this.cantidadPalcos = cantidadPalcos;
		this.tiquetesXPalco = tiquetesXPalco;
		this.tipoTiquete = tipoTiquete;
	}

	
	public int disponibles(){
		return cantidadPalcos - palcosVendidos.size();
	} 
	
	
	
	
}
