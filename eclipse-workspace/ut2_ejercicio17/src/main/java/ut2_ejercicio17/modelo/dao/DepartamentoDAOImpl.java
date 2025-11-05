package ut2_ejercicio17.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import ut2_ejercicio17.modelo.dto.DepartamentoDTO;

// Para casa acabar los métodos de esta clase y crear la clase singleton

public class DepartamentoDAOImpl implements DepartamentoDAO {
	private Connection con;
	
	@Override
	public int anadirDpto(DepartamentoDTO dpto) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://10.196.55.103:3306/empresa", "root", "toor");
			
			PreparedStatement sentencia = con.prepareStatement("INSERT INTO `empresa`.`departamentos` (`dnombre`, `loc`) VALUES (?, ?);");
			sentencia.setString(1, dpto.getDepNombre());
			sentencia.setString(2, dpto.getDepLocalidad());
			return sentencia.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int eliminarDpto(int dptoNum) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modificarDpto(int dptoNum, DepartamentoDTO dpto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<DepartamentoDTO> listarDptos() {
		// TODO Auto-generated method stub
		return null;
	}

}
