package com.uniajc;

public class CuentaCorriente extends Cuenta {
     protected float sobregiro = 0;

    public CuentaCorriente(float saldo, float tasaAnual) {
        super(saldo, tasaAnual);
    }

    public float getSobregiro() {
        return sobregiro;
    }

    public void setSobregiro(float sobregiro) {
        this.sobregiro = sobregiro;
    }

    @Override
    public void retirar(float cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException(MENSAJE_CANTIDAD_NEGATIVA);
        }
        if (cantidad <= saldo) {
            saldo -= cantidad;
            numeroRetiros++;
        } else {
            float faltante = cantidad - saldo;
            sobregiro += faltante;
            saldo = 0;
            numeroRetiros++;
            System.out.println("Se ha realizado un sobregiro de: " + faltante);
        }
    }

    @Override
    public void consignar(float cantidad) {
        if (cantidad < 0) {
        throw new IllegalArgumentException(MENSAJE_CANTIDAD_NEGATIVA);
    }

        if (sobregiro > 0) {
           
            if (cantidad <= sobregiro) {
                sobregiro -= cantidad;
                
            } else {
                float sobrante = cantidad - sobregiro;
                sobregiro = 0;
                saldo += sobrante;
            }
        } else {
            saldo += cantidad;
        }
        numeroConsignaciones++;
        System.out.println("Se ha consignado: " + cantidad);

    }
    public void extractoMensual() {
            super.extractoMensual();
     }

    public void imprimir() {
        System.out.println("-------INICIA IMPRESION CUENTA CORRIENTE--------");
        super.imprimir();
        System.out.println("Sobregiro: " + sobregiro);
        System.out.println("---- ---------------");
    }

    

}
