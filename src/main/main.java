package main;

import java.util.List;
import java.util.Scanner;

import javax.print.DocFlavor.INPUT_STREAM;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.DaoCocheMySql;
import modelo.persistencia.DaoPasajeroMySql;
import modelo.persistencia.interfaces.DaoCoche;
import modelo.persistencia.interfaces.DaoPasajero;

public class main {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		Coche c = new Coche();
		Pasajero p = new Pasajero();
		DaoCoche dc = new DaoCocheMySql();
		DaoPasajero dp = new DaoPasajeroMySql();
		int opcion = 0;
		boolean completado = false;

		while (opcion != 7) {

			opcion = menuCoches();

			switch (opcion) {
			case 1:
				System.out.println("\n----- ALTA COCHE -----\n");
				System.out.println("Introduce la marca del coche");
				c.setMarca(sc.nextLine());
				System.out.println("Introduce el modelo del coche");
				c.setModelo(sc.nextLine());
				System.out.println("Introduce la matricula del coche");
				c.setMatricula(sc.nextLine());
				completado = dc.alta(c);
				if (completado) {
					System.out.println("El coche se ha dado de alta");
				} else {
					System.out.println("El coche NO se ha dado de alta");
				}
				break;

			case 2:
				System.out.println("\n----- BAJA COCHE -----\n");
				System.out.println("Introduce el ID del coche a borrar");
				completado = dc.baja(sc.nextInt());
				sc.nextLine();
				if (completado) {
					System.out.println("El coche se ha dado de baja");
				} else {
					System.out.println("No hay coche registrado con tal ID");
				}
				break;

			case 3:
				System.out.println("\n----- CONSULTAR COCHE -----\n");
				System.out.println("Introduce el ID del coche a consultar");
				c = dc.obtener(sc.nextInt());
				sc.nextLine();
				if (c != null) {
					System.out.println("Mostrando ficha");
					System.out.println(c.toString());
				} else {
					System.out.println("No hay coche registrado con tal ID");
				}
				break;

			case 4:
				System.out.println("\n----- MODIFICAR COCHE -----\n");
				System.out.println("Introduce el ID del coche a modificar");
				c.setId(sc.nextInt());
				sc.nextLine();
				System.out.println("Introduce la nueva marca del coche");
				c.setMarca(sc.nextLine());
				System.out.println("Introduce el nuevo modelo del coche");
				c.setModelo(sc.nextLine());
				System.out.println("Introduce la nueva matricula del coche");
				c.setMatricula(sc.nextLine());
				completado = dc.modificar(c);
				if (completado) {
					System.out.println("El coche se ha modificado");
				} else {
					System.out.println("No hay coche registrado con tal ID");
				}
				break;

			case 5:
				System.out.println("\n----- LISTAR COCHES -----\n");
				List<Coche> listaCoches = dc.listar();
				for (Coche cocheListado : listaCoches) {
					System.out.println(cocheListado);
				}
				if (listaCoches.size() == 0) {
					System.out.println("Aún no hay coches registrados");
				}
				break;

			case 6:
				while (opcion != 9) {
					opcion = menuPasajeros();

					switch (opcion) {
					case 1:
						System.out.println("\n----- ALTA PASAJERO -----\n");
						System.out.println("Introduce el nombre del pasajero");
						p.setNombre(sc.nextLine());
						System.out.println("Introduce la edad del pasajero");
						p.setEdad(sc.nextInt());
						sc.nextLine();
						System.out.println("Introduce el peso del pasajero");
						p.setPeso(sc.nextDouble());
						sc.nextLine();
						completado = dp.alta(p);
						if (completado) {
							System.out.println("El pasajero se ha dado de alta");
						} else {
							System.out.println("El pasajero NO se ha dado de alta");
						}
						break;

					case 2:
						System.out.println("\n----- BAJA PASAJERO -----\n");
						System.out.println("Introduce el ID del pasajero a borrar");
						completado = dp.baja(sc.nextInt());
						sc.nextLine();
						if (completado) {
							System.out.println("El pasajero se ha dado de baja");
						} else {
							System.out.println("No hay pasajero registrado con tal ID");
						}
						break;

					case 3:
						System.out.println("\n----- CONSULTAR PASAJERO -----\n");
						System.out.println("Introduce el ID del pasajero a consultar");
						p = dp.obtener(sc.nextInt());
						sc.nextLine();
						if (p != null) {
							System.out.println("Mostrando ficha");
							System.out.println(p.toString());
						} else {
							System.out.println("No hay pasajero registrado con tal ID");
						}
						break;

					case 4:
						System.out.println("\n----- MODIFICAR PASAJERO -----\n");
						System.out.println("Introduce el ID del pasajero a modificar");
						p.setId(sc.nextInt());
						sc.nextLine();
						System.out.println("Introduce el nuevo nombre del pasajero");
						p.setNombre(sc.nextLine());
						System.out.println("Introduce la nueva edad del pasajero");
						p.setEdad(sc.nextInt());
						sc.nextLine();
						System.out.println("Introduce el nuevo peso del pasajero");
						p.setPeso(sc.nextDouble());
						sc.nextLine();
						completado = dp.modificar(p);
						if (completado) {
							System.out.println("El pasajero se ha modificado");
						} else {
							System.out.println("No hay pasajero registrado con tal ID");
						}
						break;

					case 5:
						System.out.println("\n----- LISTAR PASAJEROS -----\n");
						List<Pasajero> listaPasajeros = dp.listar();
						for (Pasajero pasajeroListado : listaPasajeros) {
							System.out.println(pasajeroListado);
						}
						if (listaPasajeros.size() == 0) {
							System.out.println("Aún no hay pasajeros registrados");
						}
						break;

					case 6:
						System.out.println("\n----- AÑADIR PASAJERO A COCHE -----\n");
						System.out.println("Introduce el ID del pasajero a asociar");
						p.setId(sc.nextInt());
						sc.nextLine();
						System.out.println("Introduce el ID del coche a asociar");
						p.setCoche_id(sc.nextInt());
						sc.nextLine();
						completado = dp.añadirEnCoche(p);
						if (completado) {
							System.out.println("El pasajero se ha asociado al coche");
						} else {
							System.out.println("El pasajero NO se ha asociado al coche");
						}
						break;

					case 7:
						System.out.println("\n----- AÑADIR PASAJERO A COCHE -----\n");
						System.out.println("Introduce el ID del pasajero a retirar");
						completado = dp.borrarDeCoche(sc.nextInt());
						sc.nextLine();
						if (completado) {
							System.out.println("El pasajero se ha retirado del coche");
						} else {
							System.out.println("El pasajero NO se ha retirado del coche");
						}
						break;

					case 8:
						System.out.println("\n----- LISTAR PASAJEROS DE UN COCHE -----\n");
						System.out.println("Introduce el ID del coche: ");
						int cocheID = sc.nextInt();
						List<Pasajero> listaPasajerosCoche = dp.listarPorCoche(cocheID);
						for (Pasajero pasajeroListado : listaPasajerosCoche) {
							System.out.println(pasajeroListado);
						}
						if (listaPasajerosCoche.size() == 0) {
							System.out.println("No hay pasajeros asociados a tal coche");
						}
						break;

					case 9:
						break;

					default:
						System.out.println("Elige una opción valida por favor");
						break;
					}
				}
				break;

			case 7:
				System.out.println("\n----- CERRANDO APLICACION -----\n");
				break;

			default:
				System.out.println("Elige una opción valida por favor");
				break;
			}
		}
	}

	public static int menuCoches() {
		System.out.println("\n --------- CONCESIONARIO ---------- \n");
		System.out.println("1. Añadir un nuevo coche");
		System.out.println("2. Borrar un coche por ID");
		System.out.println("3. Consultar un coche por ID");
		System.out.println("4. Modificar un coche por ID");
		System.out.println("5. Listar todos los coches");
		System.out.println("6. Opciones pasajeros");
		System.out.println("7. Salir de la aplicación");
		int opcion = sc.nextInt();
		sc.nextLine();
		return opcion;
	}

	public static int menuPasajeros() {
		System.out.println("\n --------- PASAJEROS ---------- \n");
		System.out.println("1. Añadir un nuevo pasajero");
		System.out.println("2. Borrar un pasajero por ID");
		System.out.println("3. Consultar un pasajero por ID");
		System.out.println("4. Modificar un pasajero por ID");
		System.out.println("5. Listar todos los pasajeros");
		System.out.println("6. Añadir un pasajero a un coche");
		System.out.println("7. Borrar un pasajero de un coche");
		System.out.println("8. Listar los pasajeros de un coche");
		System.out.println("9. Volver atras");
		int opcion = sc.nextInt();
		sc.nextLine();
		return opcion;
	}
}