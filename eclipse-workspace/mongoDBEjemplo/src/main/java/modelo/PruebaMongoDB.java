package modelo;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class PruebaMongoDB {
	private static MongoDatabase db;
	
	public static void main(String[] args) {
		try(MongoClient mongoClient = MongoClients.create("mongodb://10.196.55.103:27017")){
			db = mongoClient.getDatabase("biblioteca");
			System.out.println("Conexión exitosa con MongoDB");
//			anadirDoc();
//			mostrarNovelas();
//			mostrarAutores("Checa", 1900);
			modificarGenero("Fantasía", "novela infantil");
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void modificarGenero(String generoOld, String generoNew) {
		db.getCollection("libros").updateMany(eq("genero", generoOld), set("genero", generoNew));
	}

	private static void mostrarAutores(String nacionalidad, int nacimientoMayor) {
		
		FindIterable<Document> resultados =  db.getCollection("autores")
				.find(and(eq("nacionalidad", nacionalidad), gte("nacimiento", nacimientoMayor))).limit(20);
		for (Document doc: resultados) {
			System.out.println(doc.toString());
		}
		
		
		
	}

	private static void mostrarNovelas() {
		Document filtro = new Document("genero", "novela");
		FindIterable<Document> resultados =  db.getCollection("libros").find(filtro).limit(20);
		for (Document doc: resultados) {
			System.out.println(doc.toJson());
		}
		
		
	}

	private static void anadirDoc() {
		Document doc = new Document()
				.append("titulo", "El maravilloso Mago de Oz")
				.append("autor", "l. Frank Baum")
				.append("genero", "Fantasía");
		db.getCollection("libros").insertOne(doc);
		
	}

}
