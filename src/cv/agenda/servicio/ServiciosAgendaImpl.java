package cv.agenda.servicio;

import cv.agenda.datos.*;
import cv.agenda.domain.*;
import cv.agenda.domain.contacto.ESubtipoContacto;
import cv.agenda.domain.contacto.FuncionalidadesContactoImpl;
import cv.agenda.domain.contacto.IFuncionalidadesContacto;
import cv.agenda.domain.telefono.ESubtipoTelefono;
import cv.agenda.domain.telefono.FuncionalidadesTelefonoImpl;
import cv.agenda.domain.telefono.IFuncionalidadesTelefono;
import cv.agenda.excepciones.AccesoDatosEx;
import java.util.ArrayList;
import java.util.Scanner;

public class ServiciosAgendaImpl implements IServiciosAgenda {

    private final IAccesoDatos datos;
    private final IFuncionalidadesContacto utilsContacto;
    private final IFuncionalidadesTelefono utilsTelefono;
    private int ultimoValorID;

    public ServiciosAgendaImpl() {
        this.datos = new AccesoDatosImpl();
        this.utilsContacto = new FuncionalidadesContactoImpl();
        this.utilsTelefono = new FuncionalidadesTelefonoImpl();
        this.ultimoValorID = obtenerUltimoValorID();
    }

    @Override
    public void agregarContacto(Contacto newContacto) {
        boolean anexar = false;
        try {
            anexar = datos.existe(NOMBRE_RECURSO);
            datos.escribir(NOMBRE_RECURSO, newContacto, anexar);
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso a datos");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void listarContactos() {
        try {
            var contactos = this.datos.listar(NOMBRE_RECURSO);
            for (Contacto contacto : contactos.values()) {
                System.out.println(contacto);
            }
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso datos");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void buscarContacto(String idContacto) {
        Contacto resultado = null;
        try {
            resultado = this.datos.buscar(NOMBRE_RECURSO, idContacto);
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso a datos en buscar contacto");
            ex.printStackTrace(System.out);
        }
        System.out.println("Resultado busqueda: " + resultado);
    }

    @Override
    public void iniciarAgendaContactos() {
        try {
            if (this.datos.existe(NOMBRE_RECURSO)) {
                datos.eliminar(NOMBRE_RECURSO);
                datos.crear(NOMBRE_RECURSO);
            } else {
                datos.crear(NOMBRE_RECURSO);
            }
            ultimoValorID = 0;
        } catch (AccesoDatosEx ex) {
            System.out.println("Error al iniciar agenda");
            ex.printStackTrace(System.out);
        }
    }

    //MOVER A UTILIDADES CONTACTO:
    @Override
    public Contacto pedirDatosContacto() {
        Scanner sc = new Scanner(System.in);
        
        //Nombre del usuario
        System.out.println("Introduce nombre:");
        String nombre = sc.nextLine();
        //Tipo de Contacto a crear:
        ESubtipoContacto subtipo = utilsContacto.seleccionarTipoContacto();

        ArrayList<Email> listEmail = new ArrayList<>();
        System.out.println("Introduce Email:");
        String email = sc.nextLine();
        Email newEmail = new Email(email);
        listEmail.add(newEmail);
        ArrayList<Telefono> listNumerosTelefono = new ArrayList<>();
        System.out.println("Introduce el numero de telefono");
        String telefono = utilsTelefono.solicitarTelefonoUsuario();
        ESubtipoTelefono subtipoTe = utilsTelefono.seleccionarTipoTelefono();
        Telefono newTelefono = new Telefono(subtipoTe, telefono);
        listNumerosTelefono.add(newTelefono);
        
        System.out.println("Introduce direccion");
        String direccion = sc.nextLine();
        
        //ultimoValorID = obtenerUltimoValorID();
        ultimoValorID++;
        String id = utilsContacto.crearID(subtipo, ultimoValorID);
        
        return new Contacto(id, subtipo, nombre, listEmail, listNumerosTelefono, direccion);
    }

    public int getUltimoValorID() {
        return ultimoValorID;
    }

    public void setUltimoValorID(int ultimoValorID) {
        this.ultimoValorID = ultimoValorID;
    }
    
    @Override
    public int obtenerUltimoValorID() {
          try {
             if(!datos.existe(NOMBRE_RECURSO)){
                 System.out.println("[El sistema no encontró una AGENDA importada]");
                 datos.crear(NOMBRE_RECURSO);
             } 
            var contactos = this.datos.listar(NOMBRE_RECURSO);
            for (Contacto contacto : contactos.values()) {
                String idLastContacto = contacto.getId();
                int idNumber = utilsContacto.getNumberID(idLastContacto);
                if(ultimoValorID < idNumber) ultimoValorID = idNumber;
            }
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso datos");
            ex.printStackTrace(System.out);
        }
          return ultimoValorID;
    }

}
