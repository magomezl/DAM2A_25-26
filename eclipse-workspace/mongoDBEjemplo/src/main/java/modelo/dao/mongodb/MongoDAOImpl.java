package modelo.dao.mongodb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import modelo.dto.Autores;
import modelo.dto.Generos;
import modelo.dto.Libros;
import modelo.dto.Nacionalidades;

public class MongoDAOImpl implements MongoDAO {
	private static MongoClient mongoClient = MongoClients.create("mongodb://10.196.55.103:27017");
	private static MongoDatabase db = mongoClient.getDatabase("Biblioteca_II");

	@Override
	public List<String> getGeneros() {
		/**
		 * TreeSet es una colección que no permite duplicados y mantiene los elementos ordenados alfabeticamente 
		 */
		TreeSet<String> generosOrdenados = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		
		for (String gen: db.getCollection("libros").distinct("genero", String.class)) {
			if (gen!=null ) {
				if (!gen.trim().isEmpty()) {
					System.out.println(gen.trim().toLowerCase());
					generosOrdenados.add(gen.trim().toLowerCase());
				}
			}
		}
		return new ArrayList<String>(generosOrdenados);
	}

	@Override
	public List<Autores> getAutores() {
		List<Autores> lista = new ArrayList<Autores>();
		for(Document doc: db.getCollection("autores").find()) {
			lista.add(crearAutorDesdeDoc(doc));
		}
		return lista;
	}

	private Autores crearAutorDesdeDoc(Document doc) {
		Autores a = new Autores();
		a.setNombre(doc.getString("nombre"));
		a.setNacimiento(doc.getInteger("anio_nacimiento"));
		a.setMuerte(doc.getInteger("anio_muerte"));
		
		Nacionalidades n = new Nacionalidades();
		n.setNombre(doc.getString("nacionalidad"));

		a.setNacionalidades(n);
		
		//Los libros los rellenamos después, de momento no tenemos datos para hacerlo
		a.setLibroses(new HashSet<>());
		return a;
	}

	@Override
	public List<Libros> getLibros() {
		List<Libros> lista = new ArrayList<Libros>();
		for(Document doc: db.getCollection("libros").find()) {
			Libros l = new Libros();
			
			l.setTitulo(doc.getString("titulo"));
			
			// En mongodb cada libro puede tener más de un genero pero en Hibernate (en la db) 
			// solo tenemos un genero por libro, entonces determinamos que 
			// si hay más de un genero en mongo cogemos el primero 
			
			// Gestión de generos			
			Object o = doc.get("genero");
			Generos gen = new Generos();
			if (o instanceof String) {
				gen.setNombre((String)o);
			}else if (o instanceof List){
				gen.setNombre(((List<String>)o).get(0));
			}
			l.setGeneros(gen);
			
			// Gestión de autoreses
			o = doc.get("autor");
			Set<Autores> autoresSet = new HashSet<>();
			if (o instanceof Document) {
				// embebido
				autoresSet.add(crearAutorDesdeDoc((Document)o));
			}else if (o instanceof ObjectId) {
				Document aDoc = db.getCollection("autores").find(Filters.eq("_id", (ObjectId)o)).first();
				autoresSet.add(crearAutorDesdeDoc(aDoc));
			}else if (o instanceof List){
				for (Object elem : (List<?>)o) {
					if (elem instanceof Document) {
						autoresSet.add(crearAutorDesdeDoc((Document)elem));
					}else if (elem instanceof ObjectId) {
						Document aDoc = db.getCollection("autores").find(Filters.eq("_id", (ObjectId)elem)).first();
						autoresSet.add(crearAutorDesdeDoc(aDoc));
					}
				}
			}
			l.setAutoreses(autoresSet);
			//TODO
		}
		return lista;
	}

}
