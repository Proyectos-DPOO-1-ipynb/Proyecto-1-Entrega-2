package tiquete;

import java.util.ArrayList;
import java.util.List;

public class TiqueteDeluxe extends TiqueteCompuesto{

	private List<String> beneficios;
	private List<String> mercancia;
	
	
	public TiqueteDeluxe(String idTiquete, double precio, List<TiqueteSimple> tiquetes, List<String> beneficios,
			List<String> mercancia) {
		super(idTiquete, precio, tiquetes);
		this.beneficios = beneficios;
		this.mercancia = mercancia;
	}

	public boolean esTransferible() {
	    return false; // Bloqueo de transferencia para paquetes Deluxe
	}
	
}
