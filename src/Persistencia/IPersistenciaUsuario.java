package Persistencia;
import usuario.Usuario;
import java.io.IOException;
import java.util.List;

public interface IPersistenciaUsuario {
    public void cargar(String archivo, List<Usuario> usuarios) throws IOException, Exception;
    void salvar(String archivo, List<Usuario> usuarios) throws IOException;
}
