import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.uniajc.Cuenta;
import org.junit.jupiter.api.Test;

public class CuentaTest {

	// Suite de pruebas para metodo consignar de la clase Cuenta
	@Test
	void testConsignarActualizaSaldo() {
		Cuenta cuenta = new Cuenta(100.0f, 0.05f);

		cuenta.consignar(50.0f);

		assertEquals(150.0f, cuenta.getSaldo(), 0.001f);
	}

	@Test
	void testConsignarCantidadNegativaEsRechazada() {
		Cuenta cuenta = new Cuenta(100.0f, 0.05f);

		IllegalArgumentException exception = assertThrows(
			IllegalArgumentException.class, () -> cuenta.consignar(-10.0f)
		);
		assertEquals("La cantidad no puede ser negativa", exception.getMessage());
		cuenta.consignar(10.0f);
		assertEquals(110.0f, cuenta.getSaldo(), 0.001f);
	}

	@Test
	void testConsignarCantidadCeroNoCambiaSaldo() {
		Cuenta cuenta = new Cuenta(100.0f, 0.05f);

		cuenta.consignar(0.0f);

		assertEquals(100.0f, cuenta.getSaldo(), 0.001f);
	}


	// Suite de pruebas para metodo retirar de la clase Cuenta
	// Suite de pruebas para metodo extractoMensual de la clase Cuenta

}
