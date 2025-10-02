package tiquete;

import java.util.List;
import java.util.ArrayList;

public class TiqueteCompuesto extends Tiquete {
	
	private List<TiqueteSimple> tiquetes = new ArrayList<>();

	public TiqueteCompuesto(String idTiquete, double precio, List<TiqueteSimple> tiquetes) {
		super(idTiquete, precio);
		this.tiquetes = tiquetes;
	}

}
	

