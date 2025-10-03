package tiquete;

import java.util.List;

import evento.venue.Localidad;

public class TiqueteEntradaMultipleLugar extends TiqueteCompuesto {

	private Localidad idLugar;
	
	public TiqueteEntradaMultipleLugar(String idTiquete, double precio, List<TiqueteSimple> tiquetes, Localidad idLugar) {
		super(idTiquete, precio, tiquetes);
		this.idLugar = idLugar;
	}
	
}
