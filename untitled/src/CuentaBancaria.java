public class CuentaBancaria {
    // Atributos de instancia
    private String titular;
    private double saldo;
    private int numeroCuenta;

    // Variable de clase
    private static int contadorCuentas = 0;

    // Constructor
    public CuentaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
        this.numeroCuenta = ++contadorCuentas;
    }

    // Métodos de instancia
    public void depositar(double cantidad) {
        if (cantidad > 0) {
            this.saldo += cantidad;
        }
    }

    public boolean retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            this.saldo -= cantidad;
            return true;
        }
        return false;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public int getNumeroCuenta() {
        return this.numeroCuenta;
    }

    // Método de clase
    public static int getTotalCuentas() {
        return contadorCuentas;
    }

    // Método principal para ejecutar el programa
    public static void main(String[] args) {
        // Creación y uso de instancias
        CuentaBancaria cuenta1 = new CuentaBancaria("Juan Pérez", 1000);
        CuentaBancaria cuenta2 = new CuentaBancaria("María García", 2500);

        // Operaciones con instancias
        cuenta1.depositar(500);
        cuenta2.retirar(300);

        System.out.println("Saldo cuenta 1: " + cuenta1.getSaldo());  // 1500
        System.out.println("Saldo cuenta 2: " + cuenta2.getSaldo());  // 2200
        System.out.println("Total cuentas: " + CuentaBancaria.getTotalCuentas());  // 2
    }
}