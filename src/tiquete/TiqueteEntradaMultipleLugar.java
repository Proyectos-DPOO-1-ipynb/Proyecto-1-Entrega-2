package tiquete;

import java.util.List;

import evento.venue.Localidad;

public class TiqueteEntradaMultipleLugar extends TiqueteCompuesto {
	
	private Localidad idLocalidad;
	public TiqueteEntradaMultipleLugar(String idTiquete, double precio, List<TiqueteSimple> tiquetes, Localidad idLocalidad) {
		super(idTiquete, precio, tiquetes);
		this.idLocalidad = idLocalidad;
	}
	
	public Localidad getIdLocalidad() {
		return idLocalidad;
	}

	
}
