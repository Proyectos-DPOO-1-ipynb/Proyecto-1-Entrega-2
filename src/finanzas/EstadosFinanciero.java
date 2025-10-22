package finanzas;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Representa los estados financieros de un organizador o del sistema global.
 */
public class EstadosFinanciero {

    private double ingresosTotales;         // Lo que entra al organizador (sin recargos)
    private double ingresosTiquetera;       // Lo que gana la plataforma por sobrecargos
    private double ingresosTotalesCompletos; // Precio total pagado (con recargos)
    private double utilidadNeta;            // Ganancia neta del organizador o del sistema
    private int tiquetesVendidos;
    private int tiquetesDisponibles;
 // 🔹 Mapas para organizador
    private Map<String, Double> ingresosPorEventoOrganizador = new HashMap<>();
    private Map<LocalDate, Double> ingresosPorFechaOrganizador = new HashMap<>();

    // 🔹 Mapas para administrador
    private Map<String, Double> ingresosPorEventoAdmin = new HashMap<>();
    private Map<LocalDate, Double> ingresosPorFechaAdmin = new HashMap<>();

    /**
     * Registra una venta (llamado desde GestorFinanciero).
     * 
     * @param idEvento identificador del evento vendido
     * @param precioTotal precio total pagado por el usuario (con recargos)
     * @param precioSinRecargos precio base sin recargos (para el organizador)
     * @param gananciaTiquetera diferencia que gana la plataforma
     */
    public void agregarVenta(String idEvento, double precioTotal, double precioSinRecargos, double gananciaTiquetera, LocalDate fecha) {
        ingresosTotales += precioSinRecargos;
        ingresosTiquetera += gananciaTiquetera;
        ingresosTotalesCompletos += precioTotal;
        utilidadNeta = ingresosTotales - ingresosTiquetera; // quitar? es lo mismo que precioSinRecargos
        tiquetesVendidos++;
        
     // --- Para el organizador ---
        ingresosPorEventoOrganizador.put(
            idEvento,
            ingresosPorEventoOrganizador.getOrDefault(idEvento, 0.0) + precioSinRecargos
        );
        ingresosPorFechaOrganizador.put(
            fecha,
            ingresosPorFechaOrganizador.getOrDefault(fecha, 0.0) + precioSinRecargos
        );

        // --- Para el administrador (tiquetera) ---
        ingresosPorEventoAdmin.put(
            idEvento,
            ingresosPorEventoAdmin.getOrDefault(idEvento, 0.0) + gananciaTiquetera
        );
        ingresosPorFechaAdmin.put(
            fecha,
            ingresosPorFechaAdmin.getOrDefault(fecha, 0.0) + gananciaTiquetera
        );
    }

    public void setTiquetesDisponibles(int disponibles) {
        this.tiquetesDisponibles = disponibles;
    }

    public double getPorcentajeVenta() {
        if (tiquetesDisponibles == 0) return 0;
        return (double) tiquetesVendidos / tiquetesDisponibles * 100;
    }

    // --- Getters ---

    public double getIngresosTotales() {
        return ingresosTotales;
    }

    public double getIngresosTiquetera() {
        return ingresosTiquetera;
    }

    public double getIngresosTotalesCompletos() {
        return ingresosTotalesCompletos;
    }

    public double getUtilidadNeta() {
        return utilidadNeta;
    }

    public int getTiquetesVendidos() {
        return tiquetesVendidos;
    }

    public int getTiquetesDisponibles() {
        return tiquetesDisponibles;
    }

    public Map<String, Double> getIngresosPorEventoOrganizador() {
        return ingresosPorEventoOrganizador;
    }

    public Map<LocalDate, Double> getIngresosPorFechaOrganizador() {
        return ingresosPorFechaOrganizador;
    }

    // --- Getters para filtrado por administrador ---
    public Map<String, Double> getIngresosPorEventoAdmin() {
        return ingresosPorEventoAdmin;
    }

    public Map<LocalDate, Double> getIngresosPorFechaAdmin() {
        return ingresosPorFechaAdmin;
        
    }
    
}