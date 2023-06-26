package cv.agenda.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GeneralUtilsImpl implements IGeneralUtils {

    @Override
    public int validarNumero() {
        Scanner sc = new Scanner(System.in);
        boolean validNumber = false;
        int number = 0;
        while (!validNumber) {
            number = sc.nextInt();
            try {
                validNumber = true;
            } catch (InputMismatchException e) {
                System.out.println("El número no es válido. Por favor, ingrese un número entero:");
                sc.nextLine(); // Limpiar el búfer del scanner
            }
        }
        return number;
    }

    @Override
    public boolean continuarProceso() {
        Scanner sc = new Scanner(System.in);
        String respuesta;
        while (true) {
            System.out.println("¿Desea agregar más valores? (S/N)");
            respuesta = sc.nextLine().trim().toUpperCase();
            switch (respuesta) {
                case "S" -> {
                    sc.close();
                    return true;
                }
                case "N" -> {
                    sc.close();
                    return false;
                }
                default ->
                    System.out.println("Respuesta inválida. Por favor, ingrese 'S' para Sí o 'N' para No.");
            }
        }
    }

}
