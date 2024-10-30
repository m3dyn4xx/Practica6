import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Captura el nombre del cliente
        String nombreCliente = nombre();

        // Variables para acumulación de totales
        float totalSinIVA = 0;
        float totalIVA = 0;
        char otraLinea;
        String detallesFactura = ""; // Almacena el detalle de cada producto

        // Entrada de productos de forma secuencial
        do {
            int cantidad = cantidad();
            float precioUnitario = precio();
            int tipoIVA = iva();

            // Calcula el subtotal del producto
            float subtotal = cantidad * precioUnitario;
            float ivaProducto = subtotal * tipoIVA / 100;

            // Acumula los totales
            totalSinIVA += subtotal;
            totalIVA += ivaProducto;

            // Construye el detalle para este producto
            detallesFactura += cantidad + " x " + precioUnitario + " = " + subtotal + "\n";
            detallesFactura += tipoIVA + "% de " + subtotal + " = " + ivaProducto + "\n";

            // Pregunta si desea añadir otro producto
            System.out.print("¿Desea agregar otro producto? (s/n): ");
            otraLinea = sc.next().toLowerCase().charAt(0);
        } while (otraLinea == 's');

        // Mostrar la factura
        float totalConIVA = totalSinIVA + totalIVA;
        System.out.println("\n--- FACTURA ---");
        System.out.println("NOMBRE: " + nombreCliente);
        System.out.println("FECHA: " + LocalDate.now());
        System.out.println("LINEAS DE DETALLE:");
        System.out.println(detallesFactura);
        System.out.println("TOTAL SIN IVA: %.2f"+totalSinIVA);
        System.out.println("TOTAL IVA: %.2f"+totalIVA);
        System.out.println("TOTAL CON IVA: %.2f"+totalConIVA);
    }

    public static String nombre() {
        Scanner sc = new Scanner(System.in);
        Matcher matcher;
        String nombreBien = "";
        boolean hasta = true;
        do {
            System.out.print("Introduce tu nombre: ");
            String nombre = sc.nextLine();
            nombreBien = "";
            Pattern patron = Pattern.compile("^[A-Z][a-z]{1,19}$");
            matcher = patron.matcher(nombre);
            if (matcher.matches()) {
                nombreBien = nombre;
                hasta = false;
            } else {
                System.out.println("Tu nombre debe de seguir esta sintaxis: Xxxx. Con un minimo de 2 Caracteres y un maximo de 20 Caracteres. ");
            }
        } while (hasta);

        return nombreBien;
    }

    public static int cantidad() {
        Scanner sc = new Scanner(System.in);
        boolean hasta = true;
        int cantidad = 0;

        do {
            try {
                System.out.print("Ingrese el numero de unidades adquiridas: ");
                cantidad = sc.nextInt();
                hasta = false;
            } catch (InputMismatchException e) {
                System.out.println("Cantidad Invalida");
                sc.next();
            }
        } while (hasta);
        return cantidad;
    }

    public static float precio() {
        Scanner sc = new Scanner(System.in);
        boolean hasta = true;
        float precio = 0f;
        do {
            try {
                System.out.print("Ingrese el precio de los productos: ");
                precio = sc.nextFloat();
                hasta = false;
            } catch (InputMismatchException e) {
                System.out.println("Precio Invalido");
                sc.next();
            }
        } while (hasta);
        return precio;
    }

    public static int iva() {
        Scanner sc = new Scanner(System.in);
        boolean hasta = true;
        int iva = 0;
        do {
            try {
                System.out.print("Ingrese el % de IVA de los productos: ");
                iva = sc.nextInt();
                hasta = false;
            } catch (InputMismatchException e) {
                System.out.println("IVA Invalido");
                sc.next();
            }
        } while (hasta);
        return iva;
    }
}