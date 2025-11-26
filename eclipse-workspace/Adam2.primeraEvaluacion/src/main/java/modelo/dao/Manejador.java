package modelo.dao;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import modelo.dto.AlumnoDTO;
import modelo.dto.EmpresaDTO;

public class Manejador extends DefaultHandler {
	EmpresaDAO empresaDAO = new EmpresaDAOImpl();
	AlumnoDAO alumnoDAO = new AlumnoDAOImpl();
	int claveEmpresa;
	boolean alumnosAsignados = false;
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch(qName) {
		case "empresa":
			EmpresaDTO empresa= new EmpresaDTO();
			empresa.setNombre(attributes.getValue("nombre"));
			empresa.setDireccion(attributes.getValue("direccion"));
			empresa.setTelefono(attributes.getValue("telefono"));
			empresa.setPersona_contacto(attributes.getValue("persona_contacto"));
			empresa.setEmail(attributes.getValue("email"));
			claveEmpresa = empresaDAO.anadirEmpresa(empresa);
			break;
		case "alumnos_asignados":
			alumnosAsignados = true;
			break;
		case "alumno":
			AlumnoDTO alumno = new AlumnoDTO();
			alumno.setNombre(attributes.getValue("nombre"));
			alumno.setApellidos(attributes.getValue("apellidos"));
			alumno.setCiclo(attributes.getValue("ciclo"));
			alumno.setCurso(attributes.getValue("curso"));
			if (alumnosAsignados) {
				alumno.setId_empresa(claveEmpresa);
			}
			alumnoDAO.anadirAlumno(alumno);
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("alumnos_asignados")) {
			alumnosAsignados = false;
		}
	}

	
	
	
	
	

}
