package main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import modelo.dao.DepartamentoDAO;
import modelo.dao.DepartamentoDAOImpl;
import modelo.dto.Departamentos;

public class Main {
	private static DepartamentoDAO dptoDAO = new DepartamentoDAOImpl();
	
	public static void main(String[] args) {
		
//		System.out.println("\n\nLISTADO ANTES " + dptoDAO.listarDptos());
		Departamentos dpto = new Departamentos("Ventas", "Valladolid", null);
//		dptoDAO.anadirDpto(dpto);
//		System.out.println("\n\nLISTADO DESPUES DE ANADIR " + dptoDAO.listarDptos());
//		
//		System.out.println("\n\nLISTADO POR LOCALIDAD VALLADOLID" + dptoDAO.listarDptos("Valladolid"));
//		
//		System.out.println("\n\nLISTADO POR NOMBRE DIGITALIZACIÓN" + dptoDAO.listarDptosNombre("Digitalización"));
//		
//		System.out.println("\\n\\nLISTADO POR NOMBRE Y LOCALIDAD DEVOLVIENDO ID" + dptoDAO.listarDptos("Tarragona", "Centralita"));
//
//		dpto = new Departamentos("Dineros", "Pucela", null);
//		dptoDAO.modificarDpto(12, dpto);
//		
//		System.out.println("\n\nLISTADO DESPUES DE MODIFICAR EL 12 (Dineros, Pucela)" + dptoDAO.listarDptos());
		
		System.out.println("\n\nLISTADO " + dptoDAO.listarDptos());
		
//		Departamentos dptoOld = dptoDAO.listarDptosLocNom("Bailes", "Valencia");
//		dpto.setDnombre("Finanzas");
//		dptoDAO.modificarDpto(dptoOld, dpto);
//		
//		System.out.println("\n\nLISTADO DESPUES DE MODIFICAR " + dptoDAO.listarDptos());
//		
//		dptoDAO.eliminarDpto(13);
//		System.out.println("\n\nLISTADO DESPUES DE BORRAR EL 13 " + dptoDAO.listarDptos());
	
	}

}
