package tiquete;

import java.util.List;
import java.util.ArrayList;

public class TiqueteCompuesto extends Tiquete {
	

	private List<TiqueteSimple> tiquetes = new ArrayList<>();
	
	
	TiqueteCompuesto(String idTiquete, double precio) {
		super(idTiquete, precio);
	}


	public void addTiqueteSimple(Tiquete TiqueteSimple) {
		
		tiquetes.add((tiquete.TiqueteSimple) TiqueteSimple);
		
		
		
	}
	
	
	
		
	}
	
		
		
	
