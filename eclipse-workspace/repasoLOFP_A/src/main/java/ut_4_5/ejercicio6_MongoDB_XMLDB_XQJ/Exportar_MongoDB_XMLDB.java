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
			// MongoDB
			MongoDatabase database = mongoClient.getDatabase("academiaMusica");
			MongoCollection<Document> col = database.getCollection("matriculas");

			// Agrupamos en un mapa los alumnos de cada curso y en otro mapa cada curso son su cuota. En ambos mapas la clave será el nombre del curso
			Map<String, List<Document>> cursosMapAlumnos = new HashMap<>();
			Map<String, Integer> cursosMapCuotas = new HashMap<>();

			for(Document alumno: col.find()) {
				String nombre = alumno.getString("nombre");
				String iban = alumno.getString("iban");
				String banco = alumno.getString("banco");
				List<Document> cursos = (List<Document>) alumno.get("cursos");
				for(Document curso: cursos) {
					String denominacion = curso.getString("denominacion").toLowerCase();
					int cuota = ((Number)curso.get("cuota_mensual")).intValue();
					// Inserta en el mapa si no existe la clave
					cursosMapAlumnos.putIfAbsent(denominacion, new ArrayList<>());
					cursosMapCuotas.putIfAbsent(denominacion, cuota);

					Document alumnoParaXML = new Document("nombre", nombre)
							.append("iban", iban)
							.append("banco",  banco);
					cursosMapAlumnos.get(denominacion).add(alumnoParaXML);
				}
			}

			// Construimos el XML en un StringBuilder ahora que tenemos los datos organizados en dos mapas.
			StringBuilder xml = new StringBuilder();
			xml.append("<academia>");
			for(String cursoKey: cursosMapAlumnos.keySet()) {
				xml.append("<curso denominacion=\"")
				.append(cursoKey)
				.append("\" cuota=\"")
				.append(cursosMapCuotas.get(cursoKey))
				.append("\">");
				for(Document alumno: cursosMapAlumnos.get(cursoKey)) {
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

			// Almaceno el StringBuilder en un recurso en eXist-db
			Class cl = Class.forName("org.exist.xmldb.DatabaseImpl");
			Database databaseExistDB = (Database) cl.getDeclaredConstructor().newInstance();
			DatabaseManager.registerDatabase(databaseExistDB);
			
			//Obtenemos la colección raiz
			Collection colExistDB = DatabaseManager.getCollection("xmldb:exist://10.196.55.168:8080/exist/xmlrpc/db", "admin", "toor");
			
			//Creo la colección si no existe
			CollectionManagementService mgtService = (CollectionManagementService) colExistDB.getService("CollectionManagementService", "1.0");
			Collection colTrabajo = colExistDB.getChildCollection("academiaMusica");
			if (colTrabajo==null) {
				colTrabajo = mgtService.createCollection("academiaMusica");
			}
			
			XMLResource res = (XMLResource) colTrabajo.createResource("academia.xml", "XMLResource");
			res.setContent(xml);
			colTrabajo.storeResource(res);
			System.out.println("XML almacenado en eXist-db");
			
			consultaAlumnosPorCurso("piano");
			
		} catch (Exception e) {
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
					"declare variable $cursoVarE external; "
					+ "for $a in doc('/db/academiaMusica/academia.xml')//curso[@denominacion=$cursoVarE]/alumno return $a/@nombre/string()";
			
			XQPreparedExpression exp = conn.prepareExpression(query);
			exp.bindString(new QName("cursoVarE"), curso, null);
			XQResultSequence result = exp.executeQuery();
			while(result.next()) {
				System.out.println(result.getItemAsString(null));
			}
			
			
		} catch (XQException e) {
			e.printStackTrace();
		}
	}
}
