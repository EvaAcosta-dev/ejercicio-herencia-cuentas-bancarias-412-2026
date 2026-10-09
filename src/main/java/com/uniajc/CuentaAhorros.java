package com.uniajc;

public class CuentaAhorros extends Cuenta {
    public boolean activa;

    public final String MENSAJE_CUENTA_INACTIVA = "La cuenta de ahorros está inactiva. No se pueden realizar transacciones.";
    final float SALDO_MINIMO_ACTIVACION = 10000.0f;
    final float COMISION_MENSUAL = 1000.0f;
    final int NUMERO_RETIROS_SIN_COMISION = 4;

    public CuentaAhorros(float saldo, float tasa) {
        super(saldo, tasa);
        this.activa = saldo >= SALDO_MINIMO_ACTIVACION;

        // if (saldo >= SALDO_MINIMO_ACTIVACION) {
        //     this.activa = true;
        // } else {
        //     this.activa = false;
        // }

    }

    @Override
    public void retirar(float cantidad) {
        if (!activa) {
            throw new IllegalStateException(MENSAJE_CUENTA_INACTIVA);
        }
        super.retirar(cantidad);

        activa = saldo >= SALDO_MINIMO_ACTIVACION;

        // if (activa) {
        //     super.retirar(cantidad);
        // } else {
        //     System.out.println(MENSAJE_CUENTA_INACTIVA);
        //     throw new IllegalStateException(MENSAJE_CUENTA_INACTIVA);
        // }
    }

    @Override
    public void consignar(float cantidad) {
        if (saldo < 0 && !activa) {
            throw new IllegalStateException(MENSAJE_CUENTA_INACTIVA);
        }
        super.consignar(cantidad);

        activa = saldo >= SALDO_MINIMO_ACTIVACION;

        // if (activa) {
        //     super.consignar(cantidad);
        // } else {
        //     System.out.println(MENSAJE_CUENTA_INACTIVA);
        //     throw new IllegalStateException(MENSAJE_CUENTA_INACTIVA);
        // }
    }

    @Override
    public void extractoMensual() {
        if (numeroRetiros > NUMERO_RETIROS_SIN_COMISION) {
            comisionMensual = (numeroRetiros - NUMERO_RETIROS_SIN_COMISION) * COMISION_MENSUAL;
            setComisionMensual(comisionMensual);
        } else {
            comisionMensual = 0;
        }   
        super.extractoMensual();
        activa = saldo >= SALDO_MINIMO_ACTIVACION;

        // System.out.println("LOG estado atributi activa: " + activa);

        // if (saldo < SALDO_MINIMO_ACTIVACION) {
        //     activa = false;
        // } else {
        //     activa = true;
        // }
    }

    public void imprimir() { 
        System.out.println("---------INICIA IMPRESION CUENTA AHORROS----------");
        super.imprimir();
        System.out.println("Cuenta activa: " + activa);
        System.out.println("------------------------------");
    }
}