package modelo;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class PruebaMongoDB {
	private static MongoDatabase db;
	
	public static void main(String[] args) {
		try(MongoClient mongoClient = MongoClients.create("mongodb://10.196.55.103:27017")){
			db = mongoClient.getDatabase("biblioteca");
			System.out.println("Conexión exitosa con MongoDB");
//			anadirDoc();
//			mostrarNovelas();
//			mostrarAutores("Checa", 1900);
//			modificarGenero("novela infantil", "Fantasía");
//			anadirDocEmbebido();
//			anadirDocAutor();
//			anadirDocReferenciadoObjectId();
			
//			modificarAnadirPropDocAutor();
//			anadirDocReferenciadoId();
//			modificarEliminarPropDocAutor();
			System.out.println("Autores con nacionalidad española " + autoresNacionalidad("española"));
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	
	/**
	 * 
	 * @param nacionalidad
	 * @return número de autores de la nacionalidad pasada como parámetro
	 */
	
	private static int autoresNacionalidad(String nacionalidad) {
		Pattern patron = Pattern.compile("^"+ Pattern.quote(nacionalidad)+"$", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		
		return (int) db.getCollection("autores").countDocuments(regex("nacionalidad", patron));
		
	}






	private static void modificarGenero(String generoOld, String generoNew) {
		db.getCollection("libros").updateMany(eq("genero", generoOld), set("genero", generoNew));
	}
	
	/**
	 * Mostrar autores de una determinada nacionalidad cuyo año de nacimiento sea mayor o igual a uno dado
	 * @param nacionalidad
	 * @param nacimientoMayor
	 */
	private static void mostrarAutores(String nacionalidad, int nacimientoMayor) {
		
		FindIterable<Document> resultados =  db.getCollection("autores")
				.find(and(eq("nacionalidad", nacionalidad), gte("nacimiento", nacimientoMayor))).limit(20);
		for (Document doc: resultados) {
			System.out.println(doc.toString());
		}
		
		
		
	}
	/**
	 * Mostrar Novelas
	 */
	private static void mostrarNovelas() {
		Document filtro = new Document("genero", "novela");
		FindIterable<Document> resultados =  db.getCollection("libros").find(filtro);
		for (Document doc: resultados) {
			System.out.println(doc.toJson());
		}
		
		
	}

	// TODO crear otros dos métodos para añadir autor embebido y referencia por objectId y por id
	/**
	 * Añadimos un libro con el autor embebido
	 */
	private static void anadirDocEmbebido() {
		ArrayList<String> personajes = new ArrayList<String>(Arrays.asList("tía Em", "Hombre de hojalata", "Espantapajaros", "León"));
		Document doc = new Document()
				.append("titulo", "El maravilloso Mago de Oz")
				.append("autor", 
						new Document()
							.append("nombre", "l. Frank Baum")
							.append("nacionalidad", "Americano")
							.append("nacimiento", 1856))
				.append("genero", "Fantasía")
				.append("personajes", personajes);
		db.getCollection("libros").insertOne(doc);
		
	}
	
	/**
	 * Añadimos un libro con el autor referenciado por el id de un objeto de la colección autores
	 */
	private static void anadirDocReferenciadoId() {
		ArrayList<String> personajes = new ArrayList<String>(Arrays.asList("tía Em", "Hombre de hojalata", "Espantapajaros", "León"));
		int id = db.getCollection("autores").find(eq("nombre", "l. Frank Baum")).first().getInteger("id");
		
		
		Document doc = new Document()
				.append("titulo", "El maravilloso Mago de Oz")
				.append("autor", id)
				.append("genero", "Fantasía")
				.append("personajes", personajes);
		db.getCollection("libros").insertOne(doc);
		
	}
	
	
	
	/**
	 * Añadimos un libro con el autor referenciado por el objectId de un objeto de la colección autores
	 */
	private static void anadirDocReferenciadoObjectId() {
		ArrayList<String> personajes = new ArrayList<String>(Arrays.asList("tía Em", "Hombre de hojalata", "Espantapajaros", "León"));
		ObjectId id = db.getCollection("autores").find(eq("nombre", "l. Frank Baum")).first().getObjectId("_id");
		
		
		Document doc = new Document()
				.append("titulo", "El maravilloso Mago de Oz")
				.append("autor", id)
				.append("genero", "Fantasía")
				.append("personajes", personajes);
		db.getCollection("libros").insertOne(doc);
		
	}
	
	
	private static void anadirDocAutor() {
		
		Document doc = new Document()
				.append("nombre", "l. Frank Baum")
				.append("nacionalidad", "Americano")
				.append("nacimiento", 1856);
		db.getCollection("autores").insertOne(doc);
		
	}
	
	/**
	 * Modificamos el autor que acabamos de añadir y le añadimos una propiedad id con valor 222
	 */
	private static void modificarAnadirPropDocAutor() {
		
		db.getCollection("autores").updateOne(eq("nombre", "l. Frank Baum"), combine(set("id", 222), set("fallecimiento", 1919)));
	
	}
	
	
	private static void modificarEliminarPropDocAutor() {
		
		db.getCollection("autores").updateOne(eq("nombre", "l. Frank Baum"), unset("fallecimiento"));
	
	}
	
	
	/**
	 * Añadimos un libro con el autor como propiedad de tipo String
	 */
	private static void anadirDoc() {
		
		Document doc = new Document()
				.append("titulo", "El maravilloso Mago de Oz")
				.append("autor", "l. Frank Baum")
				.append("genero", "Fantasía");
		db.getCollection("libros").insertOne(doc);
		
	}
	
	
	//TODO método mostrar por genero pasandole el genero como parámetro
	// TODO Modificar los métodos de añadir de manera que no nos deje añadir dos libros con el mismo nombre y autor

}
