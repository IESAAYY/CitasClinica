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

	public static final int NUM_MAX_CITAS = 5;
	private static final DateTimeFormatter formato = DateTimeFormatter.ofPattern("hh:mm 'del' d 'de' MMMM 'de' yyyy");

	static Citas citas = new Citas(NUM_MAX_CITAS);

	private static void insertarCita() throws OperationNotSupportedException {

		try {
			System.out.println(".................................");
			System.out.println("Opción 1. Insertar una nueva cita");
			System.out.println(".................................");
			System.out.println("");

			Cita cita = Consola.leerCita();

			citas.insertar(cita);

			System.out.println("Cita insertada correctamente, datos de la cita: ");
			System.out.println(cita);
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println("");
			System.out.println(e.getMessage());
			System.out.println(e.getClass());

			System.out.println("Introduce correctamente los datos solicitados");
		}

	}

	private static void buscarCita() {
		
		System.out.println("................................");
		System.out.println("Opción 2. Buscar una cita creada");
		System.out.println("................................");

		LocalDateTime fechaHora = Consola.leerFechaHora();
		System.out.println("");

		for (int i = 0; i < citas.getTamano(); i++) {
			if (citas.getCitas()[i].getFechaHora().equals(fechaHora)) {
			
				System.out.println("Se ha encontrado una cita en la fecha y hora introducida: " + fechaHora.format(formato));
				System.out.print("Detalles de la cita: ");
				System.out.println(citas.getCitas()[i]);
				return;
			}
		}
		System.out.println("Lo sentimos, no se ha encontrado ninguna cita en la fecha y hora introducida: " + fechaHora.format(formato));
		
	}

	private static void borrarCita() throws OperationNotSupportedException {

		System.out.println("................................");
		System.out.println("Opción 3. Borrar una cita creada");
		System.out.println("................................");
	
		LocalDateTime fechaHora = Consola.leerFechaHora();
		System.out.println("");
		
		for (int i = 0; i < citas.getTamano(); i++) {
			if (citas.getCitas()[i].getFechaHora().equals(fechaHora)) {
				try {
					System.out.println("Se ha encontrado una cita en la fecha y hora introducida: " + fechaHora.format(formato));
					System.out.print("Detalles de la cita: ");
					System.out.println(citas.getCitas()[i]);		
					citas.borrar(citas.getCitas()[i]);
					System.out.println("Cita borrada correctamente");
				} catch (OperationNotSupportedException e) {
					System.out.println(e.getMessage());
					System.out.println(e.getClass());
				}
				return;
			}
		}
		System.out.println("Lo sentimos, no se ha encontrado ninguna cita en la fecha y hora introducida: " + fechaHora.format(formato));
		
	}
		

	private static void mostrarCitasDia() {

		System.out.println(".................................................");
		System.out.println("Opcion 4. Mostrar las citas de un día determinado");
		System.out.println(".................................................");
		System.out.println("");

		LocalDate fHIntroducida = Consola.leerFecha();

		Cita[] citaFecha = citas.getCitas(fHIntroducida);

		if (citaFecha != null) {
			System.out.println("Citas encontrada para la fecha " + fHIntroducida + ":");
			for (Cita i : citaFecha) {
				System.out.println(i);
			}
		}

		if (citas.getTamano() == 0) {
			System.out.println("No se ha encontrado cita para la fecha introducida debido a que actualmente no existen citas en este gestor");
			System.out.println("Inserta primero una cita para mostrarla");
		}

	}

	private static void mostrarCitas() {

		System.out.println(".................................");
		System.out.println("Opcion 5. Mostrar todas las citas");
		System.out.println(".................................");
		System.out.println("");

		if (citas.getTamano() != 0) {
			for (int i = 0; i < citas.getTamano(); i++) {
				System.out.println(citas.getCitas()[i]);
			}
		} else {
			System.out.println("Inserta primero una cita para poder mostrarla");
		}

	}

	private static void ejecutarOpcion(Opciones opcion) throws OperationNotSupportedException {

		switch (opcion) {
		case INSERTAR_CITA:
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			System.out.println("");
			insertarCita();
			System.out.println("");
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			break;

		case BUSCAR_CITA:
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			System.out.println("");
			buscarCita();
			System.out.println("");
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			break;

		case BORRAR_CITA:
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			System.out.println("");
			borrarCita();
			System.out.println("");
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			break;

		case MOSTRAR_CITAS_DIA:
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			System.out.println("");
			mostrarCitasDia();
			System.out.println("");
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			break;

		case MOSTRAR_CITAS:
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			System.out.println("");
			mostrarCitas();
			System.out.println("");
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			break;

		case SALIR:
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			System.out.println("");
			System.out.println("Programa finalizado");
			System.out.println("");
			System.out.println("-------------------------------------------------------------------------------------------------------------");
		}

	}

	public static void main(String[] args) throws OperationNotSupportedException {

		Opciones opcion;

		System.out.println("        =-=-=-=-=-=-=-=-=-=-=-=-=-");
		System.out.println("        |Gestor de citas clínicas|");
		System.out.println("        =-=-=-=-=-=-=-=-=-=-=-=-=-");

		do {
			Consola.mostrarMenu();
			opcion = Consola.elegirOpcion();
			ejecutarOpcion(opcion);
		} while (opcion != Opciones.SALIR);

	}

}
