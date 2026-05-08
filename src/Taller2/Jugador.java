package Taller2;

import java.util.ArrayList;

public class Jugador {
	private String apodo;
	private String progreso;
	private ArrayList<Pokemon> pokemones;

	public Jugador(String apodo, String progreso) {
		this.apodo = apodo;
		this.progreso = progreso;
		this.pokemones = new ArrayList<Pokemon>();
	}

	public String getApodo() {
		return apodo;
	}

	public String getProgreso() {
		return progreso;
	}

	public void setProgreso(String progreso) {
		this.progreso = progreso;
	}

	public ArrayList<Pokemon> getPokemones() {
		return pokemones;
	}

	public void agregarPokemon(Pokemon pokemon) {
		pokemones.add(pokemon);
	}

	// retorna cuantos pokemones estan actualmente en el equipo
	public int getCantidadEquipo() {
		if (pokemones.size() < 6) {
			return pokemones.size();
		}

		return 6;
	}

	// revisa si el jugador ya capturo un pokemon por nombre
	public boolean tienePokemon(String nombre) {
		for (int i = 0; i < pokemones.size(); i++) {
			if (pokemones.get(i).getNombre().equalsIgnoreCase(nombre)) {
				return true;
			}
		}

		return false;
	}

	// intercambia dos pokemones en la lista del jugador
	public void intercambiarPokemones(int pos1, int pos2) {
		Pokemon aux = pokemones.get(pos1);
		pokemones.set(pos1, pokemones.get(pos2));
		pokemones.set(pos2, aux);
	}
}
