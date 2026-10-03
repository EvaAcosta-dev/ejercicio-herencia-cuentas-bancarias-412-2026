import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.uniajc.CuentaAhorros;
import org.junit.jupiter.api.Test;

public class CuentaAhorrosTest {

	// Suite de pruebas para metodo consignar de la clase CuentaAhorros
	@Test
	void testConsignarActualizaSaldo() {
		CuentaAhorros cuenta = new CuentaAhorros(100.0f, 0.05f);

		cuenta.consignar(50.0f);

		assertEquals(150.0f, cuenta.getSaldo(), 0.001f);
	}

	@Test
	void testConsignarCantidadNegativaEsRechazada() {
		CuentaAhorros cuenta = new CuentaAhorros(100.0f, 0.05f);

		assertThrows(IllegalArgumentException.class, () -> cuenta.consignar(-10.0f));
		assertEquals(100.0f, cuenta.getSaldo(), 0.001f);
	}

	@Test
	void testConsignarCantidadCeroNoCambiaSaldo() {
		CuentaAhorros cuenta = new CuentaAhorros(100.0f, 0.05f);

		cuenta.consignar(0.0f);

		assertEquals(100.0f, cuenta.getSaldo(), 0.001f);
	}


	// Suite de pruebas para metodo retirar de la clase CuentaAhorros

	// Suite de pruebas para metodo extractoMensual de la clase CuentaAhorros
}
