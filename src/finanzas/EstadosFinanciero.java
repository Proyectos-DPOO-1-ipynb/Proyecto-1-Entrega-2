package finanzas;

import java.util.HashMap;
import java.util.Map;

public class EstadosFinanciero {

    private double ingresosTotales;
    private double descuentosTotales;
    private double cargosServicioTotales;
    private double costosEmisionTotales;
    private double utilidadNeta;
    private int tiquetesVendidos;
    private int tiquetesDisponibles;
    private Map<String, Double> ingresosPorEvento = new HashMap<>();

    public void agregarVenta(String idEvento, double precioBase, double descuento, double cargoServicio, double costoEmision) {
        ingresosTotales += precioBase;
        descuentosTotales += descuento;
        cargosServicioTotales += cargoServicio;
        costosEmisionTotales += costoEmision;
        utilidadNeta = ingresosTotales - descuentosTotales - cargosServicioTotales - costosEmisionTotales;
        tiquetesVendidos++;
        ingresosPorEvento.put(idEvento, ingresosPorEvento.getOrDefault(idEvento, 0.0) + precioBase);
    }

    public void setTiquetesDisponibles(int disponibles) {
        this.tiquetesDisponibles = disponibles;
    }

    public double getPorcentajeVenta() {
        if (tiquetesDisponibles == 0) return 0;
        return (double) tiquetesVendidos / tiquetesDisponibles * 100;
    }

    @Override
    public String toString() {
        return "Ingresos totales: $" + ingresosTotales +
               "\nDescuentos totales: $" + descuentosTotales +
               "\nCargos de servicio: $" + cargosServicioTotales +
               "\nCostos de emisión: $" + costosEmisionTotales +
               "\nUtilidad neta: $" + utilidadNeta +
               "\nTiquetes vendidos: " + tiquetesVendidos +
               "\nPorcentaje de venta: " + String.format("%.2f", getPorcentajeVenta()) + "%" +
               "\nIngresos por evento: " + ingresosPorEvento;
    }
}