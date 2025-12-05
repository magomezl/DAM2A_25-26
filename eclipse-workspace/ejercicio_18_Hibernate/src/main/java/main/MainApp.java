package main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import modelo.dao.DepartamentoDAO;
import modelo.dao.DepartamentoDAOImpl;
import modelo.dto.Departamentos;

public class MainApp {
	private static DepartamentoDAO dptoDAO = new DepartamentoDAOImpl();
	
	public static void main(String[] args) {
		
		Departamentos dpto = new Departamentos("Filosofía", "Valladolid", null);
		dptoDAO.anadirDpto(dpto);
		
		System.out.println(dptoDAO.listarDptos());
		
		
		
		
		

	}

}
