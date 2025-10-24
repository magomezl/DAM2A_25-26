package proyectoMavenDAM2A25_26.ejercicio_14;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import _5_ficheros.Persona;
import _5_ficheros._5_Ejercicio;
import proyectoMavenDAM2A25_26.utilidades.Utilidades;

public class Ejercicio14 {
	private final static String FICHEROTRABAJO_IN  = "/resources/serializaPersona";
	private final static String FICHEROTRABAJO_OUT = "Ejercicio14.xml";
	private final static String FICHEROTRABAJO_IN_2 = "ejercicio14_in.xml";
	private static XStream xS = new XStream(new DomDriver("UTF-8"));

	public static void main(String[] args) {
		//Importante añadir a apuntes
		xS.addPermission(AnyTypePermission.ANY);
		generaXML();
		serializa_desde_XML();
	}

	private static void serializa_desde_XML() {
		try {
			ListaPersonas lP = new ListaPersonas();
			defineEstructuraXML();
			
			ObjectOutputStream OoS = _5_Ejercicio.inicializar(Utilidades.getRutaWorkspace() + "_1_Ficheros\\resources\\serializaPersona");
			lP = (ListaPersonas) xS.fromXML(new FileInputStream(Utilidades.getRuta() + Utilidades.getRutaXstream() + FICHEROTRABAJO_IN_2));
			for(Persona person: lP.getLista()) {
				_5_Ejercicio.escribirObjeto(OoS, person);
			}
			
			_5_Ejercicio.leerObjetos(Utilidades.getRutaWorkspace() + "_1_Ficheros\\resources\\serializaPersona");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void generaXML() {
		
		ListaPersonas lP = new ListaPersonas();
		try (ObjectInputStream oIS = new ObjectInputStream(
				Ejercicio14.class.getResourceAsStream(FICHEROTRABAJO_IN))) {
			while(true) {
				Persona person = (Persona) oIS.readObject();
				lP.anadir(person);
			}
		} catch (IOException e) {
				//Mejor ponemos aquí la línea del finally
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				defineEstructuraXML();
				xS.toXML(lP, new FileOutputStream(Utilidades.getRuta() + Utilidades.getRutaXstream()+FICHEROTRABAJO_OUT));
			} catch (FileNotFoundException e) {
				System.out.println("Fichero no encontrado");
			}
		}
	}

	private static void defineEstructuraXML() {
		xS.alias("familia", ListaPersonas.class);
		xS.addImplicitCollection(ListaPersonas.class, "lista");
		xS.alias("miembro", Persona.class);
		xS.aliasField("primerApellido", Persona.class, "apellido1");
		xS.aliasField("segundoApellido", Persona.class, "apellido2");
//		xS.aliasField("name", Persona.class, "nombre");
		xS.useAttributeFor(Persona.class, "nombre");
	}
		


}
