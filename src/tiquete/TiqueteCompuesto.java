package tiquete;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TiqueteCompuesto extends Tiquete {
	
	private List<TiqueteSimple> tiquetes = new ArrayList<>();

	public TiqueteCompuesto(String idTiquete, double precio, List<TiqueteSimple> tiquetes) {
		super(idTiquete, precio);
		this.tiquetes = tiquetes;
	}
	
	public void agregarTiqueteSimple(TiqueteSimple tiqueteSimple) {
	    if (tiqueteSimple != null) {
	        tiqueteSimple.agregarATiqueteCompuesto(this);
	        tiquetes.add(tiqueteSimple);
	    }
	}
	
	public boolean tieneComponenteVencido() {
	    // Un componente se considera vencido si su fecha ya pasó,
	    // o si es hoy y la hora del evento ya ocurrió.
	    LocalDate hoy = LocalDate.now();
	    int horaActual = LocalTime.now().getHour();
	    for (TiqueteSimple ts : tiquetes) {
	        LocalDate f = ts.getFecha();
	        int h = ts.getHora();
	        if (f.isBefore(hoy)) return true;
	        if (f.isEqual(hoy) && h <= horaActual) return true;
	    }
	    return false;
	}

}
	

