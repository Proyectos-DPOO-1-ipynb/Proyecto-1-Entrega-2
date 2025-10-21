package Persistencia;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.json.*;

//import modelo.SistemaBoletamaster;
import usuario.Usuario;
import usuario.comprador.Cliente;
import usuario.comprador.Organizador;
import usuario.Administrador;

public class UsuarioPersistencia implements IPersistenciaUsuario {

	@Override // Toca crear todos los cosntructores de los usuarioooos
    public void cargar(String archivo, List<Usuario> usuarios) throws IOException, Exception {
        String json = new String(Files.readAllBytes(new File(archivo).toPath()));
        JSONArray arr = new JSONArray(json);
        for (int i = 0; i < arr.length(); i++) {
            JSONObject j = arr.getJSONObject(i);
            String tipo = j.optString("tipo", "Cliente");
            Usuario u;
            if ("Administrador".equals(tipo)) {
                u = new Administrador(j.getString("nombre"), j.getString("apellidos"),
                                      j.getString("login"), j.getString("password"),
                                      j.getString("correo"));
            } else if ("Organizador".equals(tipo)) {
                u = new Organizador(j.getString("nombre"), j.getString("apellidos"),
                                     j.getString("login"), j.getString("password"),
                                     j.getString("correo"), j.optString("idOrganizador", ""));
            } else {
                u = new Cliente(j.getString("nombre"), j.getString("apellidos"),
                                j.getString("login"), j.getString("password"),
                                j.getString("correo"));
            }
            usuarios.add(u);
        }
    }

    @Override
    public void salvar(String archivo, List<Usuario> usuarios) throws IOException {
        JSONArray arr = new JSONArray();
        for (Usuario u : usuarios) {
            JSONObject j = new JSONObject();
            j.put("tipo", u.getClass().getSimpleName());
            j.put("nombre", u.getNombre());
            j.put("apellidos", u.getApellidos());
            j.put("login", u.getLogin());
            j.put("password", u.getPassword());
            j.put("correo", u.getCorreo());
            if (u instanceof Organizador) j.put("idOrganizador", ((Organizador) u).getIdOrganizador());
            arr.put(j);
        }
        PrintWriter pw = new PrintWriter(archivo);
        arr.write(pw, 2, 0);
        pw.close();
    }
}