package com.uniajc;

public class Main{
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE CUENTAS BANCARIAS ===");

        System.out.println("\n--- Cuenta de Ahorros ---");
        CuentaAhorros cuentaAhorros = new CuentaAhorros(2500, 5);
        System.out.println("Cuenta creada");
        cuentaAhorros.imprimir();

        System.out.println("Consignando 500...");
        cuentaAhorros.consignar(500);
        cuentaAhorros.imprimir();

        System.out.println("Retirando 1000...");
        cuentaAhorros.retirar(1000);
        cuentaAhorros.imprimir();

        System.out.println("Extacto mensual...");
        cuentaAhorros.extractoMensual();
        cuentaAhorros.imprimir();

        System.out.println("\n--- Cuenta Corriente ---");
        CuentaCorriente cuentaCorriente = new CuentaCorriente(1000, 2);
        System.out.println("Cuenta creada");
        cuentaCorriente.imprimir();

        System.out.println("Consignando 300...");
        cuentaCorriente.consignar(300);
        cuentaCorriente.imprimir();

        System.out.println("Retirando 1500...");
        cuentaCorriente.retirar(1500);
        cuentaCorriente.imprimir();

        System.out.println("Consignando 400 para cubrir sobregiro...");
        cuentaCorriente.consignar(400);
        cuentaCorriente.imprimir();

        System.out.println("\n---  Cuenta inactiva ---");
        CuentaAhorros cuentaInactiva = new CuentaAhorros(1000, 3);

        cuentaInactiva.imprimir();
        System.out.println("Intentando retirar 500 de una cuenta inactiva:");
        cuentaInactiva.retirar(500);

    }
}