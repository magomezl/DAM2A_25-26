package modelo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.TypedQuery;
import modelo.dto.Departamentos;
import modelo.dto.Empleados;

public class EmpleadosImpl implements EmpleadosDAO {
	private static final Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
	private static final SessionFactory sF = cfg.buildSessionFactory();
	private static Session sesion; 
	
	@Override
	public int anadirEmpleado(Empleados empleado) {
		int valor = 0;
		sesion = sF.openSession();
		Transaction t; 
		if (sesion.createQuery("from Empleados where nombre=:name and apellido1=:surname1 and apellido2=:surname2", Empleados.class)
				.setParameter("name", empleado.getNombre())
				.setParameter("surname1", empleado.getApellido1())
				.setParameter("surname2", empleado.getApellido2())
				.list()
				.isEmpty()) {
			t = sesion.beginTransaction();
			sesion.persist(empleado);
			t.commit();
			valor = 1;
		}
		sesion.close();
		return valor;
	}

	@Override
	public int modificarEmpleado(Empleados empleadoOld, Empleados empleadoNew) {
		// TODO Faltan cosas
		int valor = 0;
		sesion = sF.openSession();
		Transaction t = sesion.beginTransaction();
		Empleados empleado = sesion.get(Empleados.class, empleadoOld);
		empleado.setNombre(empleadoNew.getNombre());
		empleado.setApellido1(empleadoNew.getApellido1());
		empleado.setApellido2(empleadoNew.getApellido2());
		empleado.setDepartamentos(empleadoNew.getDepartamentos());
		sesion.merge(empleado);
		t.commit();
		sesion.close();
		return valor;
	}

	@Override
	public int modificarEmpleado(int empleadoOld, Empleados empleadoNew) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int eliminarEmpleado(int empleadoOld) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Empleados> listarEmpleados() {
		sesion = sF.openSession();
		List<Empleados> lista = sesion.createQuery("from Empleados", Empleados.class).list();
		sesion.close();
		return lista;
	}

	@Override
	public List<Empleados> listarEmpleados(String localidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Empleados> listarEmpleadosNombreDpto(String nombreDpto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Empleados> listarEmpleados(String nombre, String apellido1, String apellido2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Empleados> listarEmpleados(Departamentos dpto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Empleados listarEmpleados(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
