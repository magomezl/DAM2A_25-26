package proyectoMavenDAM2A25_26.ejercicios_DOM;

import java.io.File;

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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import proyectoMavenDAM2A25_26.utilidades.Utilidades;

public class Ejercicio_09 {
	private final static String FICHEROSALIDA = Utilidades.getRuta() + Utilidades.getRutaDom() 
				+"ejercicio9.xml";
	
	public static void main(String[] args) {
		try {
			DocumentBuilder dB = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dB.newDocument();

			// Creamos elementos DOM
			Element raiz = doc.createElement("elementoRaiz");
			Element hijo1 = doc.createElement("elementoHijo");
			Element hijo2 = doc.createElement("elementoHijo");
			//Text textoh1 = doc.createTextNode("contenido hijo 1");
			Text textoh2 = doc.createTextNode("contenido hijo 2");
			Attr atributo = doc.createAttribute("nombre");
			atributo.setNodeValue("hijo1");

			// Creamos estructura de árbol DOM
			doc.appendChild(raiz);
			raiz.appendChild(hijo1);
			raiz.appendChild(hijo2);
			hijo1.setTextContent("contenido hijo Gómez");
			//hijo1.appendChild(textoh1);
			hijo1.setAttributeNode(atributo);

			hijo2.appendChild(textoh2);
			hijo2.setAttribute("nombre", "hijo2");
			
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.INDENT, "yes");
//			t.setOutputProperty(OutputKeys.ENCODING, "UTF-16");
//			t.setOutputProperty(OutputKeys.METHOD, "xml");
			t.transform(new DOMSource(doc), new StreamResult(System.out));
			t.transform(new DOMSource(doc), new StreamResult(new File(FICHEROSALIDA)));
		
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
}
