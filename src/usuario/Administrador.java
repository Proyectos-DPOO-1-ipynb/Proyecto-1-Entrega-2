package usuario;

import java.util.List;

import evento.venue.Venue;
import usuario.comprador.Transaccion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import finanzas.EstadosFinanciero;
import finanzas.GestorFinanciero;

public class Administrador extends Usuario{
	
	private double cargoServicio;
	private int maxTiquetesTransaccion;
	private double costoEmisionFijo;
	private Map<String, Double> sobrecargosPorTipo;
	private String adminId;
	
	private List<Transaccion> transaccionesRealizadas;
	private List<Venue> venuesPorAprobar;
	
	private static List<String> adminIds = new ArrayList<String>();
	
	public Administrador(String nombre, String apellidos, LocalDate fechaNacimiento, String login, String password,
			String correo, double cargoServicio, int maxTiquetesTransaccion, double costoEmisionFijo,
			Map<String, Double> sobrecargosPorTipo, List<Transaccion> transaccionesRealizadas,
			List<Venue> venuesPorAprobar) throws Exception {
		
		super(nombre, apellidos, fechaNacimiento, login, password, correo);
		this.cargoServicio = cargoServicio;
		this.maxTiquetesTransaccion = maxTiquetesTransaccion;
		this.costoEmisionFijo = costoEmisionFijo;
		this.sobrecargosPorTipo = new HashMap<String, Double>();
		
		sobrecargosPorTipo.put("musical", 0.10);
        sobrecargosPorTipo.put("cultural", 0.1);
        sobrecargosPorTipo.put("deportivo", 0.1);
        sobrecargosPorTipo.put("religioso", 0.1);
		
		this.adminId = generarAdminId();
		
		this.transaccionesRealizadas = transaccionesRealizadas;
		this.venuesPorAprobar = venuesPorAprobar;
	}

	
	/*
	 * public double getSobrecargoSegunEvento(String tipoEvento) throws Exception {
	 * String clave = tipoEvento.trim().toLowerCase(); if
	 * (!sobrecargosPorTipo.containsKey(clave)) { throw new
	 * Exception("Tipo de evento no valido: "+ tipoEvento); } return
	 * sobrecargosPorTipo.get(clave); }
	 * 
	 * public void setSobrecargo(String tipoEvento, double porcentaje) { String
	 * clave = tipoEvento.trim().toLowerCase(); if
	 * (sobrecargosPorTipo.containsKey(clave)) sobrecargosPorTipo.put(clave,
	 * porcentaje); }
	 */
	 
	
	public String getAdminId() {
		return adminId;
	}
	
	public String generarAdminId() {
		String id;
        
        int numero = (int) (Math.random() * 10000000);
        id = String.format("%07d", numero);
        
        while (adminIds.contains("AD" + id)) {
        	
            int numeroe = (int) (Math.random() * 10000000);
            id = String.format("%07d", numeroe);
        }
        
        adminIds.add("AD" + id);
        return "AD" + id;
	}
	
	/**
     * Retorna un resumen financiero según el filtro aplicado.
     * 
     * @param tipoFiltro      "GLOBAL", "FECHA", "EVENTO" o "ORGANIZADOR"
     * @param palabraFiltro   valor con el que se desea filtrar (ej: idEvento o nombreOrganizador)
     * @return Map con los valores calculados del filtro aplicado
     */
	/*
	 * public Map<String, Object> generarEstadoFinanciero(String tipoFiltro, String
	 * palabraFiltro) { Map<String, Object> resultado = new HashMap<>();
	 * 
	 * switch (tipoFiltro.toUpperCase()) { case "GLOBAL": EstadosFinanciero global =
	 * GestorFinanciero.getEstadoGlobal(); resultado.put("tipo", "Global");
	 * resultado.put("ingresosTotales", global.getIngresosTotales());
	 * resultado.put("Ganancias tiquetera", global.getIngresosTiquetera()); break;
	 * 
	 * case "EVENTO": boolean encontradoEvento = false; for (EstadosFinanciero
	 * estado : GestorFinanciero.getTodosEstados().values()) { if
	 * (estado.getIngresosPorEventoAdmin().containsKey(palabraFiltro)) {
	 * resultado.put("tipo", "Por Evento"); resultado.put("idEvento",
	 * palabraFiltro); resultado.put("gananciaTiquetera",
	 * estado.getIngresosPorEventoAdmin().get(palabraFiltro)); encontradoEvento =
	 * true; }} if (!encontradoEvento) { resultado.put("mensaje",
	 * "No se encontraron registros para el evento: " + palabraFiltro); } break;
	 * 
	 * case "ORGANIZADOR": EstadosFinanciero org =
	 * GestorFinanciero.obtenerEstadoOrganizador(palabraFiltro);
	 * resultado.put("tipo", "Por Organizador"); resultado.put("organizador",
	 * palabraFiltro); resultado.put("ingresosTotales", org.getIngresosTotales());
	 * resultado.put("Ganancias tiquetera", org.getIngresosTiquetera()); break;
	 * 
	 * case "FECHA": try { LocalDate fechaFiltro = LocalDate.parse(palabraFiltro);
	 * // formato "YYYY-MM-DD" boolean encontradoFecha = false; for
	 * (EstadosFinanciero estado : GestorFinanciero.getTodosEstados().values()) { if
	 * (estado.getIngresosPorFechaAdmin().containsKey(fechaFiltro)) {
	 * resultado.put("tipo", "Por Fecha"); resultado.put("idEvento", palabraFiltro);
	 * resultado.put("ingresos",
	 * estado.getIngresosPorFechaAdmin().get(fechaFiltro)); } } if
	 * (!encontradoFecha) { resultado.put("mensaje",
	 * "No hay registros de ventas para la fecha: " + fechaFiltro); }
	 * 
	 * } catch (Exception e) { resultado.put("error",
	 * "Formato de fecha inválido. Usa: YYYY-MM-DD"); } break;
	 * 
	 * default: resultado.put("error",
	 * "Tipo de filtro no válido. Usa GLOBAL, EVENTO, ORGANIZADOR o FECHA."); break;
	 * }
	 * 
	 * return resultado; }
	 */
	
    public double getCargoServicio() { 
    	return this.cargoServicio; 
    }   
    
    public int getMaxTiquetesTransaccion() { 
    	return this.maxTiquetesTransaccion; 
    }

	public double getCostoEmisionFijo() {
		return costoEmisionFijo;
	}

	public Map<String, Double> getSobrecargosPorTipo() {
		return sobrecargosPorTipo;
	}

	public List<Transaccion> getTransaccionesRealizadas() {
		return transaccionesRealizadas;
	}

	public List<Venue> getVenuesPorAprobar() {
		return venuesPorAprobar;
	}

	public static List<String> getAdminIds() {
		return adminIds;
	} 
    
}
