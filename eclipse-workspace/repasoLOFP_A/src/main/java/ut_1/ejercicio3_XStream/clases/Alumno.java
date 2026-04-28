package ut_1.ejercicio3_XStream.clases;

import java.util.List;

public class Alumno {
	private String id;
	private String nombre;
	private String curso;
	private double cuota;
	private Domiciliacion domiciliacion;
	private List<Pago> pagos;
	
	public String getId() {	return id; 	}
	public String getNombre() { return nombre; 	}
	public String getCurso() { 	return curso; }
	public double getCuota() {	return cuota; }
	public Domiciliacion getDomiciliacion() { return domiciliacion; }
	public List<Pago> getPagos() { return pagos; }
}
