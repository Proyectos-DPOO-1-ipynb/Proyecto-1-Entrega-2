package tiquete;

public abstract class Tiquete {
	
	// TODO abstract? porque tiene constructor jaja 
	private String idTiquete;
	private double precio;
	
	public Tiquete(String idTiquete, double precio){
		
		this.idTiquete = idTiquete;
		this.precio = precio;
		
	}
}
