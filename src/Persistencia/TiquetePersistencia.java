package Persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import evento.Evento;
import evento.venue.Localidad;
import tiquete.*;

public class TiquetePersistencia implements IPersistenciaTiquete{
	
	@Override
    public void cargar(String archivo, List<Tiquete> tiquetes) throws IOException, Exception {
        String jsonCompleto = new String(Files.readAllBytes(new File(archivo).toPath()));
        JSONArray jTiquetes = new JSONArray(jsonCompleto);

        for (int i = 0; i < jTiquetes.length(); i++) {
            JSONObject jT = jTiquetes.getJSONObject(i);
            String tipo = jT.getString("tipo");
            Tiquete tiquete = null;

            switch (tipo) {
                case "TiqueteSimple": {
                    tiquete = cargarTiqueteSimple(jT);
                    break;
                }
                case "TiqueteNumerado": {
                    tiquete = cargarTiqueteNumerado(jT);
                    break;
                }
                case "TiqueteDeluxe": {
                    tiquete = cargarTiqueteDeluxe(jT);
                    break;
                }
                case "TiqueteEntradaMultipleEvento": {
                    tiquete = cargarTiqueteMultipleEvento(jT);
                    break;
                }
                case "TiqueteEntradaMultipleLugar": {
                    tiquete = cargarTiqueteMultipleLugar(jT);
                    break;
                }
                default:
                    throw new Exception("Tipo de tiquete no reconocido: " + tipo);
            }

            if (tiquete != null)
                tiquetes.add(tiquete);
        }
    }

    private TiqueteSimple cargarTiqueteSimple(JSONObject jT) {
        String id = jT.getString("idTiquete");
        double precio = jT.getDouble("precio");
        LocalDate fecha = LocalDate.parse(jT.getString("fecha"));
        int hora = jT.getInt("hora");
        // El evento y la localidad pueden enlazarse externamente si se requiere
        return new TiqueteSimple(id, precio, fecha, hora, null, null);
    }

    private TiqueteNumerado cargarTiqueteNumerado(JSONObject jT) {
        String id = jT.getString("idTiquete");
        double precio = jT.getDouble("precio");
        LocalDate fecha = LocalDate.parse(jT.getString("fecha"));
        int hora = jT.getInt("hora");
        String asiento = jT.getString("idAsiento");
        return new TiqueteNumerado(id, precio, fecha, hora, null, null, asiento);
    }

    private TiqueteDeluxe cargarTiqueteDeluxe(JSONObject jT) {
        String id = jT.getString("idTiquete");
        double precio = jT.getDouble("precio");
        JSONArray jSimples = jT.getJSONArray("tiquetesSimples");
        List<TiqueteSimple> simples = new ArrayList<>();
        for (int k = 0; k < jSimples.length(); k++) {
            simples.add(cargarTiqueteSimple(jSimples.getJSONObject(k)));
        }
        List<String> beneficios = jT.getJSONArray("beneficios").toList().stream().map(Object::toString).toList();
        List<String> mercancia = jT.getJSONArray("mercancia").toList().stream().map(Object::toString).toList();

        return new TiqueteDeluxe(id, precio, simples, beneficios, mercancia);
    }

    private TiqueteEntradaMultipleEvento cargarTiqueteMultipleEvento(JSONObject jT) {
        String id = jT.getString("idTiquete");
        double precio = jT.getDouble("precio");
        JSONArray jSimples = jT.getJSONArray("tiquetesSimples");
        List<TiqueteSimple> simples = new ArrayList<>();
        for (int k = 0; k < jSimples.length(); k++) {
            simples.add(cargarTiqueteSimple(jSimples.getJSONObject(k)));
        }
        List<String> idEventos = jT.getJSONArray("idEventos").toList().stream().map(Object::toString).toList();
        int cantidadEventos = jT.getInt("cantidadEventos");
        return new TiqueteEntradaMultipleEvento(id, precio, simples, idEventos, cantidadEventos);
    }

    private TiqueteEntradaMultipleLugar cargarTiqueteMultipleLugar(JSONObject jT) {
        String id = jT.getString("idTiquete");
        double precio = jT.getDouble("precio");
        JSONArray jSimples = jT.getJSONArray("tiquetesSimples");
        List<TiqueteSimple> simples = new ArrayList<>();
        for (int k = 0; k < jSimples.length(); k++) {
            simples.add(cargarTiqueteSimple(jSimples.getJSONObject(k)));
        }
        return new TiqueteEntradaMultipleLugar(id, precio, simples, null);
    }

    @Override
    public void salvar(String archivo, List<Tiquete> tiquetes) throws IOException {
        JSONArray jTiquetes = new JSONArray();

        for (Tiquete t : tiquetes) {
            JSONObject jT = new JSONObject();
            jT.put("idTiquete", getId(t));
            jT.put("precio", getPrecio(t));
            jT.put("tipo", t.getClass().getSimpleName());

            if (t instanceof TiqueteSimple simple) {
                jT.put("fecha", simple.getFecha().toString());
                jT.put("hora", simple.getHora());
            }

            if (t instanceof TiqueteNumerado num) {
                jT.put("idAsiento", num.getIdAsiento());
            }

            if (t instanceof TiqueteCompuesto comp) {
                JSONArray jSimples = new JSONArray();
                for (TiqueteSimple s : comp.getTiquetes()) {
                    JSONObject js = new JSONObject();
                    js.put("idTiquete", getId(s));
                    js.put("precio", getPrecio(s));
                    js.put("fecha", s.getFecha().toString());
                    js.put("hora", s.getHora());
                    jSimples.put(js);
                }
                jT.put("tiquetesSimples", jSimples);
            }

            if (t instanceof TiqueteDeluxe delux) {
                jT.put("beneficios", new JSONArray(delux.getBeneficios()));
                jT.put("mercancia", new JSONArray(delux.getMercancia()));
            }

            if (t instanceof TiqueteEntradaMultipleEvento multiE) {
                jT.put("idEventos", new JSONArray(multiE.getIdEventos()));
                jT.put("cantidadEventos", multiE.getCantidadEventos());
            }

            if (t instanceof TiqueteEntradaMultipleLugar multiL) {
                if (multiL.getIdLugar() != null)
                    jT.put("idLugar", multiL.getIdLugar().getIdLocalidad());
            }

            jTiquetes.put(jT);
        }

        PrintWriter pw = new PrintWriter(archivo);
        jTiquetes.write(pw, 2, 0);
        pw.close();
    }

    // ===== Helpers =====
    private String getId(Tiquete t) {
        try {
            var field = Tiquete.class.getDeclaredField("idTiquete");
            field.setAccessible(true);
            return (String) field.get(t);
        } catch (Exception e) {
            return null;
        }
    }

    private double getPrecio(Tiquete t) {
        try {
            var field = Tiquete.class.getDeclaredField("precio");
            field.setAccessible(true);
            return (double) field.get(t);
        } catch (Exception e) {
            return 0;
        }
    }
}
