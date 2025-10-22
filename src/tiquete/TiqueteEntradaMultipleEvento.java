package tiquete;

import java.util.ArrayList;
import java.util.List;

public class TiqueteEntradaMultipleEvento extends TiqueteCompuesto{

	private List<String> idEventos;
	private int cantidadEventos = idEventos.size();
	
	
	public TiqueteEntradaMultipleEvento(String idTiquete, double precio, List<TiqueteSimple> tiquetes,
			List<String> idEventos, int cantidadEventos) {
		super(idTiquete, precio, tiquetes);
		this.idEventos = idEventos;
		this.cantidadEventos = cantidadEventos;
	}
	

	
	
}
