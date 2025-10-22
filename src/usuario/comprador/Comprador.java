package usuario.comprador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import evento.Evento;
import tiquete.Tiquete;
import usuario.Usuario;

public class Comprador extends Usuario {
	
	private int saldoVirtual;
	
	private List<Transaccion> transaccionesRealizadas;
	private List<Tiquete> historicoTiquetes;
	
	public Comprador(String nombre, String apellidos, LocalDate fechaNacimiento, String login, String password,
			String correo) throws Exception {
		super(nombre, apellidos, fechaNacimiento, login, password, correo);
		// TODO Auto-generated constructor stub
		
		this.saldoVirtual = 0;
		this.transaccionesRealizadas = new ArrayList<Transaccion>();
		this.historicoTiquetes = new ArrayList<Tiquete>();
	}
	
	
}
