package modelo.dao;

import java.util.ArrayList;

import modelo.dto.AlumnoDTO;
import modelo.dto.EmpresaDTO;

public interface EmpresaDAO {
	int anadirEmpresa(EmpresaDTO empresa);
	ArrayList<EmpresaDTO> listarEmpresas();

}
