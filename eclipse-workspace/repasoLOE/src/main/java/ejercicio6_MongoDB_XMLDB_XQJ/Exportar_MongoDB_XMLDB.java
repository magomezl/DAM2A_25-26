package ut_4_5.ejercicio6_MongoDB_XMLDB_XQJ;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import org.bson.Document;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Exportar_MongoDB_XMLDB {
	private static MongoClient mongoClient = MongoClients.create("mongodb://10.196.55.168:27017");
		
	public static void main(String[] args) {
		try {

			// Conexion con MongoDB
			MongoDatabase db = mongoClient.getDatabase("academiaMusica");
			MongoCollection<Document> collection = db.getCollection("matriculas");

			// Agrupamos en un mapa los alumnos por curso y en otro por cuota. En ambos mapas la clave será el nombre del curso
			Map<String, List<Document>> cursosMap = new HashMap<>();
			Map<String, Integer> cuotasMap = new HashMap<>();

			for(Document alumno: collection.find()) {
				String nombre = alumno.getString("nombre");
				String iban = alumno.getString("iban");
				String banco = alumno.getString("banco");
				List<Document> cursos = (List<Document>) alumno.get("cursos");
				for(Document curso: cursos) {
					String denominacion = curso.getString("denominacion").toLowerCase();
					int cuota = ((Number)curso.get("cuota_mensual")).intValue();
					// Inserta un valor si no existe en el mapa la clave
					cursosMap.putIfAbsent(denominacion,  new ArrayList<>());
					cuotasMap.putIfAbsent(denominacion, cuota);

					Document alumnoParaXML = new Document("nombre", nombre)
							.append("iban", iban)
							.append("banco", banco);

					cursosMap.get(denominacion).add(alumnoParaXML);
				}
			}

			// Generamos el XML ahora que tenemos los datos organizados en los dos mapas
			StringBuilder xml = new StringBuilder();
			xml.append("<academia>");

			for(String cursoKey: cursosMap.keySet()) {
				xml.append("<curso denominacion=\"")
				.append(cursoKey)
				.append("\" cuota=\"")
				.append(cuotasMap.get(cursoKey))
				.append("\">");
				for (Document alumno: cursosMap.get(cursoKey)) {
					xml.append("<alumno nombre=\"")
					.append(alumno.getString("nombre"))
					.append("\" iban=\"")
					.append(alumno.getString("iban"))
					.append("\" banco=\"")
					.append(alumno.getString("banco"))
					.append("\"/>");
				}
				xml.append("</curso>");
			}
			xml.append("</academia>");
			System.out.println(xml);

			// Almaceno en exist-db
			Class cl = Class.forName("org.exist.xmldb.DatabaseImpl");
			Database database = (Database) cl.getDeclaredConstructor().newInstance();
			DatabaseManager.registerDatabase(database);
			
			// Obtengo la colección raiz
			Collection root = DatabaseManager.getCollection("xmldb:exist://10.196.55.168:8080/exist/xmlrpc/db", "admin", "toor");
			// Creo la colección si no existe 
			CollectionManagementService mgtService = (CollectionManagementService) root.getService("CollectionManagementService", "1.0");
			Collection col = root.getChildCollection("academiaMusica");
			if (col == null) {
				col = mgtService.createCollection("academiaMusica");
			}
			
			XMLResource res = (XMLResource) col.createResource("academia.xml", "XMLResource");
			res.setContent(xml.toString());
			col.storeResource(res);
			
			System.out.println("XML almacenado en eXist.db");
			
			consultaAlumnosPorCurso("piano");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private static void consultaAlumnosPorCurso(String curso) {
		try {
			XQDataSource ds = new net.xqj.exist.ExistXQDataSource();
			ds.setProperty("serverName", "10.196.55.168");
			ds.setProperty("port", "8080");

			XQConnection conn = ds.getConnection("admin", "toor");
			
			String query =
					"declare variable $curso external; " +
					"for $c in doc('/db/academiaMusica/academia.xml')//curso[@denominacion=$curso]/alumno " +
							"return $c/@nombre/string()";
			
			XQPreparedExpression exp = conn.prepareExpression(query);
			exp.bindString(new QName("curso"), curso, null);
			XQResultSequence result = exp.executeQuery();
			
			while (result.next()) {
				System.out.println(result.getItemAsString(null));
			}

			
			
			
			
		} catch (XQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
