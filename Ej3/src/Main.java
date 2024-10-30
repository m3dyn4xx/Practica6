
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String opcion =" ";
        while (!opcion.equals("d")) {
            opcion = mostrarOpciones();

            switch (opcion) {
                case "a":
                    opcionA();
                    break;
                case "b":
                    opcionB();
                    break;
                case "c":
                    opcionC();
                    break;
            }
        }
        System.out.println("Has terminado");

    }

    public static String mostrarOpciones(){
        System.out.println("Seleccione un opcion: ");
        System.out.println("a) Calcular la edad de una persona.");
        System.out.println("b) Número pares y primos.");
        System.out.println("c) Palíndromo.");
        System.out.println("d) Salir.");

        return seleccionarOpciones();
    }

    public static String seleccionarOpciones(){
        Scanner sc = new Scanner(System.in);
        boolean hasta = true;
        String opcionOk = "";
        do {
            try{
                System.out.print("Opcion a seleccionar: ");
                String opcion = sc.nextLine();

                Pattern patron = Pattern.compile("^(a|b|c|d)$");
                Matcher matcher = patron.matcher(opcion);
                if (!matcher.matches()) {
                    throw new opcionIncorrectaException();
                }
                else {
                    opcionOk = opcion;
                }
                hasta = false;
            }
            catch(opcionIncorrectaException e){
                System.out.println("Opcion no valida. Escribe unicamente las opciones validas en minusuclas");
            }
        }while(hasta);
        return opcionOk;
    }

    public static void opcionA(){
        System.out.println(" ");
        LocalDate fechaHoy = LocalDate.now();

        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        boolean hasta = true;
        LocalDate fechaNac = null;
        System.out.println("Has seleccionado la opcion A");
        do {
            try{
                System.out.print("Por favor introduzca su fecha de naciemiento: ");
                String fehcaString = sc.nextLine();
                fechaNac = LocalDate.parse(fehcaString, formato);
                hasta = false;
            }catch (DateTimeParseException e){
                System.out.println("Fecha no valida.");
            }
        }while(hasta);

        //Calcuar Edad
        Period periodoFechaNac_FechaHoy = Period.between(fechaNac, fechaHoy);
        int edad = periodoFechaNac_FechaHoy.getYears();

        //Calcular Dias restantes nuevo cumpleaños
        LocalDate FechaProximoCumpleaños = fechaNac.plusYears(edad+1);
        Duration duracionFechaHoy_FechaPorximoCumpleaños = Duration.between(fechaHoy.atStartOfDay(),FechaProximoCumpleaños.atStartOfDay());

        //Mostar Valores
        System.out.println("");
        System.out.println("--Ficha--");
        System.out.println("");
        System.out.println("Tienes "+edad+" años");
        if (fechaNac.getDayOfMonth() == fechaHoy.getDayOfMonth() && fechaNac.getMonth() == fechaHoy.getMonth()) {
            System.out.println("¡Felicidades, hoy es tu cumple \uD83C\uDF82!");
        }
        System.out.println("¡Faltan "+duracionFechaHoy_FechaPorximoCumpleaños.toDays()+" dias para tu cumple!");
        System.out.println("");
    }
    public static void opcionB() {
        Scanner sc = new Scanner(System.in);
        System.out.println(" ");

        System.out.println("Has seleccionado la opcion B");
        System.out.println("Introduce 2 números");
        System.out.print("Número 1: ");
        int numero1 = sc.nextInt();
        System.out.print("Número 2: ");
        int numero2 = sc.nextInt();

        for (int i = Math.min(numero1, numero2); i <= Math.max(numero1, numero2); i++) {
            System.out.print(i + " es ");

            // Comprobar si el número es par o impar
            if (i % 2 == 0) {
                System.out.println("par.");
            } else {
                // Si es impar, verificar si es primo
                boolean esPrimo = true;
                if (i < 2) {
                    esPrimo = false;
                } else {
                    for (int j = 2; j <= Math.sqrt(i); j++) {
                        if (i % j == 0) {
                            esPrimo = false;
                            break;
                        }
                    }
                }

                // Imprimir si es primo o no
                if (esPrimo) {
                    System.out.println("impar y es primo.");
                } else {
                    System.out.println("impar y no es primo.");
                }
            }

        }
        System.out.println(" ");

    }
    public static void opcionC(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Has seleccionado la opcion C");
        System.out.print("Ingrese una cadena por favor: ");
        String textoOriginal = sc.nextLine();
        String texto = textoOriginal.toLowerCase();

        String textoInvertido = "";

        for (int i = texto.length() - 1; i >= 0; i--) {
            textoInvertido += texto.charAt(i);
        }

        if (texto.equals(textoInvertido)) {
            System.out.println("La cadena: "+ textoOriginal+" es un palíndromo.");
        } else {
            System.out.println("La cadena: "+ textoOriginal+" no es un palíndromo.");
        }
    }
}
