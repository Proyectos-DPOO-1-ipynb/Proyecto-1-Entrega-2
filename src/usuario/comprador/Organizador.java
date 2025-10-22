package usuario.comprador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import evento.Evento;
import evento.venue.Venue;
import finanzas.EstadosFinanciero;
import finanzas.GestorFinanciero;

public class Organizador extends Comprador {

	private String idOrganizador;
    private List<Evento> eventosCreados;
    
    private static List<String> idsOrganizadores = new ArrayList<String>();

    public Organizador(String nombre, String apellidos, LocalDate fechaNacimiento, String login, String password,
			String correo) throws Exception {
		super(nombre, apellidos, fechaNacimiento, login, password, correo);
		
		this.eventosCreados = new ArrayList<Evento>();
		this.idOrganizador = generarIdOrganizador();
		
	}
    
    private String generarIdOrganizador() {
    	String id;
        
        int numero = (int) (Math.random() * 10000000);
        id = String.format("%07d", numero);
        
        while (idsOrganizadores.contains("ORG" + id)) {
        	
            int numeroe = (int) (Math.random() * 10000000);
            id = String.format("%07d", numeroe);
        }
        
        idsOrganizadores.add("ORG" + id);
        return "ORG" + id;
    }
    
    public List<Evento> getEventosCreados() {
        return eventosCreados;
    }
    
    public String getIdentificacion() {
        return idOrganizador;
    }
    public void setIdentificacion(String idOrganizador) {
        this.idOrganizador = idOrganizador;
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
                if (miEstado.getIngresosPorEventoOrganizador().containsKey(palabraFiltro)) {
                    resultado.put("tipo", "Por Evento");
                    resultado.put("evento", palabraFiltro);
                    resultado.put("ingresos", miEstado.getIngresosPorEventoOrganizador().get(palabraFiltro));
                } else {
                    resultado.put("mensaje", "No hay datos para ese evento.");
                }
                break;
            case "FECHA":
                try {
                    LocalDate fechaFiltro =LocalDate.parse(palabraFiltro); // formato "YYYY-MM-DD"
                    if (miEstado.getIngresosPorFechaOrganizador().containsKey(fechaFiltro)) {
                        resultado.put("tipo", "Por Fecha");
                        resultado.put("fecha", fechaFiltro.toString());
                        resultado.put("ingresos", miEstado.getIngresosPorFechaOrganizador().get(fechaFiltro));
                    } else {
                        resultado.put("mensaje", "No se registraron ingresos para la fecha: " + fechaFiltro);
                    }
                } catch (Exception e) {
                    resultado.put("error", "Formato de fecha inválido. Usa: YYYY-MM-DD");
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
