package usuario.comprador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

	public int getSaldoVirtual() {
		return saldoVirtual;
	}

	public List<Transaccion> getTransaccionesRealizadas() {
		return transaccionesRealizadas;
	}

	public List<Tiquete> getHistoricoTiquetes() {
		return historicoTiquetes;
	}
	
	public void abonarSaldo(int monto) {     
	    if (monto < 0) throw new IllegalArgumentException("Monto negativo");
	    this.saldoVirtual += monto;
	}
	
	public void debitarSaldo(int monto) {          
	    if (monto < 0) throw new IllegalArgumentException("Monto negativo");
	    if (this.saldoVirtual < monto) throw new IllegalStateException("Saldo insuficiente");
	    this.saldoVirtual -= monto;
	}
	
	public void agregarTransaccion(Transaccion t) { 
	    if (t == null) return;
	    this.transaccionesRealizadas.add(t);
	}
	
	public void agregarTiquete(Tiquete t) {
	    if (t == null) return;
	    this.historicoTiquetes.add(t);
	}
}
