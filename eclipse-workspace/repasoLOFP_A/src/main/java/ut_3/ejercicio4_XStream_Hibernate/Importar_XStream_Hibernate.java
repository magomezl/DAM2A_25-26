package ut_3.ejercicio4_XStream_Hibernate;

import java.io.InputStream;
import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import clasesHibernate.Alumnos;
import clasesHibernate.Cursos;
import clasesHibernate.Matriculas;
import clasesHibernate.Pagos;
import ut_1.ejercicio3_XStream.clases.Academia;
import ut_1.ejercicio3_XStream.clases.Alumno;
import ut_1.ejercicio3_XStream.clases.Domiciliacion;
import ut_1.ejercicio3_XStream.clases.Pago;

public class Importar_XStream_Hibernate {
	private static SessionFactory sf = new Configuration().configure().buildSessionFactory();
	
	public static void main(String[] args) {
		//Cargamos los datos que tenemos en el documento xml en el javabean XStream
		
		XStream xs = new XStream(new DomDriver("UTF-8"));
		// Damos permiso a XStream para que acceda a las clases del JavaBean que hemos creado
		xs.addPermission(AnyTypePermission.ANY);
		
		// Definimos las reglas de correspondencia entre las clases XStream que hemos creado y el documento xml
		// Indicamos la correspondencia entre etiquetas y clases
		xs.alias("academia", Academia.class);
		xs.alias("alumno", Alumno.class);
		xs.alias("domiciliacion", Domiciliacion.class);
		xs.alias("pago", Pago.class);
		// Indicamos las propiedades de las clases que se corresponden con atributos de etiquetas
		xs.useAttributeFor(Alumno.class, "id");
		
		xs.useAttributeFor(Pago.class, "mes");
		xs.useAttributeFor(Pago.class, "estado");
		xs.useAttributeFor(Pago.class, "importe");
		
		// Para que no considere una etiqueta wrapper <alumnos>  
		xs.addImplicitCollection(Academia.class, "alumnos");
		
		InputStream is = Importar_XStream_Hibernate.class.getClassLoader().getResourceAsStream("resources/Files/academia.xml");
		
		Academia academia = (Academia) xs.fromXML(is);
		
		Session sesion = sf.openSession();
		for (Alumno a: academia.getAlumnos()) {
			Transaction tx = sesion.beginTransaction();
			Alumnos alumnoHibernate = new Alumnos();
			alumnoHibernate.setId(a.getId());
			alumnoHibernate.setNombre(a.getNombre());
			alumnoHibernate.setIban(a.getDomiciliacion().getIban());
			alumnoHibernate.setBanco(a.getDomiciliacion().getBanco());
			
			sesion.persist(alumnoHibernate);
			
			//Buscar el curso en la DB
			Cursos curso = (Cursos) sesion.createQuery("FROM Cursos c WHERE c.denominacion = :curso")
					.setParameter("curso", a.getCurso())
					.uniqueResult();
			
			if (curso == null) {
				curso = new Cursos();
				curso.setDenominacion(a.getCurso());
				curso.setCuotaMensual(BigDecimal.valueOf(a.getCuota()));
				sesion.persist(curso);
			}
			
			Matriculas matricula = new Matriculas(alumnoHibernate, curso);
			sesion.persist(matricula);
			
			for (Pago p: a.getPagos()) {
				Pagos pagoHibernate = new Pagos();
				pagoHibernate.setAlumnos(alumnoHibernate);
				pagoHibernate.setCursos(curso);
				pagoHibernate.setMes(p.getMes());
				pagoHibernate.setEstado(p.estaPagado());
				pagoHibernate.setImporte(BigDecimal.valueOf(p.getImporte()));
				sesion.persist(pagoHibernate);
			}
			
			tx.commit();
		}
		sesion.close();
		sf.close();
	}

}
