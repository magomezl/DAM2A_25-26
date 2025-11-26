package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.db.Conexion;
import modelo.dto.AlumnoDTO;

public class AlumnoDAOImpl implements AlumnoDAO {

	@Override
	public int anadirAlumno(AlumnoDTO alumno) {
		try(PreparedStatement sentencia0 = Conexion.getInstance().getCon().prepareStatement(
				"SELECT * FROM alumnos WHERE nombre LIKE ? AND apellidos LIKE ? ");
			PreparedStatement sentencia = Conexion.getInstance().getCon().prepareStatement(
				"INSERT INTO alumnos (nombre, apellidos, ciclo, curso, id_empresa) VALUES (?, ?, ?, ?, ?);", PreparedStatement.RETURN_GENERATED_KEYS)) {
			
			sentencia0.setString(1, alumno.getNombre());
			sentencia0.setString(2, alumno.getApellidos());
			
			try (ResultSet resultado = sentencia0.executeQuery()){
				// Ya existe el alumno 
				if (resultado.next()){
					return 0;
				}
			}
			sentencia.setString(1, alumno.getNombre());
			sentencia.setString(2, alumno.getApellidos());
			sentencia.setString(3, alumno.getCiclo());
			sentencia.setString(4, alumno.getCurso());
			sentencia.setInt(5, alumno.getId_empresa());
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
	public ArrayList<AlumnoDTO> listarAlumnos() {
		ArrayList<AlumnoDTO> alAlumnos = new ArrayList<AlumnoDTO>();;
		try(PreparedStatement sentencia = Conexion.getInstance().getCon().prepareStatement(
				"SELECT * FROM alumnos")) {
			ResultSet resultado = sentencia.executeQuery();
			while(resultado.next()) {
				alAlumnos.add(new AlumnoDTO(resultado.getInt(1), resultado.getString(2), resultado.getString(3), 
						resultado.getString(4), resultado.getString(5), resultado.getInt(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alAlumnos;
	}

}
