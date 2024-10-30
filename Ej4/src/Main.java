import javax.swing.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null,"Bienvenido al banco MEDINA");
        String nombre = solicitar_nombre();
        int numeroCuenta = solicitar_numeroCuenta();
        int cantidadInicial = solicitar_cantidadInicial();
        int cantidad = solicitar_cantidad();
        String accion = solicitar_accion();

        int resultado = calcularDatos(cantidadInicial,cantidad,accion);

        Pattern patron = Pattern.compile("^(s|n)$");
        boolean hasta = true;

        String masAcciones = "";
        do {
            try {
                masAcciones = JOptionPane.showInputDialog(null,"Desea realizar alguna otra accion?");
                Matcher matcher = patron.matcher(masAcciones);
                if(!matcher.matches()){
                    throw new NombreIncorrectoException();
                }
                hasta = false;
            }
            catch(NombreIncorrectoException e){
                JOptionPane.showMessageDialog(null,"Dato Incorrecto, introduzca s(si) o n(no)");
            }
        }while (hasta);

        do {
            solicitar_cantidad();
            solicitar_accion();
            calcularDatos(cantidadInicial,cantidad,accion);
        }while (masAcciones.equals("s"));


        JOptionPane.showMessageDialog(null, resultado);
    }

    public static String solicitar_nombre(){

        Pattern patron = Pattern.compile("^[A-Z][a-zA-Z]+$");
        boolean hasta = true;
        String nombre = "";
        do {
            try {
                nombre = JOptionPane.showInputDialog("Escribe tu nombre:");
                Matcher matcher = patron.matcher(nombre);
                if(!matcher.matches()){
                    throw new NombreIncorrectoException();
                }
                hasta = false;
            }
            catch(NombreIncorrectoException e){
                JOptionPane.showMessageDialog(null,"El nombre no es correcto");

            }

        }while (hasta);
    return nombre;
    }
    public static int solicitar_numeroCuenta(){
        String numeroCuentaString = " ";
        Pattern patron = Pattern.compile("^[0-9]+$");
        boolean hasta = true;
        do {
            try {
                numeroCuentaString = JOptionPane.showInputDialog("Escribe tu numero de cuenta:");
                Matcher matcher = patron.matcher(numeroCuentaString);
                if(!matcher.matches()){
                    throw new NombreIncorrectoException();
                }
                hasta = false;
            }
            catch(NombreIncorrectoException e){
                JOptionPane.showMessageDialog(null,"El numero de cuenta no es correcto");

            }

        }while (hasta);
        int numeroCuenta = 0;
        numeroCuenta += Integer.parseInt(numeroCuentaString);
        return numeroCuenta;
    }
    public static int solicitar_cantidadInicial(){
        String canitdaIncialString = " ";
        Pattern patron = Pattern.compile("^[0-9]+$");
        boolean hasta = true;
        do {
            try {
                canitdaIncialString = JOptionPane.showInputDialog("Escribe la canitdad inicial:");
                Matcher matcher = patron.matcher(canitdaIncialString);
                if(!matcher.matches()){
                    throw new NombreIncorrectoException();
                }
                hasta = false;
            }
            catch(NombreIncorrectoException e){
                JOptionPane.showMessageDialog(null,"Cantidad inicial no es correcta, solo pueden ser numeros");

            }

        }while (hasta);
        int canitdadInicial = 0;
        canitdadInicial += Integer.parseInt(canitdaIncialString);
        return canitdadInicial;
    }
    public static int solicitar_cantidad() {
        String cantidadString = " ";
        Pattern patron = Pattern.compile("^[0-9]+$");
        boolean hasta = true;
        do {
            try {
                cantidadString = JOptionPane.showInputDialog("Escribe la cantidad que deseas añadir o restar:");
                Matcher matcher = patron.matcher(cantidadString);
                if (!matcher.matches()) {
                    throw new NombreIncorrectoException();
                }
                hasta = false;
            } catch (NombreIncorrectoException e) {
                JOptionPane.showMessageDialog(null, "La cantidad no es correcta");

            }

        } while (hasta);
        int cantidad = 0;
        cantidad += Integer.parseInt(cantidadString);
        return cantidad;
    }
    public static String solicitar_accion(){
        Pattern patron = Pattern.compile("^(imposicion|reintegro)$");
        boolean hasta = true;
        String accion = "";
        do {
            try {
                accion = JOptionPane.showInputDialog("Tipo de movimiento (imposicion o reintegro):");
                Matcher matcher = patron.matcher(accion);
                if(!matcher.matches()){
                    throw new NombreIncorrectoException();
                }
                hasta = false;
            }
            catch(NombreIncorrectoException e){
                JOptionPane.showMessageDialog(null,"El tipo de movimiento no es correcto");

            }

        }while (hasta);
        return accion;
    }

    public static int calcularDatos(int cantidadInicial, int cantidad, String accion){
        if (accion.equals("imposicion")){
            cantidadInicial += cantidad;
        }
        else if (accion.equals("reintegro")){
            cantidadInicial -= cantidad;
        }
        return cantidadInicial;
    }
    public class hola{}

    }
