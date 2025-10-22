package usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private String nombre;
	private String apellidos;
	private LocalDate fechaNacimiento;
	private String login;
	private String password;
	private String correo;
	
	private static List<String> loginsRegistrados = new ArrayList<String>();
	private static List<String> correosRegistrados = new ArrayList<String>();
	
	public Usuario(String nombre, String apellidos, LocalDate fechaNacimiento, String login, String password,
			String correo) throws Exception {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		
		if (loginsRegistrados.contains(login)) {
			throw new Exception("El login ingresado ya está en uso");
		} else {
			this.login = login;
		}
		
		this.password = password;
		
		if (correosRegistrados.contains(correo)) {
			throw new Exception ("El correo ingresado ya se encuentra en uso");
		} else {
			this.correo = correo;
		} 
		
	}


	public String getNombre() {
		return nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	public String getLogin() {
		return login;
	}


	public String getPassword() {
		return password;
	}


	public String getCorreo() {
		return correo;
	}
	
	
	
}
