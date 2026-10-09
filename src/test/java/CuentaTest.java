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
    @Test
    void testRetirarMasQueElSaldoEsRechazado() {
        Cuenta cuenta = new Cuenta(100.0f, 0.05f);
 
        assertThrows(IllegalArgumentException.class, () -> cuenta.retirar(150.0f));
        assertEquals(100.0f, cuenta.getSaldo(), 0.001f);
    }
 
    @Test
    void testRetirarMasQueElSaldoLanzaMensajeFondosInsuficientes() {
        Cuenta cuenta = new Cuenta(100.0f, 0.05f);
 
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> cuenta.retirar(150.0f));
 
        assertEquals("Fondos insuficientes", ex.getMessage());
    }
 
    @Test
    void testRetirarMasQueElSaldoNoIncrementaContador() {
        Cuenta cuenta = new Cuenta(100.0f, 0.05f);
 
        assertThrows(IllegalArgumentException.class, () -> cuenta.retirar(150.0f));
        assertEquals(0, cuenta.getNumeroRetiros());
    }
 
    @Test
    void testRetirarCantidadNegativaEsRechazada() {
        Cuenta cuenta = new Cuenta(100.0f, 0.05f);
 
        assertThrows(IllegalArgumentException.class, () -> cuenta.retirar(-10.0f));
        assertEquals(100.0f, cuenta.getSaldo(), 0.001f);
        assertEquals(0, cuenta.getNumeroRetiros());
    }
 
    @Test
    void testRetirarCantidadCeroNoCambiaSaldo() {
        Cuenta cuenta = new Cuenta(100.0f, 0.05f);
 
        cuenta.retirar(0.0f);
 
        assertEquals(100.0f, cuenta.getSaldo(), 0.001f);
    }
  // Suite de pruebas para metodo calcularInteres de la clase Cuenta
    @Test
    void testCalcularInteresSumaInteresMensualAlSaldo() {
        // 1000 * (0.12 / 12) = 10
        Cuenta cuenta = new Cuenta(1000.0f, 0.12f);
 
        cuenta.calcularInteres();
 
        assertEquals(1010.0f, cuenta.getSaldo(), 0.001f);
    }
 
	// Suite de pruebas para metodo extractoMensual de la clase Cuenta
    @Test
    void testExtractoMensualSinComisionSoloAplicaInteres() {
        Cuenta cuenta = new Cuenta(1000.0f, 0.12f);
 
        cuenta.extractoMensual();
 
        assertEquals(1010.0f, cuenta.getSaldo(), 0.001f);
    }
 
    @Test
    void testExtractoMensualConComisionAplicaInteresYRestaComision() {
        Cuenta cuenta = new Cuenta(1000.0f, 0.12f);
        cuenta.setComisionMensual(50.0f);
 
        cuenta.extractoMensual();
 
        // 1000 + 10 - 50
        assertEquals(960.0f, cuenta.getSaldo(), 0.001f);
    }
 
    @Test
    void testExtractoMensualConTasaCeroSoloRestaComision() {
        Cuenta cuenta = new Cuenta(1000.0f, 0.0f);
        cuenta.setComisionMensual(50.0f);
 
        cuenta.extractoMensual();
 
        assertEquals(950.0f, cuenta.getSaldo(), 0.001f);
    }
 
    @Test
    void testExtractoMensualNoModificaLosContadores() {
        Cuenta cuenta = new Cuenta(1000.0f, 0.12f);
 
        cuenta.extractoMensual();
 
        assertEquals(0, cuenta.getNumeroConsignaciones());
        assertEquals(0, cuenta.getNumeroRetiros());
    }
}
