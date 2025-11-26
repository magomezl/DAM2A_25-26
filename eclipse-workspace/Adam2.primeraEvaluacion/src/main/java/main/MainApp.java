package main;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import modelo.dao.AlumnoDAO;
import modelo.dao.AlumnoDAOImpl;
import modelo.dao.EmpresaDAO;
import modelo.dao.EmpresaDAOImpl;
import modelo.dao.Manejador;

public class MainApp {
	
	
	public static void main(String[] args) {
		try {
			SAXParserFactory sPF = SAXParserFactory.newInstance();
			SAXParser sP = sPF.newSAXParser();
			sP.parse(MainApp.class.getResourceAsStream("/resources/agenda.xml"), new Manejador());
			//Comprobación
			AlumnoDAO aluDAO = new AlumnoDAOImpl();
			EmpresaDAO empDAO = new EmpresaDAOImpl();
			System.out.println(empDAO.listarEmpresas());
			System.out.println(aluDAO.listarAlumnos());
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
