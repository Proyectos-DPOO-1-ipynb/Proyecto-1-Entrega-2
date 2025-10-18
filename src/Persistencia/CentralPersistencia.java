package Persistencia;

import java.io.IOException;

public class CentralPersistencia {

    public static final String JSON = "JSON";

    public static IPersistenciaUsuario getPersistenciaUsuario(String tipoArchivo) throws IOException {
        if (JSON.equals(tipoArchivo))
            return new UsuarioPersistencia();
        else
            throw new IOException();
    }

    public static IPersistenciaEvento getPersistenciaEvento(String tipoArchivo) throws IOException {
        if (JSON.equals(tipoArchivo))
            return new EventoPersistencia();
        else
            throw new IOException();
    }

    public static IPersistenciaTiquete getPersistenciaTiquete(String tipoArchivo) throws IOException {
        if (JSON.equals(tipoArchivo))
            return new TiquetePersistencia();
        else
            throw new IOException();
    }
}

