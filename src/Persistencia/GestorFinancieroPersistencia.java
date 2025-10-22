package Persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Map;

import org.json.JSONObject;

import finanzas.EstadosFinanciero;
import finanzas.GestorFinanciero;

public class GestorFinancieroPersistencia {

    private static final String GLOBAL = "estadoGlobal";
    private static final String ORGANIZADORES = "estadosOrganizadores";

    /**
     * Guarda todos los estados financieros (global y por organizador) en un archivo JSON.
     * 
     * @param archivo Ruta del archivo donde se almacenará la información
     * @throws IOException si hay un problema escribiendo el archivo
     */
    public static void guardarEstadosFinancieros(String archivo) throws IOException {
        JSONObject raiz = new JSONObject();

        // 🔹 Estado global (Administrador)
        EstadosFinanciero global = GestorFinanciero.getEstadoGlobal();
        raiz.put(GLOBAL, convertirEstadoAJSON(global));

        // 🔹 Estados financieros por organizador
        JSONObject jOrganizadores = new JSONObject();
        for (Map.Entry<String, EstadosFinanciero> entry : GestorFinanciero.getTodosEstados().entrySet()) {
            jOrganizadores.put(entry.getKey(), convertirEstadoAJSON(entry.getValue()));
        }
        raiz.put(ORGANIZADORES, jOrganizadores);

        // 🔹 Escribir el archivo en formato legible
        try (PrintWriter pw = new PrintWriter(archivo)) {
            raiz.write(pw, 2, 0);
        }
    }

    /**
     * Carga y muestra los estados financieros desde un archivo JSON guardado.
     * (Puedes agregar una reconstrucción real después si lo deseas)
     */
    public static void cargarEstadosFinancieros(String archivo) throws IOException {
        File f = new File(archivo);
        if (!f.exists()) throw new IOException("El archivo no existe: " + archivo);

        String jsonCompleto = new String(Files.readAllBytes(f.toPath()));
        JSONObject raiz = new JSONObject(jsonCompleto);

        // Estado global
        JSONObject jGlobal = raiz.getJSONObject(GLOBAL);
        System.out.println("✅ Estado financiero global cargado:");
        System.out.println(jGlobal.toString(2));

        // Estados por organizador
        JSONObject jOrganizadores = raiz.getJSONObject(ORGANIZADORES);
        for (String nombreOrg : jOrganizadores.keySet()) {
            JSONObject jEstado = jOrganizadores.getJSONObject(nombreOrg);
            System.out.println("✅ Estado financiero del organizador " + nombreOrg + ":");
            System.out.println(jEstado.toString(2));
        }
    }

    /**
     * Convierte un estado financiero en un objeto JSON.
     */
    private static JSONObject convertirEstadoAJSON(EstadosFinanciero estado) {
        JSONObject json = new JSONObject();

        json.put("ingresosTotales", estado.getIngresosTotales());
        json.put("ingresosTiquetera", estado.getIngresosTiquetera());
        json.put("utilidadNeta", estado.getUtilidadNeta());

        // 🔸 Ingresos por evento del organizador
        JSONObject jEventosOrg = new JSONObject();
        for (Map.Entry<String, Double> e : estado.getIngresosPorEventoOrganizador().entrySet()) {
            jEventosOrg.put(e.getKey(), e.getValue());
        }
        json.put("ingresosPorEventoOrganizador", jEventosOrg);

        // 🔸 Ingresos por fecha del organizador
        JSONObject jFechasOrg = new JSONObject();
        for (Map.Entry<LocalDate, Double> f : estado.getIngresosPorFechaOrganizador().entrySet()) {
            jFechasOrg.put(f.getKey().toString(), f.getValue());
        }
        json.put("ingresosPorFechaOrganizador", jFechasOrg);

        // 🔸 Ingresos por evento del administrador (tiquetera)
        JSONObject jEventosAdmin = new JSONObject();
        for (Map.Entry<String, Double> e : estado.getIngresosPorEventoAdmin().entrySet()) {
            jEventosAdmin.put(e.getKey(), e.getValue());
        }
        json.put("ingresosPorEventoAdmin", jEventosAdmin);

        // 🔸 Ingresos por fecha del administrador (tiquetera)
        JSONObject jFechasAdmin = new JSONObject();
        for (Map.Entry<LocalDate, Double> f : estado.getIngresosPorFechaAdmin().entrySet()) {
            jFechasAdmin.put(f.getKey().toString(), f.getValue());
        }
        json.put("ingresosPorFechaAdmin", jFechasAdmin);

        return json;
    }

    /**
     * Verifica si ya existe un archivo con estados financieros guardados.
     */
    public static boolean existeArchivo(String archivo) {
        return new File(archivo).exists();
    }
}

