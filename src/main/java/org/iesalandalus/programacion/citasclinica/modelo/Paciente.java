package org.iesalandalus.programacion.citasclinica;

import java.util.Objects;

public class Paciente {

    // Constatnes
    private static final String ER_DNI = "[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]";
    private static final String ER_TELEFONO = "[0-9]{9}";

    // Atributos
    private String nombre;
    private String dni;
    private String telefono;

    //Constructores
    public Paciente(String nombre, String dni, String telefono) {
        setNombre(formateaNombre(nombre));
        setDni(dni);
        setTelefono(telefono);
    }

    public Paciente(Paciente p) {
        setNombre(formateaNombre(p.getNombre()));
        setDni(p.getDni());
        setTelefono(p.getTelefono());
    }

    // Métodos
    public void setNombre(String nombre) {
        if (nombre != null) {
            this.nombre = nombre;
        } else {
            throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo o vacío.");
        }
    }

    public String getNombre() {
        return nombre;
    }

    private String formateaNombre(String nombre) {
        nombre = nombre.toLowerCase().trim();
        nombre = nombre.replaceAll(" +", " ");

        String resultado = "";
        String arrayPalabra[] = nombre.split(" ");


        for (int i = 0; i < arrayPalabra.length; i++) {
            arrayPalabra[i] = arrayPalabra[i].replace(arrayPalabra[i].substring(0, 1), arrayPalabra[i].substring(0, 1).toUpperCase()) + " ";
            resultado += arrayPalabra[i];
        }
        this.nombre = resultado;

        return resultado;
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
        if (telefono != null) {
            this.telefono = telefono;
        }
        if (telefono == null) {
            throw new NullPointerException("ERROR: El teléfono de un paciente no puede ser nulo o vacío.");
        }
        if (telefono.matches(ER_TELEFONO) == false) {
            throw new IllegalArgumentException("ERROR: El teléfono de un paciente no puede ser nulo o vacío.");
        }

    }

    public String getTelefono() {
        return telefono;
    }

    private void setDni(String dni) {
        dni = dni.toUpperCase();

        if ((dni != null) && !dni.matches(ER_DNI)) {
            this.dni = dni;
        }
        if (dni == null) {
            throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vacío.");
        }
        if (!dni.matches("[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]")) {
            throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
        }
        if (!comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
        }

    }

    public String getDni() {
        return dni;
    }

    private boolean comprobarLetraDni(String dni) {
        this.dni = dni;
        int numeros = Integer.parseInt(dni.substring(0, 8));
        char letra = dni.charAt(8);

        char[] arrayLetras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
                'H', 'L', 'C', 'K', 'E'};

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
        return "nombre=" + "(" + getIniciales() + ")" + nombre + ", DNI=" + dni + ", teléfono=" + telefono;
    }


}