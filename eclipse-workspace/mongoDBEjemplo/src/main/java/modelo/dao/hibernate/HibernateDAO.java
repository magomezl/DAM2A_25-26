package modelo.dao.hibernate;

import java.util.Collection;
import java.util.List;

import modelo.dto.Autores;
import modelo.dto.Generos;
import modelo.dto.Libros;
import modelo.dto.Nacionalidades;

public interface HibernateDAO {
	
	boolean anadirAutor(Autores autor);
	
	boolean anadirLibros(Libros libro);
	
	int anadirAutores(Collection<Autores> autores);

	int anadirLibros(Collection<Libros> libros);

	<T> List<T> getAll(Class<T> entityClass);

	List<Autores> getAllAutoresConNacionalidad();

	List<Libros> getAllLibrosConGenero();

	List<Autores> buscarAutoresDeLibros(String tituloLibro);

	

	
	
}
