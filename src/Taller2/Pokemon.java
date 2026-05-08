package Taller2;

public class Pokemon {
	private String nombre;
	private String habitat;
	private double porcentajeAparicion;
	private int vida;
	private int ataque;
	private int defensa;
	private int ataqueEspecial;
	private int defensaEspecial;
	private int velocidad;
	private String tipo;
	private String estado;

	public Pokemon(String nombre, String habitat, double porcentajeAparicion, int vida, int ataque, int defensa,
			int ataqueEspecial, int defensaEspecial, int velocidad, String tipo) {
		this.nombre = nombre;
		this.habitat = habitat;
		this.porcentajeAparicion = porcentajeAparicion;
		this.vida = vida;
		this.ataque = ataque;
		this.defensa = defensa;
		this.ataqueEspecial = ataqueEspecial;
		this.defensaEspecial = defensaEspecial;
		this.velocidad = velocidad;
		this.tipo = tipo;
		this.estado = "Vivo";
	}

	public Pokemon(Pokemon otro) {
		this.nombre = otro.nombre;
		this.habitat = otro.habitat;
		this.porcentajeAparicion = otro.porcentajeAparicion;
		this.vida = otro.vida;
		this.ataque = otro.ataque;
		this.defensa = otro.defensa;
		this.ataqueEspecial = otro.ataqueEspecial;
		this.defensaEspecial = otro.defensaEspecial;
		this.velocidad = otro.velocidad;
		this.tipo = otro.tipo;
		this.estado = "Vivo";
	}

	public String getNombre() {
		return nombre;
	}

	public String getHabitat() {
		return habitat;
	}

	public double getPorcentajeAparicion() {
		return porcentajeAparicion;
	}

	public int getVida() {
		return vida;
	}

	public int getAtaque() {
		return ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public int getAtaqueEspecial() {
		return ataqueEspecial;
	}

	public int getDefensaEspecial() {
		return defensaEspecial;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public String getTipo() {
		return tipo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getStatsTotales() {
		return vida + ataque + defensa + ataqueEspecial + defensaEspecial + velocidad;
	}

	public boolean estaVivo() {
		return estado.equalsIgnoreCase("Vivo");
	}

	@Override
	public String toString() {
		return nombre + "|" + tipo + "|Stats totales: " + getStatsTotales() + "|Estado: " + estado;
	}
}
