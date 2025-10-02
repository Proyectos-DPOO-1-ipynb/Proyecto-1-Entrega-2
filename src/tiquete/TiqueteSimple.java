package tiquete;

public class TiqueteSimple extends Tiquete {
	
	

	private int fecha; // En formato YYYYMMDD
	private int hora; // En formato HHMMSS sin espacios todo en enteros
	
	public TiqueteSimple(String idTiquete, double precio, int fecha, int hora) {
		
		super(idTiquete, precio);
		
		this.fecha = fecha;
		this.hora = hora;
		
	}
	

}
