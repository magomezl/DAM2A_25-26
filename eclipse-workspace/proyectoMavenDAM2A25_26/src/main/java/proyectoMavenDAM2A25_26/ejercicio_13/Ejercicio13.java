package proyectoMavenDAM2A25_26.ejercicio_13;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import proyectoMavenDAM2A25_26.utilidades.Utilidades;

public class Ejercicio13 {
	private final static String FICHEROTRABAJO_XSL = "ejercicio13.xsl";
	private final static String FICHEROTRABAJO_XML = "ejercicio13.xml";
	private final static String FICHEROSALIDA_HTML = "index.html";

	public static void main(String[] args) {
		try {
			TransformerFactory tF = TransformerFactory.newInstance();
			Transformer t = tF.newTransformer(new StreamSource(Utilidades.getRuta() + Utilidades.getRutaXmlXsl()+FICHEROTRABAJO_XSL));
			t.transform(new StreamSource(Utilidades.getRuta()+ Utilidades.getRutaXmlXsl()+ FICHEROTRABAJO_XML), 
					new StreamResult(Utilidades.getRuta()+ Utilidades.getRutaXmlXsl()+ FICHEROSALIDA_HTML));
			System.out.println("Documento html generado");
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}
}
