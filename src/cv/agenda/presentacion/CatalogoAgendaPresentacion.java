package cv.agenda.presentacion;

import cv.agenda.domain.Contacto;
import cv.agenda.domain.contacto.FuncionalidadesContactoImpl;
import cv.agenda.domain.contacto.IFuncionalidadesContacto;
import cv.agenda.servicio.ServiciosAgendaImpl;
import cv.agenda.servicio.IServiciosAgenda;
import java.util.Scanner;

public class CatalogoAgendaPresentacion {

    public static void main(String[] args) {
        var opcion = -1;
        Scanner sc = new Scanner(System.in);
        IServiciosAgenda agenda = new ServiciosAgendaImpl();
        IFuncionalidadesContacto uc = new FuncionalidadesContactoImpl();
        while (opcion != 0) {
            System.out.println("ELIGE UNA OPCION: \n"
                    + "[1] Reiniciar Agenda \n"
                    + "[2] Agregar contacto \n"
                    + "[3] Listar contactos\n"
                    + "[4]Buscar contacto\n"
                    + "[0] Salir");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> {
                    agenda.iniciarAgendaContactos();
                    break;
                }
                case 2 -> {
                    //Cargar datos de la agenda para obtener el ultimo id:
                   Contacto newContacto = agenda.pedirDatosContacto();
                    agenda.agregarContacto(newContacto);
                    break;
                }
                case 3 -> {
                    agenda.listarContactos();
                    break;
                }
                case 4 -> {
                    System.out.println("Introduce el ID de usuario a buscar:");
                    var buscar = sc.nextLine();
                    agenda.buscarContacto(buscar);
                    break;
                }
                case 0 -> {
                    System.out.println("Fin de programa");
                    break;
                }
                default -> {
                    System.out.println("Introduce un numero valido");
                }

            }

        }

    }
}
