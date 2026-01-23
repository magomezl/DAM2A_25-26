package modelo.dao.mongodb;

import java.util.List;

import modelo.dto.Autores;
import modelo.dto.Libros;

public interface MongoDAO {
	
	List<String> getGeneros();

	List<Autores> getAutores();

	List<Libros> getLibros();

}
