package modelo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.TypedQuery;
import modelo.dto.Departamentos;

public class DepartamentoDAOImpl implements DepartamentoDAO {
	private static final Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
	private static final SessionFactory sF = cfg.buildSessionFactory();
	private static Session sesion; 
	
	@Override
	public void anadirDpto(Departamentos dpto) {
		sesion = sF.openSession();
		Transaction t = sesion.beginTransaction();
		sesion.persist(dpto);
		t.commit();
		sesion.close();
		
	}

	@Override
	public List<Departamentos> listarDptos() {
		sesion = sF.openSession();
		TypedQuery<Departamentos> consulta = sesion.createQuery("from Departamentos", Departamentos.class);
		List<Departamentos> lista = consulta.getResultList();
		sesion.close();
		return lista;
	}

}
