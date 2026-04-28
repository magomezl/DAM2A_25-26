package ut_1.ejercicio3_XStream.clases;

public class Pago {
	private String mes;
    private String estado;
    private double importe;

    public String getMes() { return mes; }
//    public String getEstado() { return estado; }
    public boolean estaPagado() { return this.estado.equalsIgnoreCase("pagado"); }
    public double getImporte() { return importe; }
}
