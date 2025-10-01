package evento.venue;

import java.util.List;

import evento.Evento;

public class Venue {

	private String direccion;
	private int maxCapacidad;
	private List<String> restricciones;
	private boolean estado;
	
	private List<Evento> eventosAsociados;
	private List<Localidad> localidadesDiseñadas;
	
}
