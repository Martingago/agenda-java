package cv.agenda.domain.contacto;

import cv.agenda.domain.telefono.FuncionalidadesTelefonoImpl;
import cv.agenda.domain.telefono.IFuncionalidadesTelefono;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FuncionalidadesContactoImpl implements IFuncionalidadesContacto {

    IFuncionalidadesTelefono utilsTelefono;

    public FuncionalidadesContactoImpl() {
        this.utilsTelefono = new FuncionalidadesTelefonoImpl();

    }

    @Override
    public String crearID(ESubtipoContacto tipo, int ultimoID) {
        return 'C' + tipo.getCodigo() + String.format("%06d", ultimoID);
    }

    @Override
    public int getNumberID(String id) {
        String numberStr = id.substring(2); //obtenemos el valor numerico del ID
        return Integer.parseInt(numberStr); //convertimos la cadena de numero a numero
    }

    @Override
    public ESubtipoContacto mapearTipoContacto(String tipoContactoTexto) {
        return switch (tipoContactoTexto) {
            case "AMIGO" ->
                ESubtipoContacto.AMIGO;
            case "FAMILIA" ->
                ESubtipoContacto.FAMILIA;
            case "EMPRESA" ->
                ESubtipoContacto.EMPRESA;
            default ->
                ESubtipoContacto.SIN_ASIGNAR;
        };
    }

    @Override
    public ESubtipoContacto seleccionarTipoContacto() {
        Scanner sc = new Scanner(System.in);
        int tipoContacto;
        boolean menuContacto = false;

        while (!menuContacto) {
            try {
                System.out.println("Selecciona el tipo de contacto:");
                System.out.println("1: CONTACTO");
                System.out.println("2: CONTACTO EMPRESA");
                System.out.println("3: CONTACTO FAMILIA");
                System.out.println("4: CONTACTO AMIGO");
                System.out.println("0: SALIR");
                tipoContacto = sc.nextInt();
                switch (tipoContacto) {
                    case 0 -> {
                        menuContacto = true;
                    }
                    case 1 -> {
                        System.out.println("Contacto por defecto");
                        return ESubtipoContacto.SIN_ASIGNAR;
                    }
                    case 2 -> {
                        System.out.println("Contacto EMPRESA");
                        return ESubtipoContacto.EMPRESA;
                    }
                    case 3 -> {
                        System.out.println("Contacto FAMILIA");
                        return ESubtipoContacto.FAMILIA;
                    }
                    case 4 -> {
                        System.out.println("Contacto AMIGO");
                        return ESubtipoContacto.AMIGO;
                    }
                    default -> {
                        System.out.println("Valor no válido, introduce un parámetro entre [0-4]");
                        sc.next();
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes introducir un valor numerico");
                sc.next();
            }
        }
        return null;
    }



}
