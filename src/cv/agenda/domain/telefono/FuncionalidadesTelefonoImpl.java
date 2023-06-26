package cv.agenda.domain.telefono;

import cv.agenda.utils.GeneralUtilsImpl;
import java.util.Scanner;

public class FuncionalidadesTelefonoImpl implements IFuncionalidadesTelefono {

    private final GeneralUtilsImpl utils;

    public FuncionalidadesTelefonoImpl() {
        utils = new GeneralUtilsImpl();
    }

    @Override
    public boolean validarTelefono(String telefono) {
        //si tiene prefijo, se comprueba que tenga de 12 a 13 caracteres
        if (telefono.startsWith("+") && telefono.length() >= 12 && telefono.length() <= 13) {
            String telefonoString = telefono.substring(1);
            return telefonoString.matches("\\d+");
        }
        return telefono.length() == 9 && telefono.matches("\\d+");
    }

    @Override
    public String trimTelefono(String telefono) {
        return telefono.replaceAll("\\s", "");
    }

    @Override
    public String salidaTelefonoFormateado(String telefono) {
        String outputTelefono = switch (telefono.length()) {
            case 12 ->
                telefono.substring(0, 3) + " " + telefono.substring(3, 6) + " "
                + telefono.substring(6, 9) + " " + telefono.substring(9);
            case 13 ->
                telefono.substring(0, 4) + " " + telefono.substring(4, 7) + " "
                + telefono.substring(7, 10) + " " + telefono.substring(10);
            default ->
                telefono.substring(0, 3) + " " + telefono.substring(3, 6) + " "
                + telefono.substring(6);
        };
        return outputTelefono;
    }

    @Override
    public ESubtipoTelefono mapearTelefono(String tipoTelefono) {
        return switch (tipoTelefono) {
            case "FIJO" ->
                ESubtipoTelefono.FIJO;
            case "MOVIL" ->
                ESubtipoTelefono.MOVIL;
            case "OFICINA" ->
                ESubtipoTelefono.OFICINA;
            default ->
                ESubtipoTelefono.OTRO;
        };
    }

    @Override
    public ESubtipoTelefono seleccionarTipoTelefono() {

        ESubtipoTelefono selectedTipoTelefono;
        System.out.println("Indica el tipo de Telefono:");
        System.out.println("[1] Fijo");
        System.out.println("[2] Movil");
        System.out.println("[3] Oficina");
        System.out.println("[4] Otro");
        int opcion = 0;
        boolean opcionValida = false;

        while (!opcionValida) {
            opcion = utils.validarNumero(); //solicita y valida un numero al usuario
            if (opcion >= 1 && opcion <= 4) {
                break;
            } else {
                System.out.println("Error, selecciona un valor valido");
            }
        }
        switch (opcion) {
            case 1 -> {
                selectedTipoTelefono = ESubtipoTelefono.FIJO;
                break;
            }
            case 2 -> {
                selectedTipoTelefono = ESubtipoTelefono.MOVIL;
                break;
            }
            case 3 -> {
                selectedTipoTelefono = ESubtipoTelefono.OFICINA;
                break;
            }
            case 4 -> {
                selectedTipoTelefono = ESubtipoTelefono.OTRO;
                break;
            }
            default -> {
                selectedTipoTelefono = ESubtipoTelefono.OTRO;
            }
        }
        return selectedTipoTelefono;
    }

    @Override
    public String solicitarTelefonoUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el número del telefono [Puede incluir o no prefijo]");
        String telefono = "";
        boolean telefonoValido = false;
        while (!telefonoValido) {
            telefono = sc.nextLine();
            telefono = trimTelefono(telefono);
            System.out.println("Telefono:" + telefono);
            //comprobamos que el telefono que introduce el usuario es válido
            if (validarTelefono(telefono)) {
                System.out.println("Telefono válido");
                telefonoValido = true;
            } else {
                System.out.println("Telefono no válido, introduce un telefono válido");
            }
        }
        return telefono;
    }

}
