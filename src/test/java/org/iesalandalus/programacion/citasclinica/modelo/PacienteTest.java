package org.iesalandalus.programacion.citasclinica.modelo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class PacienteTest {
	
	private static final String ERROR_NOMBRE_NULO = "ERROR: El nombre de un paciente no puede ser nulo o vac�o.";
	private static final String ERROR_DNI_NULO = "ERROR: El DNI de un paciente no puede ser nulo o vac�o.";
	private static final String ERROR_DNI_NO_VALIDO = "ERROR: El DNI no tiene un formato v�lido.";
	private static final String ERROR_LETRA_DNI_NO_VALIDA = "ERROR: La letra del DNI no es correcta.";
	private static final String ERROR_TELEFONO_NULO = "ERROR: El tel�fono de un paciente no puede ser nulo o vac�o.";
	private static final String ERROR_TELEFONO_NO_VALIDO = "ERROR: El tel�fono no tiene un formato v�lido.";
	private static final String ERROR_COPIAR_PACIENTE_NULO = "ERROR: No es posible copiar un paciente nulo.";
	private static final String NOMBRE_INCORRECTO = "Deber�a haber saltado una excepci�n indicando que el nombre es incorrecto";
	private static final String DNI_INCORRECTO = "Deber�a haber saltado una excepci�n indicando que el DNI es incorrecto";
	private static final String TELEFONO_INCORRECTO = "Deber�a haber saltado una excepci�n indicando que el tel�fono es incorrecto";
	private static final String PACIENTE_NULO = "Deber�a haber saltado una excepci�n indicando que no se puede copiar un paciente nulo.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepci�n no es correcto.";
	private static final String TIPO_NO_CORRECTO = "El tipo de la excepci�n no es correcto.";
	private static final String CADENA_NO_ESPERADA = "La cadena devuelta no es la esperada.";
	private static final String PACIENTE_NO_ESPERADO = "El paciente copiado deber�a ser el mismo que el pasado como par�metro.";
	private static final String NOMBRE_NO_ESPERADO = "El nombre devuelto no es el mismo que el pasado al constructor.";
	private static final String DNI_NO_ESPERADO = "El DNI devuelto no es el mismo que el pasado al constructor.";
	private static final String TELEFONO_NO_ESPERADO = "El tel�fono devuelto no es el mismo que el pasado al constructor.";
	private static final String OBJETO_DEBERIA_SER_NULO = "No se deber�a haber creado el objeto paciente.";
	private static final String NOMBRE_JRJR = "Jos� Ram�n Jim�nez Reyes";
	private static final String DNI_JRJR = "11223344B";
	private static final String TELEFONO_JRJR = "950112233";
	private static final String NOMBRE_MAL_ARDR = "  ANDR�S   RuBiO   dEl             r�o";
	private static final String NOMBRE_ARDR = "Andr�s Rubio Del R�o";
	private static final String DNI_ARDR = "22334455Y";
	private static final String TELEFONO_ARDR = "666223344";

	@Test
	public void constructorNombreValidoDniValidoTelefonoValidoCreaPacienteCorrectamente() {
		Paciente paciente = new Paciente(NOMBRE_JRJR, DNI_JRJR, TELEFONO_JRJR);
		assertThat(NOMBRE_NO_ESPERADO, paciente.getNombre(), is(NOMBRE_JRJR));
		assertThat(DNI_NO_ESPERADO, paciente.getDni(), is(DNI_JRJR));
		assertThat(TELEFONO_NO_ESPERADO, paciente.getTelefono(), is(TELEFONO_JRJR));
		paciente = new Paciente(NOMBRE_MAL_ARDR, DNI_ARDR, TELEFONO_ARDR);
		assertThat(NOMBRE_NO_ESPERADO, paciente.getNombre(), is(NOMBRE_ARDR));
		assertThat(DNI_NO_ESPERADO, paciente.getDni(), is(DNI_ARDR));
		assertThat(TELEFONO_NO_ESPERADO, paciente.getTelefono(), is(TELEFONO_ARDR));
	}

	@Test
	public void constructorNombreNoValidoDniValidoTelefonoValidoLanzaExcepcion() {
		Paciente paciente = null;
		try {
			paciente = new Paciente(null, DNI_JRJR, TELEFONO_JRJR);
			fail(NOMBRE_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NOMBRE_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, paciente, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			paciente = new Paciente("", DNI_JRJR, TELEFONO_JRJR);
			fail(NOMBRE_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NOMBRE_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, paciente, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			paciente = new Paciente("  ", DNI_JRJR, TELEFONO_JRJR);
			fail(NOMBRE_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NOMBRE_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, paciente, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorNombreValidoDniNoValidoTelefonoValidoLanzaExcepcion() {
		Paciente paciente = null;
		try {
			paciente = new Paciente(NOMBRE_JRJR, null, TELEFONO_JRJR);
			fail(DNI_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_DNI_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, paciente, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			paciente = new Paciente(NOMBRE_JRJR, "", TELEFONO_JRJR);
			fail(DNI_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_DNI_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, paciente, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			paciente = new Paciente(NOMBRE_JRJR, "   ", TELEFONO_JRJR);
			fail(DNI_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_DNI_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, paciente, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			paciente = new Paciente(NOMBRE_JRJR, "11223344", TELEFONO_JRJR);
			fail(DNI_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_DNI_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, paciente, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			paciente = new Paciente(NOMBRE_JRJR, "112233445A", TELEFONO_JRJR);
			fail(DNI_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_DNI_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, paciente, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			paciente = new Paciente(NOMBRE_JRJR, "11223344AA", TELEFONO_JRJR);
			fail(DNI_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_DNI_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, paciente, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			paciente = new Paciente(NOMBRE_JRJR, "11223344A", TELEFONO_JRJR);
			fail(DNI_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_LETRA_DNI_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULO, paciente, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorNombreValidoDniValidoTelefonoNoValidoLanzaExcepcion() {
		Paciente paciente = null;
		try {
			paciente = new Paciente(NOMBRE_JRJR, DNI_JRJR, null);
			fail(TELEFONO_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_TELEFONO_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, paciente, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			paciente = new Paciente(NOMBRE_JRJR, DNI_JRJR, "");
			fail(TELEFONO_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_TELEFONO_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, paciente, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			paciente = new Paciente(NOMBRE_JRJR, DNI_JRJR, "   ");
			fail(TELEFONO_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_TELEFONO_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, paciente, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			paciente = new Paciente(NOMBRE_JRJR, DNI_JRJR, "9");
			fail(TELEFONO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_TELEFONO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, paciente, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			paciente = new Paciente(NOMBRE_JRJR, DNI_JRJR, "9991122334");
			fail(TELEFONO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_TELEFONO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, paciente, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			paciente = new Paciente(NOMBRE_JRJR, DNI_JRJR, "777112233");
			fail(TELEFONO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_TELEFONO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, paciente, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorPacienteValidoCopiaPacienteCorrectamente() {
		Paciente paciente1 = new Paciente(NOMBRE_JRJR, DNI_JRJR, TELEFONO_JRJR);
		Paciente paciente2 = new Paciente(paciente1);
		assertThat(PACIENTE_NO_ESPERADO, paciente2, is(paciente1));
		assertThat(NOMBRE_NO_ESPERADO, paciente2.getNombre(), is(NOMBRE_JRJR));
		assertThat(DNI_NO_ESPERADO, paciente2.getDni(), is(DNI_JRJR));
		assertThat(TELEFONO_NO_ESPERADO, paciente2.getTelefono(), is(TELEFONO_JRJR));
	}
	
	@Test
	public void constructorPacienteNuloLanzaExcepcion() {
		Paciente paciente = null;
		try {
			paciente = new Paciente(null);
			fail(PACIENTE_NULO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_COPIAR_PACIENTE_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, paciente, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void toStringDevuelveLaCadenaEsperada() {
		Paciente paciente = new Paciente(NOMBRE_JRJR, DNI_JRJR, TELEFONO_JRJR);
		assertThat(CADENA_NO_ESPERADA, paciente.toString(), is(String.format("nombre=%s (%s), DNI=%s, tel�fono=%s", NOMBRE_JRJR, "JRJR", DNI_JRJR, TELEFONO_JRJR)));
		paciente = new Paciente(NOMBRE_MAL_ARDR, DNI_ARDR, TELEFONO_ARDR);
		assertThat(CADENA_NO_ESPERADA, paciente.toString(), is(String.format("nombre=%s (%s), DNI=%s, tel�fono=%s", NOMBRE_ARDR, "ARDR", DNI_ARDR, TELEFONO_ARDR)));
	}

}