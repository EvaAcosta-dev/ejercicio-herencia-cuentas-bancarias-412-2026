package com.uniajc;

public class CuentaAhorros extends Cuenta {
    protected boolean activa;

    final String MENSAJE_CUENTA_INACTIVA = "La cuenta de ahorros está inactiva. No se pueden realizar transacciones.";
    final float SALDO_MINIMO_ACTIVACION = 10000.0f;
    final float COMISION_MENSUAL = 1000.0f;
    final int NUMERO_RETIROS_SIN_COMISION = 4;

    public CuentaAhorros(float saldo, float tasa) {
        super(saldo, tasa);
<<<<<< < HEAD
         if (saldo < 10000){
            this.activa = false;
        } else {
            this.activa = true;
        }
    } 

    @Override
    public void retirar(float cantidad) {
        if (activa){
        super.retirar(cantidad);
        }else {
            System.out.println("La cuenta esta inactiva.");
        }
     }

    @Override

    public void consignar(float cantidad) {
        super.consignar(cantidad);
    }

    @Override
    public void extractoMensual() { 
         if (numeroRetiros > 4) {
            comisionMensual += (numeroRetiros - 4) * 1000;
        }
            super.extractoMensual();
            this.activa = (saldo >= 10000);
    }
    

    public void imprimir() {
        System.out.println("Datos Cuenta de Ahorros");
        System.out.println("Saldo: " + saldo);
        System.out.println("Comision mensual: " + comisionMensual);
        System.out.println("Numero de transacciones realizadas: " + (numeroConsignaciones + numeroRetiros));
=======

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
        }

        activa = saldo >= SALDO_MINIMO_ACTIVACION;

        // System.out.println("LOG estado atributi activa: " + activa);

        // if (saldo < SALDO_MINIMO_ACTIVACION) {
        //     activa = false;
        // } else {
        //     activa = true;
        // }
    }

    public void imprimir() { 
        System.out.println("---------------INICIA IMPRESION ---------------");
        super.imprimir();
        System.out.println("Cuenta activa: " + activa);
        System.out.println("------------------------------");
>>>>>>> upstream/main
    }
}