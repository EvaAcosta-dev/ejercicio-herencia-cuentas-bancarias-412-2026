import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.uniajc.CuentaCorriente;
import org.junit.jupiter.api.Test;

public class CuentaCorrienteTest {

	// Suite de pruebas para metodo consignar de la clase CuentaCorriente
	@Test
	void testConsignarSinSobregiroActualizaSaldo() {
		CuentaCorriente cuenta = new CuentaCorriente(5000.0f, 0.03f);

		cuenta.consignar(1000.0f);

		assertEquals(6000.0f, cuenta.getSaldo(), 0.001f);
		assertEquals(0.0f, cuenta.getSobregiro(), 0.001f);
		assertEquals(1, cuenta.getNumeroConsignaciones());
	}

	@Test
	void testConsignarCantidadNegativaEsRechazada() {
		CuentaCorriente cuenta = new CuentaCorriente(5000.0f, 0.03f);

		IllegalArgumentException exception = assertThrows(
			IllegalArgumentException.class, () -> cuenta.consignar(-10.0f)
		);

		assertEquals("La cantidad no puede ser negativa", exception.getMessage());
		assertEquals(5000.0f, cuenta.getSaldo(), 0.001f);
		assertEquals(0, cuenta.getNumeroConsignaciones());
	}

	@Test
	void testConsignarCantidadCeroNoCambiaSaldo() {
		CuentaCorriente cuenta = new CuentaCorriente(5000.0f, 0.03f);

		cuenta.consignar(0.0f);

		assertEquals(5000.0f, cuenta.getSaldo(), 0.001f);
	}

	@Test
	void testConsignarMenorAlSobregiroSoloReduceElSobregiro() {
		CuentaCorriente cuenta = new CuentaCorriente(5000.0f, 0.03f);
		cuenta.retirar(6000.0f); // saldo 0, sobregiro 1000

		cuenta.consignar(400.0f);

		assertEquals(0.0f, cuenta.getSaldo(), 0.001f);
		assertEquals(600.0f, cuenta.getSobregiro(), 0.001f);
	}

	@Test
	void testConsignarIgualAlSobregiroDejaTodoEnCero() {
		CuentaCorriente cuenta = new CuentaCorriente(5000.0f, 0.03f);
		cuenta.retirar(6000.0f); // saldo 0, sobregiro 1000

		cuenta.consignar(1000.0f);

		assertEquals(0.0f, cuenta.getSaldo(), 0.001f);
		assertEquals(0.0f, cuenta.getSobregiro(), 0.001f);
	}

	@Test
	void testConsignarMayorAlSobregiroPagaSobregiroYAcreditaElSobrante() {
		CuentaCorriente cuenta = new CuentaCorriente(5000.0f, 0.03f);
		cuenta.retirar(6500.0f); // saldo 0, sobregiro 1500

		cuenta.consignar(2000.0f);

		assertEquals(500.0f, cuenta.getSaldo(), 0.001f);
		assertEquals(0.0f, cuenta.getSobregiro(), 0.001f);
	}


	// Suite de pruebas para metodo retirar de la clase CuentaCorriente
	@Test
	void testRetirarMenorAlSaldoActualizaSaldoSinSobregiro() {
		CuentaCorriente cuenta = new CuentaCorriente(5000.0f, 0.03f);

		cuenta.retirar(2000.0f);

		assertEquals(3000.0f, cuenta.getSaldo(), 0.001f);
		assertEquals(0.0f, cuenta.getSobregiro(), 0.001f);
		assertEquals(1, cuenta.getNumeroRetiros());
	}

	@Test
	void testRetirarIgualAlSaldoNoGeneraSobregiro() {
		CuentaCorriente cuenta = new CuentaCorriente(5000.0f, 0.03f);

		cuenta.retirar(5000.0f);

		assertEquals(0.0f, cuenta.getSaldo(), 0.001f);
		assertEquals(0.0f, cuenta.getSobregiro(), 0.001f);
	}

