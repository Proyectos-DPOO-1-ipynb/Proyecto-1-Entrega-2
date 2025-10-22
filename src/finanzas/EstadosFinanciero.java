package finanzas;

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

    private Map<String, Double> ingresosPorEvento = new HashMap<>();

    /**
     * Registra una venta (llamado desde GestorFinanciero).
     * 
     * @param idEvento identificador del evento vendido
     * @param precioTotal precio total pagado por el usuario (con recargos)
     * @param precioSinRecargos precio base sin recargos (para el organizador)
     * @param gananciaTiquetera diferencia que gana la plataforma
     */
    public void agregarVenta(String idEvento, double precioTotal, double precioSinRecargos, double gananciaTiquetera) {
        ingresosTotales += precioSinRecargos;
        ingresosTiquetera += gananciaTiquetera;
        ingresosTotalesCompletos += precioTotal;
        utilidadNeta = ingresosTotales - ingresosTiquetera; // simplificado, puedes ajustar la fórmula

        tiquetesVendidos++;
        ingresosPorEvento.put(idEvento,
            ingresosPorEvento.getOrDefault(idEvento, 0.0) + precioSinRecargos);
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

    public Map<String, Double> getIngresosPorEvento() {
        return ingresosPorEvento;
    }
}