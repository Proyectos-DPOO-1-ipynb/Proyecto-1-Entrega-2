package usuario.comprador;

import java.util.List;

import tiquete.Tiquete;

public class Transaccion {

	private int fecha;
	private String tipoPago;
	private double montoTotal;
	
	private Comprador usuario;
	private List<Tiquete> tiquetesComprados;
	
	
}
