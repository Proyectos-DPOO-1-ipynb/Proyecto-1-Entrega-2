package usuario;

import java.util.List;

import evento.venue.Venue;
import usuario.comprador.Transaccion;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import finanzas.EstadosFinanciero;
import finanzas.GestorFinanciero;

public class Administrador {
	
	private double cargoServicio;
	private int maxTiquetesTransaccion;
	// TODO sobrecargo ???\
	private double costoEmisionFijo;
	private Map<String, Double> sobrecargosPorTipo = new HashMap<>();
	private String adminId;
	
	private List<Transaccion> transaccionesRealizadas;
	private List<Venue> venuesPorAprobar;
	
	public Administrador(String adminId, double costoEmisionFijo) {
        this.adminId = adminId;
        this.costoEmisionFijo = costoEmisionFijo;

        // valores iniciales por defecto
        sobrecargosPorTipo.put("musical", 0.10);
        sobrecargosPorTipo.put("cultural", 0.1);
        sobrecargosPorTipo.put("deportivo", 0.1);
        sobrecargosPorTipo.put("religioso", 0.1);
    }
	
	public double getSobrecargoSegunEvento(String tipoEvento) throws Exception {
		String clave = tipoEvento.trim().toLowerCase();
        if (!sobrecargosPorTipo.containsKey(clave)) {
        	throw new Exception("Tipo de evento no valido: "+ tipoEvento);
        };
        return sobrecargosPorTipo.get(clave);
    }
	
	public void setSobrecargo(String tipoEvento, double porcentaje) {
		String clave = tipoEvento.trim().toLowerCase();
        if (sobrecargosPorTipo.containsKey(clave))
            sobrecargosPorTipo.put(clave, porcentaje);
    }
	
	public String getAdminId() {
		return adminId;
	}
	
	
	/**
     * Retorna un resumen financiero según el filtro aplicado.
     * 
     * @param tipoFiltro      "GLOBAL", "FECHA", "EVENTO" o "ORGANIZADOR"
     * @param palabraFiltro   valor con el que se desea filtrar (ej: idEvento o nombreOrganizador)
     * @return Map con los valores calculados del filtro aplicado
     */
    public Map<String, Object> generarEstadoFinanciero(String tipoFiltro, String palabraFiltro) {
        Map<String, Object> resultado = new HashMap<>();

        switch (tipoFiltro.toUpperCase()) {
            case "GLOBAL":
                EstadosFinanciero global = GestorFinanciero.getEstadoGlobal();
                resultado.put("tipo", "Global");
                resultado.put("ingresosTotales", global.getIngresosTotales());
                resultado.put("Ganancias tiquetera", global.getIngresosTiquetera());
                break;

            case "EVENTO":
            	boolean encontradoEvento = false;
                for (EstadosFinanciero estado : GestorFinanciero.getTodosEstados().values()) {
                    if (estado.getIngresosPorEventoAdmin().containsKey(palabraFiltro)) {
                        resultado.put("tipo", "Por Evento");
                        resultado.put("idEvento", palabraFiltro);
                        resultado.put("gananciaTiquetera", estado.getIngresosPorEventoAdmin().get(palabraFiltro));
                        encontradoEvento = true;
                    }}
                    if (!encontradoEvento) {
                        resultado.put("mensaje", "No se encontraron registros para el evento: " + palabraFiltro);
                    }
                break;

            case "ORGANIZADOR":
                EstadosFinanciero org = GestorFinanciero.obtenerEstadoOrganizador(palabraFiltro);
                resultado.put("tipo", "Por Organizador");
                resultado.put("organizador", palabraFiltro);
                resultado.put("ingresosTotales", org.getIngresosTotales());
                resultado.put("Ganancias tiquetera", org.getIngresosTiquetera());
                break;

            case "FECHA":
            	try {
                    LocalDate fechaFiltro = LocalDate.parse(palabraFiltro); // formato "YYYY-MM-DD"
                    boolean encontradoFecha = false;
            	for (EstadosFinanciero estado : GestorFinanciero.getTodosEstados().values()) {
            		if (estado.getIngresosPorFechaAdmin().containsKey(fechaFiltro)) {
                        resultado.put("tipo", "Por Fecha");
                        resultado.put("idEvento", palabraFiltro);
                        resultado.put("ingresos", estado.getIngresosPorFechaAdmin().get(fechaFiltro));
                    }
            	}
            	if (!encontradoFecha) {
                    resultado.put("mensaje", "No hay registros de ventas para la fecha: " + fechaFiltro);
                }

            } catch (Exception e) {
                resultado.put("error", "Formato de fecha inválido. Usa: YYYY-MM-DD");
            }
                break;

            default:
                resultado.put("error", "Tipo de filtro no válido. Usa GLOBAL, EVENTO, ORGANIZADOR o FECHA.");
                break;
        }

        return resultado;
    }
	
}
