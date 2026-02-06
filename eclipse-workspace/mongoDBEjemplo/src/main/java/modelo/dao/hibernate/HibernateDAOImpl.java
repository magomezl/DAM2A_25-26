package modelo.dao.hibernate;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import modelo.dto.Autores;
import modelo.dto.Generos;
import modelo.dto.Libros;
import modelo.dto.Nacionalidades;

public class HibernateDAOImpl implements HibernateDAO {
	private static final SessionFactory sF = new Configuration().configure().buildSessionFactory();
	private static Session sesion; 
	
	
	@Override
	public boolean anadirAutor(Autores autor) {
		boolean retorno = false;
		sesion = sF.openSession();
		Transaction tx = sesion.beginTransaction();
		try {
			// Busco y persisto si no existe en la db la nacionalidad
			Nacionalidades nacAutor = autor.getNacionalidades();
			//TODO método
			if (nacAutor != null) {
				Nacionalidades nacEnBD = sesion.createSelectionQuery("FROM Nacionalidades WHERE LOWER(nombre)= :nombreP", Nacionalidades.class)
					.setParameter("nombreP", nacAutor.getNombre().toLowerCase())
					.uniqueResult();
				if (nacEnBD != null) {
					// Existe la nacionalidad en la db -> la tomo 
					autor.setNacionalidades(nacEnBD);
				}else {
					// No existe -> la guardo en la DB 
					sesion.persist(nacAutor);
				}
			}
			sesion.persist(autor);
			tx.commit();
			retorno = true;
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			sesion.close();
		}
		return retorno;
	}

	@Override
	public boolean anadirLibros(Libros libro) {
		boolean retorno = false;
		sesion = sF.openSession();
		Transaction tx = sesion.beginTransaction();
		try {
			// Gestión de Generos
			Generos genLibro = libro.getGeneros();
			if (genLibro != null) {
				Generos genEnBD = sesion.createSelectionQuery("FROM Generos WHERE LOWER(nombre)= :nombreG", Generos.class)
					.setParameter("nombreG", genLibro.getNombre().toLowerCase())
					.uniqueResult();
				if (genEnBD != null) {
					// Ya existe en la DB -> lo usamos 
					libro.setGeneros(genEnBD);
				}else {
					// No existe en la DB -> persistimos el nuevo
					sesion.persist(genLibro);
				}
			}
			
			// Gestión de autoreses. Tengo que mirar si los autores están en la DB. Si no están los persisto, si están los uso
			Set<Autores> autoresProcesados = new HashSet<>();
			if (libro.getAutoreses()!=null) {
				for (Object o: libro.getAutoreses()) {
					
					Autores autor = (Autores)o;
					
					Nacionalidades nacAutor = autor.getNacionalidades();
					//TODO método
					if (nacAutor != null) {
						Nacionalidades nacEnBD = sesion.createSelectionQuery("FROM Nacionalidades WHERE LOWER(nombre)= :nombreP", Nacionalidades.class)
							.setParameter("nombreP", nacAutor.getNombre().toLowerCase())
							.uniqueResult();
						if (nacEnBD != null) {
							// Si existe la nacionalidad la tomo
							autor.setNacionalidades(nacEnBD);
						}else {
							// Si no existe la persisto
							sesion.persist(nacAutor);
						}
					}
					// Autores 
					// Vemos si existe el autor 
					Autores autorBD = sesion.createSelectionQuery("FROM Autores WHERE LOWER(nombre)= :nombreP AND nacimiento = :anio", Autores.class)
							.setParameter("nombreP", autor.getNombre().toLowerCase())
							.setParameter("anio", autor.getNacimiento())
							.uniqueResult();
						if (autorBD != null) {
							// Si existe el autore en la DB lo añado
							autoresProcesados.add(autorBD);
						}else {
							// Si no existe la persisto
							sesion.persist(autor);
							autoresProcesados.add(autor);
						}
				} // for
				libro.setAutoreses(autoresProcesados);
			}// if
			sesion.persist(libro);
			
			/**
			 * Antes de persistir el libro y para que se almacenen registros en la tabla que 
			 * representa la relación muchos a muchos hay que mantener la relación bidireccional,
			 * es decir, tengo que añadir el libro al Set libroses de cada autor
			 * 
			 * Ya que si no lo hago Hibernate solo conoce la relación entre libros y autores desde
			 * el lado del libro. También tiene que conocerla desde el lado del autor.
			 * En este caso tengo que coger los autoresProcesados y añadirles el libro
			 * 
			 */
			for (Autores au: autoresProcesados) {
				au.getLibroses().add(libro);
			}
			tx.commit();
			retorno = true;
			
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			sesion.close();
		}
		
		return retorno;
	}

	@Override
	public int anadirAutores(Collection<Autores> autores) {
		int i = 0;
		for(Autores autor: autores) {
			if (anadirAutor(autor)) i++; 
		}
		return i;
	}

	@Override
	public int anadirLibros(Collection<Libros> libros) {
		int i = 0;
		for(Libros libro: libros) {
			if (anadirLibros(libro)) i++; 
		}
		return i;
	}

	@Override
	public <T> List<T> getAll(Class<T> entityClass) {
		try(Session sesion = sF.openSession()){
			String hql = "FROM " + entityClass.getSimpleName();
			return sesion.createSelectionQuery(hql, entityClass).getResultList();
		}
	}

	@Override
	public List<Autores> getAllAutoresConNacionalidad() {
		try(Session sesion = sF.openSession()){
//			String hql = "FROM Autores";  Con esta consulta no nos trae la nacionalidad por ser lazy. Tenemos que forzarlo con la siguiente consulta
			String hql = "SELECT a FROM Autores a LEFT JOIN FETCH a.nacionalidades";
			List<Autores> autoresAL = sesion.createSelectionQuery(hql, Autores.class).getResultList();
			return autoresAL;		
		}
	}

	@Override
	public List<Libros> getAllLibrosConGenero() {
		try(Session sesion = sF.openSession()){
			String hql = "SELECT l FROM Libros l LEFT JOIN FETCH l.generos";
			List<Libros> librosAL = sesion.createSelectionQuery(hql, Libros.class).getResultList();
			System.out.println(librosAL);
			return librosAL;		
		}
	}

	@Override
	public List<Autores> buscarAutoresDeLibros(String tituloLibro) {
		if (tituloLibro == null || tituloLibro.trim().isEmpty()) {
			return null;
		}
		try(Session sesion = sF.openSession()){
			String hql = "SELECT DISTINCT a FROM Libros l JOIN l.autoreses a LEFT JOIN FETCH a.nacionalidades WHERE lower(l.titulo) LIKE :patronTituloLibro";
			List<Autores> autoresAL = sesion.createSelectionQuery(hql, Autores.class)
					.setParameter("patronTituloLibro", "%" + tituloLibro +"%")
					.getResultList();
			return autoresAL;		
		}
	}

}
