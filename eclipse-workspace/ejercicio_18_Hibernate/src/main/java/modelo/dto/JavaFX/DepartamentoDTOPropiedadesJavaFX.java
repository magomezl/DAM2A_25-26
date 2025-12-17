package modelo.dto.JavaFX;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * clase que vamos a utilizar para interactuar con la vista JavaFX. FavaFX utiliza propiedades Observables IntegerProperty, StringProperty...
 * no los tipos de Java
 */
public class DepartamentoDTOPropiedadesJavaFX {
	private IntegerProperty depNum;
	private StringProperty depNombre;
	private StringProperty depLocalidad;
	
	public DepartamentoDTOPropiedadesJavaFX() {
	}

	/**
	 * Le paso los datos del modelo, es decir, los de los objetos Hibernate 
	 * @param depNum
	 * @param depNombre
	 * @param depLocalidad
	 */
	
	public DepartamentoDTOPropiedadesJavaFX(int depNum, String depNombre, String depLocalidad) {
		this.depNum = new SimpleIntegerProperty(depNum);
		this.depNombre = new SimpleStringProperty(depNombre);
		this.depLocalidad = new SimpleStringProperty(depLocalidad);
	}

	public int getDepNum() {
		return depNum.get();
	}

	public void setDepNum(int depNum) {
		this.depNum.set(depNum);
	}

	public String getDepNombre() {
		return depNombre.get();
	}

	public void setDepNombre(String depNombre) {
		this.depNombre.set(depNombre);
	}

	public String getDepLocalidad() {
		return depLocalidad.get();
	}

	public void setDepLocalidad(String depLocalidad) {
		this.depLocalidad.set(depLocalidad); 
	}
	
	

}
