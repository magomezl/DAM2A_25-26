package proyectoMavenDAM2A25_26.ejercicio_11;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import proyectoMavenDAM2A25_26.utilidades.Utilidades;

public class Ejercicio11_bis {
	private final static String FICHEROTRABAJO = "Ejercicio11.xml";
	
	public static void main(String[] args) {
		
		try {
			SAXParserFactory sPF = SAXParserFactory.newInstance();
			SAXParser sP = sPF.newSAXParser();
			sP.parse(new InputSource(Utilidades.getRuta() + Utilidades.getRutaSax() + FICHEROTRABAJO), new Manejador_bis("SOPA DE CEBOLLA"));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
}
