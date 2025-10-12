package evento.venue;

import java.time.LocalDate;

public class Descuento {
	
	private double porcentaje;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	
	private Localidad localidadAsociada;

	public Descuento(double porcentaje, LocalDate fechaInicio, LocalDate fechaFin, Localidad localidadAsociada) {
		this.porcentaje = porcentaje;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.localidadAsociada = localidadAsociada;
	}

	public double getPorcentaje() {
		return porcentaje;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public Localidad getLocalidadAsociada() {
		return localidadAsociada;
	}
	
	
	
}
