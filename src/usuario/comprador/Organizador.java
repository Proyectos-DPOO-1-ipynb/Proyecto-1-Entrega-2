package usuario.comprador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import evento.Evento;
import evento.venue.Venue;
import finanzas.EstadosFinanciero;
import finanzas.GestorFinanciero;

public class Organizador extends Comprador {
	private String idOrganizador;
	private Venue venueAsociado;
    private List<Evento> eventosCreados = new ArrayList<>();

    public List<Evento> getEventosCreados() {
        return eventosCreados;
    }
    
    public String getIdentificacion() {
        return idOrganizador;
    }
    public void setIdentificacion(String idOrganizador) {
        this.idOrganizador = idOrganizador;
    }
    public Venue getVenueAsociado() {
        return venueAsociado;
    }
    public void setVenueAsociado(Venue venueAsociado) {
        this.venueAsociado = venueAsociado;
    }
    
 // --- Métodos financieros ---
    /**
     * Permite al organizador consultar sus propios estados financieros.
     * 
     * @param tipoFiltro "GLOBAL", "EVENTO" o "LOCALIDAD"
     * @param palabraFiltro valor asociado al filtro (ej: idEvento o idLocalidad)
     * @return Map con la información financiera filtrada
     */
    public Map<String, Object> generarEstadoFinanciero(String tipoFiltro, String palabraFiltro) {
        Map<String, Object> resultado = new HashMap<>();

        EstadosFinanciero miEstado = GestorFinanciero.obtenerEstadoOrganizador(idOrganizador);

        switch (tipoFiltro.toUpperCase()) {
            case "GLOBAL":
                resultado.put("tipo", "Global del Organizador");
                resultado.put("organizador", idOrganizador);
                resultado.put("ingresosTotales", miEstado.getIngresosTotales());
                resultado.put("utilidadNeta", miEstado.getUtilidadNeta());
                resultado.put("porcentajeVenta", miEstado.getPorcentajeVenta());
                break;

            case "EVENTO":
                if (miEstado.getIngresosPorEvento().containsKey(palabraFiltro)) {
                    resultado.put("tipo", "Por Evento");
                    resultado.put("evento", palabraFiltro);
                    resultado.put("ingresos", miEstado.getIngresosPorEvento().get(palabraFiltro));
                } else {
                    resultado.put("mensaje", "No hay datos para ese evento.");
                }
                break;

            case "LOCALIDAD":
                resultado.put("mensaje", "Filtro por localidad aún no implementado (depende de estructura de evento/localidad).");
                break;

            default:
                resultado.put("error", "Tipo de filtro no válido. Usa GLOBAL, EVENTO o LOCALIDAD.");
                break;
        }

        return resultado;
    }
}
