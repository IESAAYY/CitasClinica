package org.iesalandalus.programacion.citasclinica.vista;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.citasclinica.modelo.Cita;

import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private Consola() {

	}

	public static void mostrarMenu() {

		System.out.println("Menú de opciones");
		System.out.println("*********************************************************************");
		System.out.println("1. Insertar una nueva cita.");
		System.out.println("2. Buscar una cita creada");
		System.out.println("3. Borrar una cita creada");
		System.out.println("4. Mostrar las citas de un día determinado");
		System.out.println("5. Mostrar todas las citas");
		System.out.println("0. Salir del programa");
		System.out.println("*********************************************************************");

	}

	public static Opciones elegirOpcion() {
		int oIntroducido = 0;
		Opciones[] opcion = Opciones.values();

		do {
			System.out.println("Elige una opción(0 - 5");
			oIntroducido = Entrada.entero();

		} while (oIntroducido < 0 || oIntroducido > 5);

		return opcion[oIntroducido];
	}

	public static Paciente leerPaciente() {

		Paciente paciente;
		String nombre, dni, telefono;

		System.out.print("Introduce el nombre completo del paciente: ");
		nombre = Entrada.cadena();

		System.out.print("Introduce su dni con la letra: ");
		dni = Entrada.cadena();

		System.out.print("Introduce su teléfono: ");
		telefono = Entrada.cadena();

		paciente = new Paciente(nombre, dni, telefono);

		return paciente;

	}

	public static LocalDateTime leerFechaHora() {

		LocalDateTime fechaHora = null;
		boolean fHValida = false;

		DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern(Cita.FORMATO_FECHA_HORA);

		do {
			try {
				System.out.println("Introduce el día, el mes, el año, la hora y los minutos en el siguiente formato ");
				System.out.print(Cita.FORMATO_FECHA_HORA + " (Ejemplo: 17/01/2022 14:07): ");
				fechaHora = LocalDateTime.parse(Entrada.cadena(), formatoFechaHora);
				fHValida = true;
			} catch (DateTimeParseException e) {
				fHValida = false;
				System.out.println("");
			}
		} while (!fHValida);

		return fechaHora;
	}
	
	public Cita leerCita() {
		
		Cita cita = new Cita(leerPaciente(), leerFechaHora());
		
		return cita;
		
	}
	
	public static LocalDate leerFecha() {
		
		LocalDate fechaHora = null;
		boolean fHValida = false;

		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		do {
			try {
				System.out.println("Introduce el día, el mes, y el año en el siguiente formato ");
				System.out.print("dd/MM/yyyy" + " (Ejemplo: 17/01/2022): ");
				fechaHora = LocalDate.parse(Entrada.cadena(), formatoFecha);
				fHValida = true;
			} catch (DateTimeParseException e) {
				fHValida = false;
				System.out.println("");
			}
		} while (!fHValida);

		return fechaHora;
		
	}

}
