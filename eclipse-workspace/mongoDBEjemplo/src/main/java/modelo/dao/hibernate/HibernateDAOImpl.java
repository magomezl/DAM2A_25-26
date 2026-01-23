package modelo.dao.hibernate;

import java.util.Collection;

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
					autor.setNacionalidades(nacEnBD);
				}else {
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
							autor.setNacionalidades(nacEnBD);
						}else {
							sesion.persist(nacAutor);
						}
					}
					
					// TODO Autores 
					
					
					
					
				}
			}
			
			
			
			
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

}
