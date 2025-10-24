package proyectoMavenDAM2A25_26.ejercicio_14;

import java.util.ArrayList;

import _5_ficheros.Persona;

public class ListaPersonas {
	private ArrayList<Persona> lista = new ArrayList<Persona>();
	
	public void anadir(Persona person) {
		lista.add(person);
	}

	public ArrayList<Persona> getLista() {
		return lista;
	}
}
