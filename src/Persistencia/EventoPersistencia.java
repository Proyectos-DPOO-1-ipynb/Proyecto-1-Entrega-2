package Persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.*;
import org.json.JSONArray;
import org.json.JSONObject;
import usuario.comprador.Organizador;


import evento.Evento;
import evento.venue.Venue;
import evento.venue.Localidad;
import evento.venue.LocalidadBasica;
import evento.venue.LocalidadNumerada;
import evento.venue.Descuento;

public class EventoPersistencia implements IPersistenciaEvento{
	@Override
    public void cargar(String archivo, List<Evento> eventos) throws IOException, Exception {
        String jsonCompleto = new String(Files.readAllBytes(new File(archivo).toPath()));
        JSONArray jEventos = new JSONArray(jsonCompleto);

        for (int i = 0; i < jEventos.length(); i++) {
            JSONObject jEvento = jEventos.getJSONObject(i);

            // ===== Cargar Organizador =====
            JSONObject jOrg = jEvento.getJSONObject("organizador");
            Organizador organizador = new Organizador(
                jOrg.getString("nombre"),
                jOrg.getString("apellidos"),
                jOrg.getString("login"),
                jOrg.getString("password"),
                jOrg.getString("correo"),
                jOrg.optString("idOrganizador", "")
            );

            // ===== Cargar Venue =====
            JSONObject jVenue = jEvento.getJSONObject("venue");
            Venue venue = new Venue(
                jVenue.getString("direccion"),
                jVenue.getInt("maxCapacidad"),
                jVenue.getJSONArray("restricciones").toList().stream().map(Object::toString).toList(),
                jVenue.getBoolean("estado")
            );

            // ===== Cargar Localidades =====
            JSONArray jLocalidades = jVenue.getJSONArray("localidades");
            for (int j = 0; j < jLocalidades.length(); j++) {
                JSONObject jLoc = jLocalidades.getJSONObject(j);
                Localidad localidad;

                if ("LocalidadNumerada".equals(jLoc.getString("tipo"))) {
                    localidad = new LocalidadNumerada(
                        jLoc.getString("idLocalidad"),
                        jLoc.getDouble("precio")
                    );
                } else {
                    localidad = new LocalidadBasica(
                        jLoc.getString("idLocalidad"),
                        jLoc.getDouble("precio")
                    );
                }

                // Descuento opcional
                if (jLoc.has("descuento")) {
                    JSONObject jDesc = jLoc.getJSONObject("descuento");
                    Descuento desc = new Descuento(
                        jDesc.getDouble("porcentaje"),
                        jDesc.getInt("fechaInicio"),
                        jDesc.getInt("fechaFin")
                    );
                    localidad.setDescuento(desc);
                }

                venue.agregarLocalidad(localidad);
            }

            // ===== Crear Evento =====
            LocalDate fecha = LocalDate.parse(jEvento.getString("fecha"));
            Evento evento = new Evento(
                jEvento.getString("tipo"),
                fecha,
                jEvento.getInt("hora"),
                jEvento.getString("idEvento"),
                venue,
                organizador
            );

            eventos.add(evento);
        }
    }

    @Override
    public void salvar(String archivo, List<Evento> eventos) throws IOException {
        JSONArray jEventos = new JSONArray();

        for (Evento e : eventos) {
            JSONObject jEvento = new JSONObject();
            jEvento.put("tipo", e.getTipo());
            jEvento.put("fecha", e.getFecha().toString());
            jEvento.put("hora", e.getHora());
            jEvento.put("idEvento", e.getIdEvento());
            jEvento.put("estado", e.getEstado());

            // ===== Guardar Organizador =====
            Organizador o = e.getOrganizadorAsignado();
            if (o != null) {
                JSONObject jOrg = new JSONObject();
                jOrg.put("nombre", o.getNombre());
                jOrg.put("apellidos", o.getApellidos());
                jOrg.put("login", o.getLogin());
                jOrg.put("password", o.getPassword());
                jOrg.put("correo", o.getCorreo());
                jOrg.put("idOrganizador", o.getIdOrganizador());
                jEvento.put("organizador", jOrg);
            }

            // ===== Guardar Venue =====
            Venue v = e.getVenueAsignado();
            JSONObject jVenue = new JSONObject();
            jVenue.put("direccion", v.getDireccion());
            jVenue.put("maxCapacidad", v.getMaxCapacidad());
            jVenue.put("restricciones", new JSONArray(v.getRestricciones()));
            jVenue.put("estado", v.isEstado());

            // Localidades
            JSONArray jLocalidades = new JSONArray();
            for (Localidad l : v.getLocalidades()) {
                JSONObject jLoc = new JSONObject();
                jLoc.put("idLocalidad", l.getIdLocalidad());
                jLoc.put("precio", l.getPrecio());
                jLoc.put("tipo", l.getClass().getSimpleName());

                if (l.getDescuento() != null) {
                    Descuento d = l.getDescuento();
                    JSONObject jDesc = new JSONObject();
                    jDesc.put("porcentaje", d.getPorcentaje());
                    jDesc.put("fechaInicio", d.getFechaInicio());
                    jDesc.put("fechaFin", d.getFechaFin());
                    jLoc.put("descuento", jDesc);
                }

                jLocalidades.put(jLoc);
            }

            jVenue.put("localidades", jLocalidades);
            jEvento.put("venue", jVenue);

            jEventos.put(jEvento);
        }

        PrintWriter pw = new PrintWriter(archivo);
        jEventos.write(pw, 2, 0);
        pw.close();
    }
}
