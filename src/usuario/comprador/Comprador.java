package usuario.comprador;

import java.util.List;

import tiquete.Tiquete;
import usuario.Usuario;

public class Comprador extends Usuario {
	
	private int saldoVirtual = 0;
	
	private List<Transaccion> transaccionesRealizadas;
	private List<Tiquete> historicoTiquetes;
	
	
}
