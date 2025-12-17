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
		Transaction t; 
		if (sesion.createQuery("from Departamentos where loc=:localidad and dnombre=:nombre", Departamentos.class)
				.setParameter("localidad", dpto.getLoc())
				.setParameter("nombre", dpto.getDnombre())
				.list()
				.isEmpty()) {
			t = sesion.beginTransaction();
			sesion.persist(dpto);
			t.commit();
		}
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
	public List<Departamentos> listarDptos(String localidad) {
		sesion = sF.openSession();
		List<Departamentos> lista = sesion.createQuery("from Departamentos where loc=:localidad", Departamentos.class)
				.setParameter("localidad", localidad)
				.list();
		sesion.close();
		return lista;
	}

	@Override
	public List<Departamentos> listarDptosNombre(String nombre) {
		sesion = sF.openSession();
		List<Departamentos> lista = sesion.createQuery("from Departamentos where dnombre=:nombre", Departamentos.class)
				.setParameter("nombre", nombre)
				.getResultList();
		sesion.close();
		return lista;
	}

	@Override
	public int listarDptos(String nombre, String localidad) {
		sesion = sF.openSession();
		TypedQuery<Departamentos> consulta = sesion.createQuery("from Departamentos where loc=:localidad and dnombre=:nombre", Departamentos.class)
				.setParameter("localidad", localidad)
				.setParameter("nombre", nombre);
		Departamentos dpto = consulta.getResultList().get(0);
		sesion.close();
		return dpto.getDeptNo();
	}

	
	@Override
	public Departamentos listarDptosLocNom(String nombre, String localidad) {
		sesion = sF.openSession();
		TypedQuery<Departamentos> consulta = sesion.createQuery("from Departamentos where loc=:localidad and dnombre=:nombre", Departamentos.class)
				.setParameter("localidad", localidad)
				.setParameter("nombre", nombre);
		Departamentos dpto = consulta.getResultList().get(0);
		sesion.close();
		return dpto;
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
	public void modificarDpto(Departamentos dptoOld, Departamentos dptoNew) {
		sesion = sF.openSession();
		Transaction t = sesion.beginTransaction();
		dptoOld.setDnombre(dptoNew.getDnombre());
		dptoOld.setLoc(dptoNew.getLoc());
		sesion.merge(dptoOld);
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
	public void eliminarDpto(Departamentos dpto) {
		sesion = sF.openSession();
		Transaction t = sesion.beginTransaction();
		sesion.remove(dpto);
		t.commit();
		sesion.close();
	}

}
