package evento.venue;

import java.time.LocalDate;

import evento.Evento;
import usuario.comprador.Organizador;

public class Descuento {

	private Organizador organizador;
	private Evento evento;
	private Localidad localidad;
	private double porcentaje;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private String estado;
	
	
	public Descuento(Organizador organizador, Evento evento, Localidad localidad, double porcentaje, LocalDate fechaInicio,
			LocalDate fechaFin) throws Exception {
		
		
		if (porcentaje <= 0) {
			
			throw new Exception("Se ingresó incorrectamente el descuento");
			
		}
		
		if(fechaInicio.isAfter(fechaFin)) {
			
			throw new Exception("Fechas mal ingresadas. Fecha de inicio es mayor a fecha de final");
		}
		
		if(localidad.verificadorAsociacionEvento(evento) == false) {
			
			throw new Exception("La localidad ingresada no corresponde al evento ingresado");
		}
		
		
		
		
		this.organizador = organizador;
		this.evento = evento;
		this.localidad = localidad;
		this.porcentaje = porcentaje;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;

		
	}
	
	public double getPorcentaje() {
		return porcentaje;
		
	}
	
}
