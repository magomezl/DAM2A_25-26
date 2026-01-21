package modelo.dao.mongodb;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

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

}
