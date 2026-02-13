package existdbApp;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import org.w3c.dom.Node;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

import net.xqj.exist.ExistXQDataSource;

public class Prueba {
	private static XQDataSource  xqs = new ExistXQDataSource(); ; 
	private static XQConnection conn;
	private static Collection col;
	
	public static void main(String[] args) {
		
		//XQJ
		try {
			xqs.setProperty("serverName", "10.196.55.103");
			xqs.setProperty("port", "8080");
			xqs.setProperty("user", "admin");
			xqs.setProperty("password", "toor");
			
			conn = xqs.getConnection();
			if (conn ==null) {
				System.out.println("Error de conexión");
				return;
			}
			consultarAutores();
		} catch (XQException e) {
			e.printStackTrace();
		}
		
		// Crear un método que añada un autor que se pasará como parámetro su nombre y apellidos a un libro cuyo título también será un parámetro
		anadirAutor("Adolfo", "Nuñez Gil", "La Conspiracion");
//		eliminarAutor("Adolfo", "Nuñez Gil");		
		
		// XML:DB
		try {
			//Cargamos el driver eXist
			Class cl = Class.forName("org.exist.xmldb.DatabaseImpl");
			// Creamos una instancia de la bbdd
			Database database = (Database) cl.getDeclaredConstructor().newInstance();
			// Registro del driver
			DatabaseManager.registerDatabase(database);
			
			col = DatabaseManager.getCollection("xmldb:exist://10.196.55.103:8080/exist/xmlrpc/db/ejercicios", "admin", "toor");
			
			CollectionManagementService servicio = (CollectionManagementService) col.getService("CollectionManagementService", "1.0");
			
//			servicio.createCollection("micoleccion");
//			servicio.removeCollection("col2");
			
//			System.getProperty("user.dir") + System.getProperty("file.separator") +
//			"resources" + System.getProperty("file.separator")
			subirDocumento(new File(System.getProperty("user.dir") + "/src/main/resources/files/documento1.xml"));
			descargarDocumento(col, "ciclos.xml", System.getProperty("user.dir") + "/src/main/resources/files");
				
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		}


	}

	private static void descargarDocumento(Collection col, String docName, String URIDestino) {
		try {
			XMLResource res = (XMLResource) col.getResource(docName);
			// Paso el contenido del recurso a un árbol DOM en memoria
			Node documento = res.getContentAsDOM();
			File archivo = new File(URIDestino+"/"+docName);
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.transform(new DOMSource(documento), new StreamResult(archivo));
			
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void subirDocumento(File archivo) {
			try {
				if (archivo.canRead()){
					System.out.println("hola");
					Resource recurso = col.createResource(archivo.getName(), "XMLResource");
					recurso.setContent(archivo);
					col.storeResource(recurso);
				}
			} catch (XMLDBException e) {
			
				e.printStackTrace();
			}
	}

	private static void eliminarAutor(String nombreAutor, String apellidosAutor) {
		String consulta = "for $autor in doc('/db/ejercicios/Libros.xml')/Libros/Libro/Autores/Autor[Nombre='" + nombreAutor
				+ "'][Apellido='" + apellidosAutor + "'] return update delete $autor";
		
		try {
			XQExpression xqe = conn.createExpression();
			xqe.executeCommand(consulta);
		} catch (XQException e) {
			e.printStackTrace();
		}
	}

	private static void anadirAutor(String nombreAutor, String apellidosAutor, String tituloLibro) {
		String consulta = "update insert "
				+ "<Autor> "
				+ "<Nombre>" + nombreAutor + "</Nombre>"
				+ "<Apellido>" + apellidosAutor + "</Apellido>" 
				+ "</Autor> into doc('/db/ejercicios/Libros.xml')/Libros/Libro[Titulo='" + tituloLibro + "']/Autores";
		
		try {
			XQExpression xqe = conn.createExpression();
			xqe.executeCommand(consulta);
		} catch (XQException e) {
			e.printStackTrace();
		}
	}

	private static void consultarAutores() {
		String consulta = "doc('/db/ejercicios/Libros.xml')/Libros/Libro/Autores/Autor";
		
		XQExpression xqe;
		try {
			xqe = conn.createExpression();
			XQResultSequence xqrs = xqe.executeQuery(consulta);

			while(xqrs.next()){
				XMLStreamReader xsr = xqrs.getItemAsStream();
				while (xsr.hasNext()){
					if (xsr.getEventType()== XMLStreamConstants.CHARACTERS){
						System.out.println(xsr.getText());
					} else if (xsr.getEventType() == XMLStreamConstants.START_ELEMENT) {
						System.out.println("Principio de elemento" + xsr.getLocalName());
						
					}else if (xsr.getEventType() == XMLStreamConstants.END_ELEMENT) {
						System.out.println("Fin de elemento" + xsr.getLocalName());
					}
					xsr.next();
				}
			}
		} catch (XQException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	
	
	
}
