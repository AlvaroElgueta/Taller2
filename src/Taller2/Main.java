package Taller2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	// Alvaro Elgueta Muñoz - 21806097-8 - ICCI
	private static ArrayList<String> habitats = new ArrayList<String>();
	private static ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();
	private static ArrayList<Gimnasio> gimnasios = new ArrayList<Gimnasio>();
	private static ArrayList<MiembroAltoMando> altoMando = new ArrayList<MiembroAltoMando>();

	private static Jugador jugador;
	private static Scanner s;
	private static Random r = new Random();

	public static void main(String[] args) {
		s = new Scanner(System.in);

		try {
			leerHabitats();
			leerPokedex();
			leerGimnasios();
			leerAltoMando();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: No se encontro un archivo necesario.");
			s.close();
			return;
		}

		menuInicial();
		s.close();
	}

	// menu inicial del juego
	private static void menuInicial() {
		boolean salir = false;

		do {
			System.out.println("1) Continuar.");
			System.out.println("2) Nueva Partida.");
			System.out.println("3) Salir.");

			int opcion = leerEnteroEnRango("Ingrese Opcion: ", 1, 3);

			switch (opcion) {
			case 1:
				if (cargarPartida()) {
					System.out.println("Bienvenido " + jugador.getApodo() + "!!");
					menuPrincipal();
				}
				break;

			case 2:
				nuevaPartida();
				System.out.println("Bienvenido " + jugador.getApodo() + "!!");
				menuPrincipal();
				break;

			case 3:
				salir = true;
				System.out.println("Nos vemos entrenador...");
				break;
			}

		} while (!salir);
	}

	// menu principal del juego
	private static void menuPrincipal() {
		boolean salir = false;

		do {
			System.out.println();
			System.out.println(jugador.getApodo() + ", que deseas hacer?");
			System.out.println();
			System.out.println("1) Revisar equipo.");
			System.out.println("2) Salir a capturar.");
			System.out.println("3) Acceso al PC (cambiar Pokémon del equipo).");
			System.out.println("4) Retar un gimnasio.");
			System.out.println("5) Desafío al Alto Mando.");
			System.out.println("6) Curar Pokémon.");
			System.out.println("7) Guardar.");
			System.out.println("8) Guardar y Salir.");

			int opcion = leerEnteroEnRango("Ingrese Opcion: ", 1, 8);

			switch (opcion) {
			case 1:
				revisarEquipo();
				break;

			case 2:
				salirCapturar();
				break;

			case 3:
				accesoPC();
				break;

			case 4:
				retarGimnasio();
				break;

			case 5:
				desafiarAltoMando();
				break;

			case 6:
				curarPokemones();
				break;

			case 7:
				guardarPartida();
				break;

			case 8:
				guardarPartida();
				System.out.println("Nos vemos entrenador...");
				salir = true;
				break;
			}

		} while (!salir);
	}

	// carga los habitats
	private static void leerHabitats() throws FileNotFoundException {
		Scanner archivo = new Scanner(new File("Habitats.txt"));

		while (archivo.hasNextLine()) {
			String linea = archivo.nextLine().trim();

			if (!linea.equals("")) {
				habitats.add(linea);
			}
		}

		archivo.close();
	}

	// carga la pokedex
	private static void leerPokedex() throws FileNotFoundException {
		Scanner archivo = new Scanner(new File("Pokedex.txt"));

		while (archivo.hasNextLine()) {
			String datos[] = archivo.nextLine().split(";");

			if (datos.length == 10) {
				try {
					String nombre = datos[0].trim();
					String habitat = datos[1].trim();
					double porcentaje = Double.parseDouble(datos[2].trim());
					int vida = Integer.parseInt(datos[3].trim());
					int ataque = Integer.parseInt(datos[4].trim());
					int defensa = Integer.parseInt(datos[5].trim());
					int ataqueEspecial = Integer.parseInt(datos[6].trim());
					int defensaEspecial = Integer.parseInt(datos[7].trim());
					int velocidad = Integer.parseInt(datos[8].trim());
					String tipo = datos[9].trim();

					pokedex.add(new Pokemon(nombre, habitat, porcentaje, vida, ataque, defensa, ataqueEspecial,
							defensaEspecial, velocidad, tipo));

				} catch (NumberFormatException e) {
					System.out.println("ERROR: Hay un pokemon con datos invalidos.");
				}
			}
		}

		archivo.close();
	}

	// carga los gimnasios
	private static void leerGimnasios() throws FileNotFoundException {
		Scanner archivo = new Scanner(new File("Gimnasios.txt"));

		while (archivo.hasNextLine()) {
			String datos[] = archivo.nextLine().split(";");

			if (datos.length >= 4) {
				try {
					int numero = Integer.parseInt(datos[0].trim());
					String lider = datos[1].trim();
					String estado = datos[2].trim();
					int cantidad = Integer.parseInt(datos[3].trim());

					Gimnasio gimnasio = new Gimnasio(numero, lider, estado);

					for (int i = 0; i < cantidad; i++) {
						Pokemon pokemon = buscarPokemonPokedex(datos[4 + i].trim());

						if (pokemon != null) {
							gimnasio.agregarPokemon(new Pokemon(pokemon));
						}
					}

					gimnasios.add(gimnasio);

				} catch (NumberFormatException e) {
					System.out.println("ERROR: Hay un gimnasio con datos invalidos.");
				}
			}
		}

		archivo.close();
	}

	// carga el alto mando
	private static void leerAltoMando() throws FileNotFoundException {
		Scanner archivo = new Scanner(new File("Alto Mando.txt"));

		while (archivo.hasNextLine()) {
			String datos[] = archivo.nextLine().split(";");

			if (datos.length >= 8) {
				try {
					int numero = Integer.parseInt(datos[0].trim());
					String nombre = datos[1].trim();

					MiembroAltoMando miembro = new MiembroAltoMando(numero, nombre);

					for (int i = 2; i < datos.length; i++) {
						Pokemon pokemon = buscarPokemonPokedex(datos[i].trim());

						if (pokemon != null) {
							miembro.agregarPokemon(new Pokemon(pokemon));
						}
					}

					altoMando.add(miembro);

				} catch (NumberFormatException e) {
					System.out.println("ERROR: Hay un miembro del alto mando con datos invalidos.");
				}
			}
		}

		archivo.close();
	}

	// carga una partida guardada
	private static boolean cargarPartida() {
		File archivoRegistros = new File("Registros.txt");

		if (!archivoRegistros.exists() || archivoRegistros.length() == 0) {
			System.out.println("ERROR: No hay una partida guardada.");
			return false;
		}

		try {
			Scanner archivo = new Scanner(archivoRegistros);

			if (!archivo.hasNextLine()) {
				archivo.close();
				System.out.println("ERROR: No hay una partida guardada.");
				return false;
			}

			String datosJugador[] = archivo.nextLine().split(";");

			if (datosJugador.length < 2) {
				archivo.close();
				System.out.println("ERROR: El archivo Registros.txt es invalido.");
				return false;
			}

			jugador = new Jugador(datosJugador[0].trim(), datosJugador[1].trim());

			while (archivo.hasNextLine()) {
				String datos[] = archivo.nextLine().split(";");

				if (datos.length == 2) {
					Pokemon base = buscarPokemonPokedex(datos[0].trim());

					if (base != null) {
						Pokemon capturado = new Pokemon(base);
						capturado.setEstado(datos[1].trim());
						jugador.agregarPokemon(capturado);
					}
				}
			}

			archivo.close();
			return true;

		} catch (FileNotFoundException e) {
			System.out.println("ERROR: No se pudo leer Registros.txt.");
			return false;
		}
	}

	// crea una nueva partida
	private static void nuevaPartida() {
		String apodo = leerTextoNoVacio("Ingrese su apodo de jugador: ");
		jugador = new Jugador(apodo, "none");
		guardarPartida();
	}

	// muestra el equipo actual
	private static void revisarEquipo() {
		if (jugador.getPokemones().size() == 0) {
			System.out.println("No tienes pokemones en tu equipo.");
			return;
		}

		System.out.println("Equipo Actual:");

		for (int i = 0; i < jugador.getCantidadEquipo(); i++) {
			Pokemon pokemon = jugador.getPokemones().get(i);
			System.out.println((i + 1) + ") " + pokemon.getNombre() + "|" + pokemon.getTipo() + "|Stats totales: "
					+ pokemon.getStatsTotales() + "|" + pokemon.getEstado());
		}
	}

	// permite salir a capturar
	private static void salirCapturar() {
		System.out.println("Donde deseas ir a explorar?");
		System.out.println();
		System.out.println("Zonas disponibles:");
		System.out.println();

		for (int i = 0; i < habitats.size(); i++) {
			System.out.println((i + 1) + ") " + habitats.get(i));
		}

		System.out.println((habitats.size() + 1) + ") Volver al menu.");

		int opcion = leerEnteroEnRango("Ingrese Zona: ", 1, habitats.size() + 1);

		if (opcion == habitats.size() + 1) {
			return;
		}

		String habitat = habitats.get(opcion - 1);
		Pokemon encontrado = generarPokemonAleatorio(habitat);

		if (encontrado == null) {
			System.out.println("No se encontro ningun pokemon en esa zona.");
			return;
		}

		System.out.println("Oh!! Ha aparecido un increible " + encontrado.getNombre() + "!!");
		System.out.println();
		System.out.println("Que deseas hacer?");
		System.out.println();
		System.out.println("1) Capturar");
		System.out.println("2) Huir");

		int resp = leerEnteroEnRango("Ingrese Opcion: ", 1, 2);

		switch (resp) {
		case 1:
			if (jugador.tienePokemon(encontrado.getNombre())) {
				System.out.println("Ya tienes a " + encontrado.getNombre() + ". No puedes capturarlo nuevamente.");
				return;
			}

			jugador.agregarPokemon(encontrado);
			System.out.println(encontrado.getNombre() + " capturado con exito!!");

			if (jugador.getPokemones().size() <= 6) {
				System.out.println(encontrado.getNombre() + " ha sido agregado a tu equipo!");
			} else {
				System.out.println(encontrado.getNombre() + " ha sido enviado al PC!");
			}
			break;

		case 2:
			System.out.println("Has huido correctamente.");
			break;
		}
	}

	// menu del pc
	private static void accesoPC() {
		if (jugador.getPokemones().size() == 0) {
			System.out.println("No tienes pokemones capturados.");
			return;
		}

		System.out.println("Pokemones capturados:");

		for (int i = 0; i < jugador.getPokemones().size(); i++) {
			Pokemon pokemon = jugador.getPokemones().get(i);
			System.out.println((i + 1) + ") " + pokemon.getNombre() + " - " + pokemon.getEstado());
		}

		System.out.println();
		System.out.println("1) Cambiar Pokemon.");
		System.out.println("2) Salir.");

		int opcion = leerEnteroEnRango("Ingrese Opcion: ", 1, 2);

		switch (opcion) {
		case 1:
			int pos1 = leerEnteroEnRango("Ingrese el primer pokemon a intercambiar: ", 1, jugador.getPokemones().size());
			int pos2 = leerEnteroEnRango("Ingrese el segundo pokemon a intercambiar: ", 1, jugador.getPokemones().size());

			jugador.intercambiarPokemones(pos1 - 1, pos2 - 1);
			System.out.println("Pokemones intercambiados con exito!");
			break;

		case 2:
			return;
		}
	}

	// muestra los gimnasios
	private static void retarGimnasio() {
		System.out.println("Gimnasios cargados:");
		System.out.println();

		for (int i = 0; i < gimnasios.size(); i++) {
			System.out.println((i + 1) + ") " + gimnasios.get(i).getLider() + " - Estado: "
					+ gimnasios.get(i).getEstado());
		}
		
		System.out.println("Sistema de combate de gimnasios aun no implementado.");
	}

	// muestra el alto mando
	private static void desafiarAltoMando() {
		System.out.println("Miembros del Alto Mando cargados:");
		System.out.println();

		for (int i = 0; i < altoMando.size(); i++) {
			System.out.println((i + 1) + ") " + altoMando.get(i).getNombre());
		}

		System.out.println("Desafio al Alto Mando aun no implementado.");
	}

	// cura los pokemones
	private static void curarPokemones() {
		if (jugador.getPokemones().size() == 0) {
			System.out.println("No tienes pokemones para curar.");
			return;
		}

		for (int i = 0; i < jugador.getPokemones().size(); i++) {
			jugador.getPokemones().get(i).setEstado("Vivo");
		}

		System.out.println("Tu equipo se ha recuperado!");
	}

	// guarda la partida
	private static void guardarPartida() {
		if (jugador == null) {
			return;
		}

		try {
			PrintWriter pw = new PrintWriter(new FileWriter("Registros.txt"));
			pw.println(jugador.getApodo() + ";" + jugador.getProgreso());

			for (int i = 0; i < jugador.getPokemones().size(); i++) {
				Pokemon pokemon = jugador.getPokemones().get(i);
				pw.println(pokemon.getNombre() + ";" + pokemon.getEstado());
			}

			pw.close();
			System.out.println("Partida guardada con exito!");

		} catch (IOException e) {
			System.out.println("ERROR: No se pudo guardar la partida.");
		}
	}

	// busca un pokemon en la pokedex por nombre
	private static Pokemon buscarPokemonPokedex(String nombre) {
		for (int i = 0; i < pokedex.size(); i++) {
			if (pokedex.get(i).getNombre().equalsIgnoreCase(nombre)) {
				return pokedex.get(i);
			}
		}

		return null;
	}

	// genera un pokemon aleatorio en una zona
	private static Pokemon generarPokemonAleatorio(String habitat) {
		ArrayList<Pokemon> pokemonesZona = new ArrayList<Pokemon>();

		for (int i = 0; i < pokedex.size(); i++) {
			if (pokedex.get(i).getHabitat().equalsIgnoreCase(habitat)) {
				pokemonesZona.add(pokedex.get(i));
			}
		}

		if (pokemonesZona.size() == 0) {
			return null;
		}

		double numero = r.nextDouble();
		double acumulado = 0;

		for (int i = 0; i < pokemonesZona.size(); i++) {
			acumulado += pokemonesZona.get(i).getPorcentajeAparicion();

			if (numero <= acumulado) {
				return new Pokemon(pokemonesZona.get(i));
			}
		}

		return new Pokemon(pokemonesZona.get(pokemonesZona.size() - 1));
	}

	// lee texto no vacio
	private static String leerTextoNoVacio(String mensaje) {
		while (true) {
			System.out.print(mensaje);
			String texto = s.nextLine().trim();

			if (texto.equals("")) {
				System.out.println("ERROR: No puede quedar vacio.");
			} else if (texto.contains(";")) {
				System.out.println("ERROR: No se permite usar ';' en este campo.");
			} else {
				return texto;
			}
		}
	}

	// lee entero entre un rango valido
	private static int leerEnteroEnRango(String mensaje, int min, int max) {
		while (true) {
			System.out.print(mensaje);
			String texto = s.nextLine();

			try {
				int numero = Integer.parseInt(texto);

				if (numero >= min && numero <= max) {
					return numero;
				} else {
					System.out.println("ERROR: Debe ingresar una opcion valida.");
				}

			} catch (NumberFormatException e) {
				System.out.println("ERROR: Debe ingresar un numero.");
			}
		}
	}
}
