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
//	private final static String DOCTRABAJO_IN = Utilidades.getRuta() + Utilidades.getRutaDom() + "SerializaPersona";
	private final static String DOCTRABAJO_IN =  "/resources/serializaPersona";
	private final static String DOCTRABAJO_OUT = Utilidades.getRuta() + Utilidades.getRutaDom() 
	+ "FicheroPersonasSerializado.xml";
	
	public static void main(String[] args) {
		Document doc = null;
		Transformer t = null;
		
		try (ObjectInputStream oIS = new ObjectInputStream(
				Ejercicio10.class.getResourceAsStream(DOCTRABAJO_IN))){
				if (oIS == null) {
					System.out.println("No se encuentra el recurso");
				}
				
//				new FileInputStream
//				(new File(DOCTRABAJO_IN)))){
			
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
			
				Element elem = doc.createElement("nombre");
				Text texto = doc.createTextNode(persona.getNombre().toString());
				elementoPersona.appendChild(elem);
				elem.appendChild(texto);
				//....
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

}
