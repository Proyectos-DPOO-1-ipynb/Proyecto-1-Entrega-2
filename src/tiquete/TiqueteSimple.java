package tiquete;

import java.time.LocalDate;
import java.time.LocalDateTime;

import evento.Evento;
import evento.venue.Localidad;
import tiquete.TiqueteCompuesto;

public class TiqueteSimple extends Tiquete {
	
	
	private LocalDate fecha;
	private int hora;
	
	private Evento eventoAsociado;
	private Localidad localidadTiquete;
	
	private TiqueteCompuesto paqueteAsociado;

	
	public TiqueteSimple(String idTiquete, double precio, LocalDate fecha, int hora, Evento eventoAsociado,
			Localidad localidadTiquete) {
		
		super(idTiquete, precio);
		this.fecha = fecha;
		this.hora = hora;
		this.eventoAsociado = eventoAsociado;
		this.localidadTiquete = localidadTiquete;
	}
	
	public TiqueteCompuesto getPaqueteAsociado() {
	    return this.paqueteAsociado;
	}
	
	public void agregarATiqueteCompuesto(TiqueteCompuesto paqueteAsociado) {
	    this.paqueteAsociado = paqueteAsociado;
	}
	
	
}
