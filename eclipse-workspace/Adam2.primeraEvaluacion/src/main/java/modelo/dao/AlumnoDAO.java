package modelo.dao;

import java.util.ArrayList;

import modelo.dto.AlumnoDTO;
import modelo.dto.EmpresaDTO;

public interface AlumnoDAO {
	int anadirAlumno(AlumnoDTO alumno);
	ArrayList<AlumnoDTO> listarAlumnos();

}
