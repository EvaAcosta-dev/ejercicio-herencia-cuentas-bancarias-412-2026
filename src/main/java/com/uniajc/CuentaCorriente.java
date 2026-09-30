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
        if (sobregiro > 0) {
            if (cantidad <= sobregiro) {
                cantidad -= sobregiro;
            } else {
                float sobrante = cantidad - sobregiro;
                sobregiro = 0;
                saldo += sobrante;
            }
        } else {
            saldo += cantidad;
        }
        numeroConsignaciones++;
    }

    public void imprimir() {
        System.out.println("Saldo: " + saldo);
        System.out.println("Comision mensual: " + comisionMensual);
        System.out.println("Numero de transacciones realizadas: " + (numeroConsignaciones + numeroRetiros));
        System.out.println("Sobregiro: " + sobregiro);
    }
}