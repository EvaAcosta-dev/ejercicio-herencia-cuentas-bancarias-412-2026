import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
	@Test
    void testConsignarEnCuentaInactivaQueLlegaAlMinimoLaReactiva() {
        CuentaAhorros cuenta = new CuentaAhorros(9000.0f, 0.05f);
 
        cuenta.consignar(1000.0f);
 
        assertEquals(10000.0f, cuenta.getSaldo(), 0.001f);
        assertTrue(cuenta.activa);
    }
	 @Test
    void testConsignarEnCuentaInactivaSinLlegarAlMinimoSigueInactiva() {
        CuentaAhorros cuenta = new CuentaAhorros(5000.0f, 0.05f);
 
        cuenta.consignar(1000.0f);
 
        assertEquals(6000.0f, cuenta.getSaldo(), 0.001f);
        assertFalse(cuenta.activa);
    }


    // Suite de pruebas para metodo retirar de la clase CuentaAhorros

     @Test
    void testRetirarQuedandoEnElMinimoExactoSigueActiva() {
        CuentaAhorros cuenta = new CuentaAhorros(15000.0f, 0.05f);
 
        cuenta.retirar(5000.0f);
 
        assertEquals(10000.0f, cuenta.getSaldo(), 0.001f);
        assertTrue(cuenta.activa);
    }
 
    @Test
    void testRetirarQuedandoPorDebajoDelMinimoDesactivaLaCuenta() {
        CuentaAhorros cuenta = new CuentaAhorros(15000.0f, 0.05f);
 
        cuenta.retirar(5001.0f);
 
        assertEquals(9999.0f, cuenta.getSaldo(), 0.001f);
        assertFalse(cuenta.activa);
    }
 
    @Test
    void testRetirarEnCuentaInactivaEsRechazado() {
        CuentaAhorros cuenta = new CuentaAhorros(5000.0f, 0.05f);
 
        assertThrows(IllegalStateException.class, () -> cuenta.retirar(100.0f));
        assertEquals(5000.0f, cuenta.getSaldo(), 0.001f);
        assertEquals(0, cuenta.getNumeroRetiros());
    }
 
    @Test
    void testRetirarEnCuentaInactivaLanzaMensajeCorrecto() {
        CuentaAhorros cuenta = new CuentaAhorros(5000.0f, 0.05f);
 
        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> cuenta.retirar(100.0f));
 
        assertEquals(cuenta.MENSAJE_CUENTA_INACTIVA, ex.getMessage());
    }
 
    @Test
    void testRetirarMasQueElSaldoEsRechazado() {
        CuentaAhorros cuenta = new CuentaAhorros(15000.0f, 0.05f);
 
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> cuenta.retirar(20000.0f));
 
        assertEquals("Fondos insuficientes", ex.getMessage());
        assertEquals(15000.0f, cuenta.getSaldo(), 0.001f);
    }
 
    @Test
    void testRetirarCantidadNegativaEsRechazada() {
        CuentaAhorros cuenta = new CuentaAhorros(15000.0f, 0.05f);
 
        assertThrows(IllegalArgumentException.class, () -> cuenta.retirar(-10.0f));
        assertEquals(15000.0f, cuenta.getSaldo(), 0.001f);
        assertEquals(0, cuenta.getNumeroRetiros());
    }

    // Suite de pruebas para metodo extractoMensual de la clase CuentaAhorros

    @Test
    void testExtractoMensualSinRetirosNoAplicaComision() {
        CuentaAhorros cuenta = new CuentaAhorros(15000.0f, 0.12f);
        assertEquals(0.0f, cuenta.getComisionMensual(), 0.001f);

        cuenta.extractoMensual();

        assertEquals(0.0f, cuenta.getComisionMensual(), 0.001f);
        assertTrue(cuenta.getSaldo() > 15000.0f);
    }

    @Test
    void testExtractoMensualConRetirosIgualesALimiteNoAplicaComision() {
        CuentaAhorros cuenta = new CuentaAhorros(15000.0f, 0.12f);
        cuenta.retirar(1000.0f);
        cuenta.retirar(2000.0f);
        cuenta.retirar(500.0f);
        cuenta.retirar(1000.0f);
        assertEquals(4, cuenta.getNumeroRetiros());

        cuenta.extractoMensual();

        assertEquals(0.0f, cuenta.getComisionMensual(), 0.001f);
    }

    @Test
    void testExtractoMensualConRetirosExcedenLimiteAplicaComision() {
        CuentaAhorros cuenta = new CuentaAhorros(15000.0f, 0.12f);
        cuenta.retirar(1000.0f);
        cuenta.retirar(2000.0f);
        cuenta.retirar(500.0f);
        cuenta.retirar(1000.0f);
        cuenta.retirar(3000.0f);
        assertEquals(5, cuenta.getNumeroRetiros());

        cuenta.extractoMensual();

        assertEquals(1000.0f, cuenta.getComisionMensual(), 0.001f);
    }

    @Test
    void testExtractoMensualMultiplesRetirosExcesoCalculaComisionCorrecta() {
        CuentaAhorros cuenta = new CuentaAhorros(20000.0f, 0.12f);
        for (int i = 0; i < 7; i++) {
            cuenta.retirar(1000.0f);
        }
        assertEquals(7, cuenta.getNumeroRetiros());

        cuenta.extractoMensual();

        assertEquals(3000.0f, cuenta.getComisionMensual(), 0.001f);
    }

    @Test
    void testExtractoMensualActualizaEstadoActivaSegunSaldoFinal() {
        CuentaAhorros cuenta = new CuentaAhorros(13000.0f, 0.12f);
        cuenta.retirar(500.0f);
        cuenta.retirar(800.0f);
        cuenta.retirar(900.0f);
        cuenta.retirar(200.0f);
        cuenta.retirar(400.0f);
        assertTrue(cuenta.activa);

        cuenta.extractoMensual();

        assertFalse(cuenta.activa);
    }

    @Test
    void testExtractoMensualCuentaInactivaNoLanzaExcepcion() {
        CuentaAhorros cuenta = new CuentaAhorros(5000.0f, 0.12f);
        assertFalse(cuenta.activa);

        assertDoesNotThrow(() -> cuenta.extractoMensual());
        assertFalse(cuenta.activa);
    }

    @Test
    void testExtractoMensualCalculaInteresYRestaComision() {
        CuentaAhorros cuenta = new CuentaAhorros(15000.0f, 0.12f);
        cuenta.retirar(1000.0f);
        cuenta.retirar(2000.0f);
        cuenta.retirar(500.0f);
        cuenta.retirar(1000.0f);
        cuenta.retirar(1000.0f);
        float saldoAntes = cuenta.getSaldo();

        cuenta.extractoMensual();

        float interesEsperado = saldoAntes * (0.12f / 12);
        float saldoEsperado = saldoAntes + interesEsperado - 1000.0f;
        assertEquals(saldoEsperado, cuenta.getSaldo(), 0.01f);
    }

    @Test
    void testExtractoMensualReseteaComisionCadaMes() {
        CuentaAhorros cuenta = new CuentaAhorros(15000.0f, 0.12f);
        cuenta.retirar(1000.0f);
        cuenta.retirar(2000.0f);
        cuenta.retirar(500.0f);
        cuenta.retirar(1000.0f);
        cuenta.retirar(3000.0f);
        cuenta.extractoMensual();
        assertEquals(1000.0f, cuenta.getComisionMensual(), 0.001f);

        cuenta.extractoMensual();

        assertEquals(1000.0f, cuenta.getComisionMensual(), 0.001f);
    }
}