	@Test
	void testRetirarMayorAlSaldoGeneraSobregiro() {
		CuentaCorriente cuenta = new CuentaCorriente(5000.0f, 0.03f);

		cuenta.retirar(6000.0f);

		assertEquals(0.0f, cuenta.getSaldo(), 0.001f);
		assertEquals(1000.0f, cuenta.getSobregiro(), 0.001f);
		assertEquals(1, cuenta.getNumeroRetiros());
	}

	@Test
	void testRetirarConSobregiroPrevioAcumulaSobregiro() {
		CuentaCorriente cuenta = new CuentaCorriente(5000.0f, 0.03f);

		cuenta.retirar(6000.0f); // sobregiro 1000
		cuenta.retirar(500.0f);  // sobregiro 1500

		assertEquals(0.0f, cuenta.getSaldo(), 0.001f);
		assertEquals(1500.0f, cuenta.getSobregiro(), 0.001f);
		assertEquals(2, cuenta.getNumeroRetiros());
	}

	@Test
	void testRetirarCantidadNegativaEsRechazada() {
		CuentaCorriente cuenta = new CuentaCorriente(5000.0f, 0.03f);

		IllegalArgumentException exception = assertThrows(
			IllegalArgumentException.class, () -> cuenta.retirar(-10.0f)
		);

		assertEquals("La cantidad no puede ser negativa", exception.getMessage());
		assertEquals(5000.0f, cuenta.getSaldo(), 0.001f);
		assertEquals(0, cuenta.getNumeroRetiros());
	}

	@Test
	void testRetirarCantidadCeroNoCambiaSaldo() {
		CuentaCorriente cuenta = new CuentaCorriente(5000.0f, 0.03f);

		cuenta.retirar(0.0f);

		assertEquals(5000.0f, cuenta.getSaldo(), 0.001f);
		assertEquals(0.0f, cuenta.getSobregiro(), 0.001f);
	}


	// Suite de pruebas para metodo extractoMensual de la clase CuentaCorriente
	@Test
	void testExtractoMensualAplicaInteresAlSaldo() {
		// 12% anual -> 1% mensual -> 1000 * 0.01 = 10
		CuentaCorriente cuenta = new CuentaCorriente(1000.0f, 0.12f);

		cuenta.extractoMensual();

		assertEquals(1010.0f, cuenta.getSaldo(), 0.001f);
	}

	@Test
	void testExtractoMensualConSaldoCeroNoGeneraInteres() {
		CuentaCorriente cuenta = new CuentaCorriente(5000.0f, 0.03f);
		cuenta.retirar(6000.0f); // saldo 0, sobregiro 1000

		cuenta.extractoMensual();

		assertEquals(0.0f, cuenta.getSaldo(), 0.001f);
		assertEquals(1000.0f, cuenta.getSobregiro(), 0.001f);
	}

	@Test
	void testExtractoMensualNoModificaElSobregiro() {
		CuentaCorriente cuenta = new CuentaCorriente(1000.0f, 0.12f);
		cuenta.setSobregiro(200.0f);

		cuenta.extractoMensual();

		assertEquals(200.0f, cuenta.getSobregiro(), 0.001f);
	}


	// Escenario completo (el mismo del Main)
	@Test
	void testEscenarioCompletoConRetirosYConsignaciones() {
		CuentaCorriente cuenta = new CuentaCorriente(5000.0f, 0.03f);

		cuenta.retirar(6000.0f);   // saldo 0, sobregiro 1000
		cuenta.retirar(500.0f);    // saldo 0, sobregiro 1500
		cuenta.consignar(2000.0f); // saldo 500, sobregiro 0
		assertEquals(500.0f, cuenta.getSaldo(), 0.001f);
		assertEquals(0.0f, cuenta.getSobregiro(), 0.001f);

		cuenta.consignar(400.0f);  // saldo 900
		cuenta.retirar(1500.0f);   // saldo 0, sobregiro 600
		cuenta.consignar(300.0f);  // saldo 0, sobregiro 300

		assertEquals(0.0f, cuenta.getSaldo(), 0.001f);
		assertEquals(300.0f, cuenta.getSobregiro(), 0.001f);
		assertEquals(3, cuenta.getNumeroRetiros());
		assertEquals(3, cuenta.getNumeroConsignaciones());
	}

}