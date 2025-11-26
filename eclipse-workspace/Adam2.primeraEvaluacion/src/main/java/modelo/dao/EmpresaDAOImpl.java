package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.db.Conexion;
import modelo.dto.EmpresaDTO;

public class EmpresaDAOImpl implements EmpresaDAO {

	@Override
	public int anadirEmpresa(EmpresaDTO empresa) {
		try(PreparedStatement sentencia0 = Conexion.getInstance().getCon().prepareStatement(
				"SELECT * FROM empresas WHERE nombre LIKE ?");
			PreparedStatement sentencia = Conexion.getInstance().getCon().prepareStatement(
				"INSERT INTO empresas (nombre, direccion, telefono, persona_contacto, email) VALUES (?, ?, ?, ?, ?);", PreparedStatement.RETURN_GENERATED_KEYS)) {
		
			sentencia0.setString(1, empresa.getNombre());
			
			try (ResultSet resultado = sentencia0.executeQuery()){
				// Ya existe la empresa devuelve la clave
				if (resultado.next()){
					return resultado.getInt(1);
				}
			}
			sentencia.setString(1, empresa.getNombre());
			sentencia.setString(2, empresa.getDireccion());
			sentencia.setString(3, empresa.getTelefono());
			sentencia.setString(4, empresa.getPersona_contacto());
			sentencia.setString(5, empresa.getEmail());
			sentencia.executeUpdate();
			try(ResultSet clave = sentencia.getGeneratedKeys()){
				if (clave.next()) {
					return clave.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<EmpresaDTO> listarEmpresas() {
		ArrayList<EmpresaDTO> alEmpresa = new ArrayList<EmpresaDTO>();;
		try(PreparedStatement sentencia = Conexion.getInstance().getCon().prepareStatement(
				"SELECT * FROM empresas")) {
			ResultSet resultado = sentencia.executeQuery();
			while(resultado.next()) {
				alEmpresa.add(new EmpresaDTO(resultado.getInt(1), resultado.getString(2), resultado.getString(3), 
						resultado.getString(4), resultado.getString(5), resultado.getString(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alEmpresa;
	}

}
