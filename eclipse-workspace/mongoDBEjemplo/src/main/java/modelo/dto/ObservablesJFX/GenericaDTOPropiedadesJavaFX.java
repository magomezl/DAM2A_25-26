package modelo.dto.ObservablesJFX;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GenericaDTOPropiedadesJavaFX {
	private IntegerProperty num;
	private StringProperty nombre;
	
	public GenericaDTOPropiedadesJavaFX() {
	
	}

	public GenericaDTOPropiedadesJavaFX(String nombre) {
		this.nombre = new SimpleStringProperty(nombre);
	}

	public GenericaDTOPropiedadesJavaFX(int num, String nombre) {
		this.num = new SimpleIntegerProperty(num);
		this.nombre = new SimpleStringProperty(nombre);
	}
 
	public int getNum() {
		return num.get();
	}

	public void setNum(int num) {
		this.num.set(num);
	}

	public String getNombre() {
		return nombre.get();
	}

	public void setNombre(String nombre) {
		this.nombre.set(nombre);
	}

	
}
