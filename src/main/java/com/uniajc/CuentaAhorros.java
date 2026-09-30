package com.uniajc;

public class CuentaAhorros extends Cuenta {
    protected boolean activa;

    public CuentaAhorros(float saldo, float tasa) {
        super(saldo, tasa);
    }

    @Override
    public void retirar(float cantidad) { }

    @Override
    public void consignar(float cantidad) { }

    @Override
    public void extractoMensual() { }

    public void imprimir() { }
}