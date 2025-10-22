package finanzas;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import usuario.comprador.Transaccion;
import evento.Evento;
import tiquete.Tiquete;

/**
 * Controlador global del sistema financiero.
 */
public class GestorFinanciero {

    private static EstadosFinanciero estadoGlobal = new EstadosFinanciero();
    private static Map<String, EstadosFinanciero> estadosOrganizadores = new HashMap<>();

    public static EstadosFinanciero getEstadoGlobal() {
        return estadoGlobal;
    }

    public static Map<String, EstadosFinanciero> getTodosEstados() {
        return estadosOrganizadores;
    }

    /**
     * Registra automáticamente cada transacción en los estados financieros.
     */
    public static void registrarTransaccion(Transaccion transaccion) {
        Evento evento = transaccion.getEvento();
        if (evento == null || evento.getIdEvento() == null) return;

        String idOrganizador = evento.getOrganizadorAsignado().getIdentificacion(); 
        double precioTotal = transaccion.getMontoTotal();
        double precioSinRecargosTotal = 0; // Para organizadorrr
        
        List<Tiquete> listaTiquetes = transaccion.getTiquetesComprados();
        for (Tiquete tiquete : listaTiquetes) {
            precioSinRecargosTotal += tiquete.getPrecio();  // Para organizadorrr
        }	
        
        double gananciasTiquetera = precioTotal - precioSinRecargosTotal; // Para el Adminnnn
        
        LocalDate fechaCompra = transaccion.getFecha();
        
        // Registrar en estado global
        estadoGlobal.agregarVenta(evento.getIdEvento(), precioTotal, precioSinRecargosTotal, gananciasTiquetera, fechaCompra);

        // Registrar por organizador
        EstadosFinanciero estadoOrg = estadosOrganizadores.computeIfAbsent(idOrganizador, k -> new EstadosFinanciero());
        estadoOrg.agregarVenta(evento.getIdEvento(), precioTotal, precioSinRecargosTotal, gananciasTiquetera,fechaCompra);
    }

    /**
     * Permite consultar el estado financiero de un organizador en particular.
     */
    public static EstadosFinanciero obtenerEstadoOrganizador(String idOrganizador) {
        return estadosOrganizadores.getOrDefault(idOrganizador, new EstadosFinanciero());
    }
}
