package usuario;

import java.util.List;

import evento.venue.Venue;
import usuario.comprador.Transaccion;
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
                resultado.put("utilidadNeta", global.getUtilidadNeta());
                resultado.put("porcentajeVenta", global.getPorcentajeVenta());
                break;

            case "EVENTO":
                // Buscar el evento en todos los organizadores
                for (EstadosFinanciero estado : GestorFinanciero.getTodosEstados().values()) {
                    if (estado.getIngresosPorEvento().containsKey(palabraFiltro)) {
                        resultado.put("tipo", "Por Evento");
                        resultado.put("idEvento", palabraFiltro);
                        resultado.put("ingresos", estado.getIngresosPorEvento().get(palabraFiltro));
                    }
                }
                break;

            case "ORGANIZADOR":
                EstadosFinanciero org = GestorFinanciero.obtenerEstadoOrganizador(palabraFiltro);
                resultado.put("tipo", "Por Organizador");
                resultado.put("organizador", palabraFiltro);
                resultado.put("ingresosTotales", org.getIngresosTotales());
                resultado.put("utilidadNeta", org.getUtilidadNeta());
                resultado.put("porcentajeVenta", org.getPorcentajeVenta());
                break;

            case "FECHA":
                // Este caso requiere que más adelante registres fechas en GestorFinanciero o Transacción
                resultado.put("mensaje", "Filtro por fecha aún no implementado (requiere registro de fechas por venta).");
                break;

            default:
                resultado.put("error", "Tipo de filtro no válido. Usa GLOBAL, EVENTO, ORGANIZADOR o FECHA.");
                break;
        }

        return resultado;
    }
	
	
	
	

}
