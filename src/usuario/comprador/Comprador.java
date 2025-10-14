package usuario.comprador;

import java.util.List;

import evento.Evento;
import tiquete.Tiquete;
import usuario.Usuario;

public class Comprador extends Usuario {
	
	private int saldoVirtual = 0;
	
	private List<Transaccion> transaccionesRealizadas;
	private List<Tiquete> historicoTiquetes;
	
	public Compra comprarTiquetes(Evento evento) {
		return null;
	}
	
}
