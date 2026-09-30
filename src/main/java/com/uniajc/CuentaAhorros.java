package com.uniajc;

public class CuentaAhorros extends Cuenta {
    protected boolean activa;

    public CuentaAhorros(float saldo, float tasa) {
        super(saldo, tasa);
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
    }
}