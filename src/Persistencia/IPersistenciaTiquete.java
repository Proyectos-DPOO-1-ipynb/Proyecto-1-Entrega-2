package Persistencia;
import java.io.IOException;
import java.util.List;
import tiquete.Tiquete;

public interface IPersistenciaTiquete {
    void cargar(String archivo, List<Tiquete> tiquetes) throws IOException, Exception;
    void salvar(String archivo, List<Tiquete> tiquetes) throws IOException;
}