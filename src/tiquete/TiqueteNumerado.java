package tiquete;

public class TiqueteNumerado extends TiqueteSimple {
	
	
	private String idAsiento;
	
	TiqueteNumerado(String idTiquete, double precio, int fecha, int hora, String idAsiento){
		
		super(idTiquete, precio, fecha, hora);
		
		this.idAsiento = idAsiento;
		
	}
	
		
}
