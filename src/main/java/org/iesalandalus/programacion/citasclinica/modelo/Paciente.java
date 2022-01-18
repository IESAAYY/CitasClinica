package org.iesalandalus.programacion.citasclinica.modelo;

import java.util.Objects;

public class Paciente {

	// Constatnes
	private static final String ER_DNI = "[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke]";
	private static final String ER_TELEFONO = "(9|6)[0-9]{8}";

	// Atributos
	private String nombre;
	private String dni;
	private String telefono;

	// Constructores
	public Paciente(String nombre, String dni, String telefono) {
		
		setNombre(nombre);
		setDni(dni);
		setTelefono(telefono);
	}

	public Paciente(Paciente p) {
		
		if (p == null) {
			throw new NullPointerException("ERROR: No es posible copiar un paciente nulo.");
		}
		setNombre(p.getNombre());
		setDni(p.getDni());
		setTelefono(p.getTelefono());
		
	}

	// M�todos
	public void setNombre(String nombre) {

		if (nombre == null || nombre.trim().isEmpty()) {
			throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo o vac�o.");
		}
		this.nombre = formateaNombre(nombre);
		
	}

	public String getNombre() {
		
		return nombre;
		
	}

	private String formateaNombre(String nombre) {
		
		nombre = nombre.replaceAll(" +", " ");
		nombre = nombre.trim().toLowerCase();

		String resultado = "";
		String arrayPalabra[] = nombre.split(" ");

		for (int i = 0; i < arrayPalabra.length; i++) {
			arrayPalabra[i] = arrayPalabra[i].replace(arrayPalabra[i].substring(0, 1),
					arrayPalabra[i].substring(0, 1).toUpperCase()) + " ";
			resultado += arrayPalabra[i];
		}		
		return resultado.trim();
		
	}

	private String getIniciales() {
		
		String iniciales = "";
		
		formateaNombre(nombre);

		String arrayPalabra[] = nombre.split(" ");
		for (int i = 0; i < arrayPalabra.length; i++) {
			iniciales += arrayPalabra[i].charAt(0);
		}

		return iniciales;
		
	}

	public void setTelefono(String telefono) {
		
		if (telefono == null || telefono.trim().isEmpty()) {
			throw new NullPointerException("ERROR: El tel�fono de un paciente no puede ser nulo o vac�o.");
		}
		if (telefono.matches(ER_TELEFONO) == false) {
			throw new IllegalArgumentException("ERROR: El tel�fono no tiene un formato v�lido.");
		}
		this.telefono = telefono;

	}

	public String getTelefono() {
		
		return telefono;
		
	}

	private void setDni(String dni) {
		
		if (dni == null || dni.trim().isEmpty()) {
			throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vac�o.");
		}
		if (!dni.matches(ER_DNI)) {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato v�lido.");
		}
		if (!comprobarLetraDni(dni)) {
			throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
		}
		this.dni = dni;
		
	}

	public String getDni() {
		return dni;
	}

	private boolean comprobarLetraDni(String dni) {
		
		this.dni = dni;
		int numeros = Integer.parseInt(dni.substring(0, 8));
		char letra = dni.charAt(8);

		char[] arrayLetras = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
				'H', 'L', 'C', 'K', 'E' };

		int resto = numeros % 23;
		char letraCorrecta = arrayLetras[resto];
		if (letra == letraCorrecta) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public int hashCode() {
		
		return Objects.hash(dni);
		
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		return Objects.equals(dni, other.dni);
		
	}

	@Override
	public String toString() {
		
		return "nombre=" + nombre + " (" + getIniciales() + ")" + ", DNI=" + dni + ", tel�fono=" + telefono;
		
	}

}