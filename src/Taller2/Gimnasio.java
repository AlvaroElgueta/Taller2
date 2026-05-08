package Taller2;

import java.util.ArrayList;

public class Gimnasio {
	private int numero;
	private String lider;
	private String estado;
	private ArrayList<Pokemon> pokemones;

	public Gimnasio(int numero, String lider, String estado) {
		this.numero = numero;
		this.lider = lider;
		this.estado = estado;
		this.pokemones = new ArrayList<Pokemon>();
	}

	public int getNumero() {
		return numero;
	}

	public String getLider() {
		return lider;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public ArrayList<Pokemon> getPokemones() {
		return pokemones;
	}

	// agrega un pokemon al gimnasio
	public void agregarPokemon(Pokemon pokemon) {
		pokemones.add(pokemon);
	}
}
