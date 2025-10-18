package Persistencia;
import java.io.IOException;
import java.util.List;
import evento.Evento;

public interface IPersistenciaEvento {
	 void cargar(String archivo, List<Evento> eventos) throws IOException, Exception;
	 void salvar(String archivo, List<Evento> eventos) throws IOException;
	}
