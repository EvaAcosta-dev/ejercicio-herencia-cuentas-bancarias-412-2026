package com.uniajc;

public class Main{
    public static void main(String[] args) {
<<<<<<< HEAD
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

=======
        System.out.println("Hello world desde la rama Cuentas!");

        CuentaAhorros cuentaAhorros = new CuentaAhorros(15000.0f, 0.05f);

        cuentaAhorros.imprimir(); // Saldo: 15000.0f, Activa: true

        cuentaAhorros.consignar(5000.0f); // Saldo: 20000.0f
        cuentaAhorros.retirar(2000.0f); // Saldo: 18000.0f

        cuentaAhorros.imprimir(); // Saldo: 18000.0f, Activa: true

        cuentaAhorros.retirar(1000.0f); // Saldo: 17000.0f

        cuentaAhorros.extractoMensual(); // Comision mensual: 0.0f, Saldo: 17000.0f
    
        cuentaAhorros.imprimir(); // Saldo: 17000.0f, Activa: true

        cuentaAhorros.retirar(7000.0f); // Saldo: 10000.0f
        cuentaAhorros.retirar(1000.0f); // Saldo: 9000.0f

        cuentaAhorros.imprimir(); // Saldo: 9000.0f, Activa: false

        cuentaAhorros.extractoMensual(); // Comision mensual: 0.0f, Saldo: 9000.0f
    
        cuentaAhorros.consignar(11000.0f);
        cuentaAhorros.retirar(500.0f);
        cuentaAhorros.extractoMensual(); // Comision mensual: 1000f, Saldo: 19500.0f
        cuentaAhorros.imprimir(); // Saldo: 20000.0f, Activa: true
    
>>>>>>> upstream/main
    }
}