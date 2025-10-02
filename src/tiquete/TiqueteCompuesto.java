package tiquete;

import java.util.List;
import java.util.ArrayList;

public class TiqueteCompuesto extends Tiquete {
	
	
	private List<TiqueteSimple> Paquete = new ArrayList<>();
	
	public TiqueteCompuesto(String idTiquete, double precio) {
		
		super(idTiquete, precio);
		
		
		
	}
	
		
		
}
