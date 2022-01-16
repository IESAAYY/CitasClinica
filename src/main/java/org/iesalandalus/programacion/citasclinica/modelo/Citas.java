package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

public class Citas {

	private int capacidad;
	private int tamano;

	Cita[] arrayCitas;

	public Citas(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		arrayCitas = new Cita[capacidad];
		this.capacidad = capacidad;
		tamano = 0;

	}

	public Cita[] getCitas() {
		return arrayCitas;

	}

	public Cita[] getCitas(LocalDate localDate) {

		if (localDate == null) {
			throw new NullPointerException("ERROR: No se pueden devolver las citas para un día nulo.");
		}

		Cita[] arrayLocalDate = new Cita[tamano];

		int j = 0;
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (arrayCitas[i].getFechaHora().toLocalDate().equals(localDate)) {
				arrayLocalDate[j++] = arrayCitas[i];
			}
		}
		return arrayLocalDate;

	}

	private boolean capacidadSuperada(int capacidad) {

		if (capacidad >= this.capacidad) {
			return true;
		} else {
			return false;
		}

	}

	private boolean tamanoSuperado(int tamano) {
		if (tamano >= this.tamano) {
			return true;
		} else {
			return false;
		}
	}

	private int buscarIndice(Cita cita) {

		int indice = 0;
		boolean citaEncontrada = false;
		for (int i = 0; !tamanoSuperado(i) && !citaEncontrada; i++) {
			if (arrayCitas[indice].equals(cita)) {
				citaEncontrada = true;
			}
		}
		while (!tamanoSuperado(indice) && !citaEncontrada) {
			if (arrayCitas[indice].equals(cita)) {
				citaEncontrada = true;
			} else {
				indice++;
			}
		}
		return indice;
	}

	public void insertar(Cita c) throws OperationNotSupportedException {

		if (c == null) {
			throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
		}

		if (!tamanoSuperado(buscarIndice(c))) {
			throw new OperationNotSupportedException("ERROR: Ya existe una cita para esa fecha y hora.");
		}

		if (capacidadSuperada(buscarIndice(c))) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más citas.");
		}

		arrayCitas[buscarIndice(c)] = new Cita(c);
		tamano++;
	}

	public Cita buscar(Cita c) {

		if (!tamanoSuperado(buscarIndice(c))) {
			return new Cita(c);
		} else {
			return null;
		}

	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {

		for (int i = indice; i < tamano; i++) {

			arrayCitas[i] = arrayCitas[i + 1];
		}
		tamano--;

	}

	public int getTamano() {
		return tamano;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void borrar(Cita cita) throws OperationNotSupportedException {

		if (cita == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una cita nula.");
		}

		if (!tamanoSuperado(buscarIndice(cita))) {
			desplazarUnaPosicionHaciaIzquierda(buscarIndice(cita));
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ninguna cita para esa fecha y hora.");
		}

	}

}