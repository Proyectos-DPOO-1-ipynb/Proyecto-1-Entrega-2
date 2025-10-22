package usuario.comprador;

import java.time.LocalDate;
import java.util.List;

import evento.Evento;

public class Cliente extends Comprador {

	public Cliente(String nombre, String apellidos, LocalDate fechaNacimiento, String login, String password,
			String correo) throws Exception {
		super(nombre, apellidos, fechaNacimiento, login, password, correo);
	}
	
}
