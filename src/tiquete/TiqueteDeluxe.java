package tiquete;

import java.util.ArrayList;
import java.util.List;

public class TiqueteDeluxe extends TiqueteCompuesto{

	private List<String> beneficios = new ArrayList<>();
	private List<String> mercancia = new ArrayList<>();
	
	
	public TiqueteDeluxe(String idTiquete, double precio, List<TiqueteSimple> tiquetes, List<String> beneficios,
			List<String> mercancia) {
		super(idTiquete, precio, tiquetes);
		this.beneficios = beneficios;
		this.mercancia = mercancia;
	}

	
	
}
