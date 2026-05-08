package Taller2;

import java.util.ArrayList;

public class MiembroAltoMando {
	private int numero;
	private String nombre;
	private ArrayList<Pokemon> pokemones;

	public MiembroAltoMando(int numero, String nombre) {
		this.numero = numero;
		this.nombre = nombre;
		this.pokemones = new ArrayList<Pokemon>();
	}

	public int getNumero() {
		return numero;
	}

	public String getNombre() {
		return nombre;
	}

	public ArrayList<Pokemon> getPokemones() {
		return pokemones;
	}

	// agrega un pokemon al miembro del alto mando
	public void agregarPokemon(Pokemon pokemon) {
		pokemones.add(pokemon);
	}
}
