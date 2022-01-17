package org.iesalandalus.programacion.citasclinica;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.citasclinica.modelo.Citas;
import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.citasclinica.vista.Consola;
import org.iesalandalus.programacion.citasclinica.vista.Opciones;

public class MainApp {

	public static final int NUM_MAX_CITAS = 50;
	static Citas citas = new Citas(NUM_MAX_CITAS);

	private static void insertarCita() throws OperationNotSupportedException {

		try {
			System.out.println("Opción 1. Insertar una nueva cita");
			System.out.println("");
			Cita cita = Consola.leerCita();
			citas.insertar(cita);
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
			System.out.println("");
			System.out.println("Error: Datos introducidos no válidos");
			System.out.println("Introduce correctamente los datos solicitados");
			System.out.println("");
			insertarCita();
		}

	}

	private static void buscarCita() {

		System.out.println("Opción 2. Buscar una cita creada");
		System.out.println("");

		Paciente paciente = new Paciente("Paciente", "11223344B", "923456789");
		LocalDateTime fechaHora = Consola.leerFechaHora();

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("h 'y' m 'del' d 'de' MMMM 'de' yyyy");

		Cita cita = new Cita(paciente, fechaHora);

		System.out.println("");

		if (citas.buscar(cita) != null) {
			System.out.println(cita);
		} else {
			System.out.println("Lo sentimos, no existe una cita para las " + fechaHora.format(formato));
		}
	}

	private static void borrarCita() throws OperationNotSupportedException {

		try {
			System.out.println("Opción 3. Borrar una cita creada");
			System.out.println("");

			Paciente paciente = new Paciente("Paciente", "11223344B", "923456789");
			LocalDateTime fechaHora = Consola.leerFechaHora();

			Cita cita = new Cita(paciente, fechaHora);

			citas.borrar(cita);

			System.out.println("Cita borrada correctamente");

		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println("");
			System.out.println(e.getMessage());
		}

	}

	private static void mostrarCitas() {

		System.out.println("Método pendiente de finalizar");
		
	}

	private static void mostrarCitasDia() {

		System.out.println("Método pendiente de finalizar");
		
	}

	private static void ejecutarOpcion(Opciones opcion) throws OperationNotSupportedException {

		switch (opcion) {
		case INSERTAR_CITA:
			insertarCita();
			break;

		case BUSCAR_CITA:
			buscarCita();
			break;

		case BORRAR_CITA:
			borrarCita();
			break;

		case MOSTRAR_CITAS:
			mostrarCitas();
			break;

		case MOSTRAR_CITAS_DIA:
			mostrarCitas();
			break;

		case SALIR:
			System.out.println("Programa finalizado");
		}

	}

	public static void main(String[] args) throws OperationNotSupportedException {
		Opciones opcion;

		do {
			Consola.mostrarMenu();
			opcion = Consola.elegirOpcion();
			ejecutarOpcion(opcion);
		} while (opcion != Opciones.SALIR);
		System.out.println("Programa finalizado");

	}

}
