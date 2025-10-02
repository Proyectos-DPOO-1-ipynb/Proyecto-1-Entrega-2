package tiquete;

public abstract class Tiquete {
	
	
	private String idTiquete;
	private double precio;
	
	Tiquete(String idTiquete, double precio){
		
		this.idTiquete = idTiquete;
		this.precio = precio;
		
	}
}
