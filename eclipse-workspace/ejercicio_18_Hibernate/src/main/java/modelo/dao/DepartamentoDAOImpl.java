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

	@Override
	public void modificarDpto(Departamentos dptoOld, Departamentos dptoNew) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarDpto(int dptoOld, Departamentos dptoNew) {
		sesion = sF.openSession();
		Transaction t = sesion.beginTransaction();
		Departamentos dpto = sesion.get(Departamentos.class, dptoOld);
		dpto.setDnombre(dptoNew.getDnombre());
		dpto.setLoc(dptoNew.getLoc());
		sesion.merge(dpto);
		t.commit();
		sesion.close();
	}

	@Override
	public void eliminarDpto(int dptoOld) {
		sesion = sF.openSession();
		Transaction t = sesion.beginTransaction();
		Departamentos dpto = sesion.get(Departamentos.class, dptoOld);
		sesion.remove(dpto);
		t.commit();
		sesion.close();
	}

	@Override
	public List<Departamentos> listarDptos(String localidad) {
		sesion = sF.openSession();
		TypedQuery<Departamentos> consulta = sesion.createQuery("from Departamentos where loc=:localidad", Departamentos.class)
				.setParameter("localidad", localidad);
		
		List<Departamentos> lista = consulta.getResultList();
		sesion.close();
		return lista;
	}

	

}
