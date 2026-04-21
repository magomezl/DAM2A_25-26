package ut_1.ejercicio2_DOM_CSV;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import ut_1.ejercicio1_SAX_Excel.Parseo_SAX_Excel;

public class Importar_CSV_DOM {

	public static void main(String[] args) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			
//			InputStream xml = Importar_CSV_DOM.class.getClassLoader().getResourceAsStream("resources/Files/academia.xml");
			
//			Document doc = builder.parse(xml);
			
			Document doc = builder.parse("src/main/resources/Files/alumnos_actualizado.xml");
			
			// normalizar el documento xml eliminando nodos de texto vacios, innecesarios: tabulaciones, saltos de línea...
			doc.getDocumentElement().normalize();
			
			//Evitamos alumnos con el mismo id
			Set<String> idsExistentes = new HashSet<>();
			NodeList listaAlumno = doc.getElementsByTagName("alumno");
			for(int i=0; i<listaAlumno.getLength(); i++) {
				Element alumno = (Element)listaAlumno.item(i);
				idsExistentes.add(alumno.getAttribute("id"));
			}
			
			InputStream csv = Importar_CSV_DOM.class.getClassLoader().getResourceAsStream("resources/Files/nuevos_alumnos.csv");
			
			CSVReader reader = new CSVReader(new InputStreamReader(csv));
			
//			CSVReader reader = new CSVReader(new FileReader("src/main/resources/nuevos_alumnos.csv"));
			
			//Leo la cabecera pero la ignoro
			reader.readNext();
			
			Element raiz = doc.getDocumentElement();
			
			String[] linea; 
			while((linea=reader.readNext())!=null) {
				String id = linea[0];
				if (idsExistentes.contains(id)) {
					System.out.println("ID duplicado: " + id + " (alumno no insertado");
					continue; // Salta a la siguiente línea del CSV 
				}
				idsExistentes.add(id);
								
				//Creamos el nodo <alumno>
				Element alumno = doc.createElement("alumno");
				alumno.setAttribute("id", id);
				alumno.appendChild(crearElemento(doc, "nombre", linea[1]));
				alumno.appendChild(crearElemento(doc, "curso", linea[2]));
				alumno.appendChild(crearElemento(doc, "cuota", obtenerCuota(linea[2])));
				
				Element domiciliacion = doc.createElement("domiciliacion");
				domiciliacion.appendChild(crearElemento(doc, "iban", linea[3]));
				domiciliacion.appendChild(crearElemento(doc, "banco", linea[4]));
				alumno.appendChild(domiciliacion);
				
				Element pagos = doc.createElement("pagos");
				alumno.appendChild(pagos);
				
				raiz.appendChild(alumno);
			}
			
			reader.close();
			
			// Hacemos persistente en un documento xml el DOM que hemos ido construyendo en memoria
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			
			transformer.transform(new DOMSource(doc), new StreamResult("src/main/resources/Files/alumnos_actualizado.xml"));
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvValidationException e) {
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

	private static String obtenerCuota(String curso) {
		switch(curso.toLowerCase()) {
		case "canto": 
			return "120";
		case "guitarra": 
			return "150";
		case "piano": 
			return "100";
		case "batería": 
			return "180";
		default:
			return "0";
		
		}
	}

	private static Element crearElemento(Document doc, String nombre, String valor) {
		Element elemento = doc.createElement(nombre);
		elemento.setTextContent(valor);
		return elemento;
	}
}
