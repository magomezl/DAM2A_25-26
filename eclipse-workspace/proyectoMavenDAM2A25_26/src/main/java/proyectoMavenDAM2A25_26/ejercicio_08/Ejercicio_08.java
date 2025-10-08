package proyectoMavenDAM2A25_26.ejercicio_08;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import proyectoMavenDAM2A25_26.utilidades.Utilidades;

public class Ejercicio_08 {
	
	private final static String DOCTRABAJO_IN = 
			"ejercicio_08" + System.getProperty("file.separator") + "ejercicio8.xml";
	private final static String DOCTRABAJO_OUT = 
			"ejercicio_08" + System.getProperty("file.separator") + "ejercicio8_URI.xml";

	public static void main(String[] args) {
		try {
			DocumentBuilder dB =  DocumentBuilderFactory.newInstance().newDocumentBuilder();

			Document doc = dB.parse(new File(Utilidades.getRuta()+DOCTRABAJO_IN));

			URI uri = new URI("https://www.w3schools.com/xml/cd_catalog.xml");
			Document docWeb = dB.parse(uri.toURL().openStream());
			
			Transformer t = TransformerFactory.newInstance().newTransformer();

			t.transform(new DOMSource(doc), new StreamResult(System.out));

			System.out.println("\n\nDOCUMENTO EN LÍNEA");

			t.transform(new DOMSource(docWeb), new StreamResult(System.out));
			t.transform(new DOMSource(docWeb), new StreamResult(new File(Utilidades.getRuta()+DOCTRABAJO_OUT)));
			
		} catch (TransformerException | SAXException | IOException | URISyntaxException | ParserConfigurationException e) {
			System.out.println("Error");
			e.printStackTrace();
		}

	}

}
