package proyectoMavenDAM2A25_26.ejercicios_DOM;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

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
import org.w3c.dom.Text;

import _5_ficheros.Persona;
import proyectoMavenDAM2A25_26.utilidades.Utilidades;

public class Ejercicio10 {
	private final static String DOCTRABAJO_IN =  "/resources/serializaPersona";
	private final static String DOCTRABAJO_OUT = Utilidades.getRuta() + Utilidades.getRutaDom() 
	+ "FicheroPersonasSerializado.xml";
	
	public static void main(String[] args) {
		Document doc = null;
		Transformer t = null;
		
		try (ObjectInputStream oIS = new ObjectInputStream(
				Ejercicio10.class.getResourceAsStream(DOCTRABAJO_IN))){
			
			DocumentBuilder dB = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			doc = dB.newDocument();
			
			t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			
			Element elementoRaiz = doc.createElement("personas");
			doc.appendChild(elementoRaiz);
			
			Persona persona = new Persona();
			while(true) {
				persona = (Persona) oIS.readObject();
				Element elementoPersona = doc.createElement("persona");
				CreaElemento("nombre", persona.getNombre().toString(), elementoPersona, doc);
				CreaElemento("apellido1", persona.getApellido1().toString(), elementoPersona, doc);
				CreaElemento("apellido2", persona.getApellido2().toString(), elementoPersona, doc);
				CreaElemento("nacimiento", persona.getNacimiento().toString(), elementoPersona, doc);
				elementoRaiz.appendChild(elementoPersona);
			}
		}catch (EOFException e) {
			System.out.println("Fin de fichero");
			try {
				t.transform(new DOMSource(doc), new StreamResult(new File(DOCTRABAJO_OUT)));
			} catch (TransformerException e1) {
				e1.printStackTrace();
			}
		}catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * Crea la etiqueta XML con su contenido textual y lo añade a su etiqueta padre
	 * @param etiqueta nombre de la etiqueta XML a crear
	 * @param contenidoEtiqueta contenido textual de la etiqueta con nombre etiqueta
	 * @param padre elemento padre del que estamos creando con nombre etiqueta
	 * @param documentoDOM documento DOM en memoria donde creamos todos los elementos: etiqueta y nodo de texto
	 */

	private static void CreaElemento(String etiqueta, String contenidoEtiqueta, Element padre, Document documentoDOM) {
		Element elem = documentoDOM.createElement(etiqueta);
		Text texto = documentoDOM.createTextNode(contenidoEtiqueta);
		padre.appendChild(elem);
		elem.appendChild(texto);
	}

}
