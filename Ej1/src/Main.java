import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String descripcionMasLarga = "";
        String descripcionMasUnidades = "";
        String descripcionCaducaAntes = "";
        int maxUnidades = 0;
        LocalDate fechaMasProxima = null;

        for (int i = 0; i < 10; i++) {
            String descripcion = descripcion();
            if (descripcion.length() > descripcionMasLarga.length()) {
                descripcionMasLarga = descripcion;
            }

            int unidades = unidades();
            if (unidades > maxUnidades) {
                maxUnidades = unidades;
                descripcionMasUnidades = descripcion;
            }

            LocalDate fechaCaducidad = caducidad();
            if (fechaMasProxima == null || (fechaCaducidad != null && fechaCaducidad.isBefore(fechaMasProxima))) {
                fechaMasProxima = fechaCaducidad;
                descripcionCaducaAntes = descripcion;
            }
        }

        System.out.println("Descripción más larga: " + descripcionMasLarga);
        System.out.println("Descripción con más unidades: " + descripcionMasUnidades);
        System.out.println("Descripción que caduca antes: " + descripcionCaducaAntes);
    }

    public static String descripcion(){
        Scanner sc = new Scanner(System.in);
        Matcher matcher;
        String descripcionBien = "";
        boolean hasta = true;
        do {
            System.out.print("Descripción: ");
            String descripcion  = sc.nextLine();
            Pattern patron = Pattern.compile("^[a-zA-Z]{3,}$");
            matcher = patron.matcher(descripcion);
            if (matcher.matches()) {
                descripcionBien = descripcion;
                hasta = false;
            }
            else {
                System.out.println("Solo puedes poner letras");
            }
        }
        while (hasta);


        return descripcionBien;
    }

    public static int unidades(){
        Scanner scanner = new Scanner(System.in);
        int unidades = 0;
        boolean hasta = true;
        do {
            System.out.print("Número de unidades: ");
            try {
                unidades = scanner.nextInt();
                hasta = false;
            }
            catch (InputMismatchException e) {
                System.out.println("Número inválido.");
                scanner.nextLine();
            }

        }
        while (hasta);

        return unidades;
    }

    public static LocalDate caducidad(){
        Scanner scanner = new Scanner(System.in);
        LocalDate fechaCaducidad = null;

        boolean hasta = true;
        do {
            try {
                System.out.print("Fecha de caducidad (dd/MM/yyyy): ");
                DateTimeFormatter formato_fecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                fechaCaducidad = LocalDate.parse(scanner.nextLine(), formato_fecha);
                hasta = false;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Inténtalo de nuevo.");
            }
        }
        while (hasta);
        return fechaCaducidad;
    }
}