package modelo.dto.ObservablesJFX;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LibroDTOPropiedadesJavaFX {
	private IntegerProperty num;
	private StringProperty titulo;
	private StringProperty genero;
	
	public LibroDTOPropiedadesJavaFX() {
	
	}

	public LibroDTOPropiedadesJavaFX(int num, String titulo, String genero) {
		this.num = new SimpleIntegerProperty(num);
		this.titulo = new SimpleStringProperty(titulo);
		this.genero = new SimpleStringProperty(genero);
	}

	public int getNum() {
		return num.get();
	}

	public void setNum(int num) {
		this.num.set(num);
	}

	public String getTitulo() {
		return titulo.get();
	}

	public void setTitulo(String titulo) {
		this.titulo.set(titulo);
	}

	public String getGenero() {
		return genero.get();
	}

	public void setGenero(String genero) {
		this.genero.set(genero)
		;
	}

	
	
	
	
}
