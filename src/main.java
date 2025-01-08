import java.util.Scanner;
import java.text.DecimalFormat;

public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("==== Convertidor de Monedas ====");
            System.out.println("1. Convertir Moneda");
            System.out.println("2. Salir");
            System.out.print("Elige una opción: ");

            int eleccion = scanner.nextInt();

            if (eleccion == 1) {
                System.out.print("Ingresa la moneda base (por ejemplo, USD, EUR, ARS): ");
                String monedaBase = scanner.next().toUpperCase();

                System.out.print("Ingresa la moneda destino (por ejemplo, USD, EUR, ARS): ");
                String monedaDestino = scanner.next().toUpperCase();

                System.out.print("Ingresa la cantidad a convertir: ");
                double cantidad = scanner.nextDouble();

                try {
                    double tasaDeCambio = ConversorDeMonedas.obtenerTasaDeCambio(monedaBase, monedaDestino);
                    double cantidadConvertida = ConversorDeMonedas.convertirMoneda(cantidad, tasaDeCambio);
                    DecimalFormat df = new DecimalFormat("#.##");
                    System.out.printf("%s %s es equivalente a %s %s%n", df.format(cantidad), monedaBase, df.format(cantidadConvertida), monedaDestino);
                } catch (Exception e) {
                    System.out.println("Error al obtener la tasa de cambio. Inténtalo de nuevo.");
                }
            } else if (eleccion == 2) {
                System.out.println("Saliendo del programa...");
                break;
            } else {
                System.out.println("Opción no válida, intenta nuevamente.");
            }
        }

        scanner.close();
    }
}
