package cv.agenda.datos;

import cv.agenda.domain.*;
import cv.agenda.domain.contacto.*;
import cv.agenda.excepciones.*;
import cv.agenda.listas.*;
import java.io.*;
import java.util.*;

public class AccesoDatosImpl implements IAccesoDatos {

    private final IGestionListas lista;
    private final IFuncionalidadesContacto funcionalidadesContacto;

    public AccesoDatosImpl() {
        this.lista = new GestionListasImpl();
        this.funcionalidadesContacto = new FuncionalidadesContactoImpl();
    }

    @Override
    public Contacto extraerContactoFichero(String data) {
        //Obtener data desde el archivo
        String[] datosLinea = data.split(",");
        //obtener ID
        String id = datosLinea[0];
        ESubtipoContacto subtipo = funcionalidadesContacto.mapearTipoContacto(datosLinea[1]);
        //obtener nombre
        String nombre = datosLinea[2];
        //Listar emails
        ArrayList<Email> listEmail = lista.getSubdataList(id, datosLinea[3], Email.class);
        //Listar telefonos:
        ArrayList<Telefono> listNumeroTelefono = lista.getSubdataList(id, datosLinea[4], Telefono.class);
        //obtener direccion
        String direccion = "";
        if (datosLinea[5].isEmpty()) {
            direccion = "";
        } else {
            direccion = datosLinea[5];
        }
        //Crear un objeto contacto
        return new Contacto(id, subtipo, nombre, listEmail, listNumeroTelefono, direccion);
    }

    @Override
    public boolean existe(String nombreRecurso) {
        var archivo = new File(nombreRecurso);
        return archivo.exists();
    }

    @Override
    public LinkedHashMap<String, Contacto> listar(String nombreRecurso) throws LecturaDatosEx {
        var archivo = new File(nombreRecurso); //fichero del que se extrae la informacion
        LinkedHashMap<String, Contacto> contactos = new LinkedHashMap<>(); //Map en el que se cargan los datos
        try {
            var entrada = new BufferedReader(new FileReader(nombreRecurso));
            String linea = null;
            linea = entrada.readLine();
            while (linea != null) {
                //Extrae dato linea y lo convierte en un objeto contacto
                Contacto contacto = extraerContactoFichero(linea);
                contactos.put(contacto.getId(), contacto);
                linea = entrada.readLine();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEx("Exeption al encontrar el archivo" + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEx("Exeption al leer el archivo" + ex.getMessage());
        }
        return contactos;
    }

    @Override
    public void escribir(String nombreRecurso, Contacto contacto, boolean anexo) throws EscrituraDatosEx {
        var archivo = new File(nombreRecurso);
        try {
            var salida = new PrintWriter(new FileWriter(archivo, anexo));
            String id = contacto.getId();
            String subtipo = contacto.getSubtipoContacto().toString();
            String nombre = contacto.getNombre();
            List<Email> listMail = contacto.getListEmail();
            String stringMail = lista.obtenerEmails(listMail);
            List<Telefono> listTelefono = contacto.getListNumerosTelefono();
            String stringTelefonos = lista.obtenerTelefonos(listTelefono);
            String direccion = contacto.getDireccion();
            if (direccion.isEmpty()) {
                direccion = "Sin especificar";
            }
            salida.append(id).append(",").append(subtipo).append(",").append(nombre).append(",").append(stringMail).append(",").append(stringTelefonos).append(",").append(direccion).append(",").append("\n");
            salida.close();
            System.out.println("Contacto añadido al archivo");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new EscrituraDatosEx("Exeption al escribir Contactos" + ex.getMessage());
        }
    }

    @Override
    public Contacto buscar(String nombreRecurso, String idContacto) throws LecturaDatosEx {
        HashMap<String, Contacto> contactos = listar(nombreRecurso);
        int indice = 0;
        for (Contacto contacto : contactos.values()) {
            if (contacto.getId().equals(idContacto)) {
                System.out.println("Contacto encontrado en: " + indice);
                return contacto;
            }
            indice++;
        }
        System.out.println("No se ha encontrado el usuario con ID: " + idContacto);
        Contacto resultado = null;

        return resultado;
    }

    @Override
    public void crear(String nombreRecurso) throws AccesoDatosEx {
        var archivo = new File(nombreRecurso);
        try {
            var salida = new PrintWriter(new FileWriter(nombreRecurso));
            salida.close();
            System.out.println("Se ha creado el archivo");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new AccesoDatosEx("Exeption al crear archivo" + ex.getMessage());
        }
    }

    @Override
    public void eliminar(String nombreRecurso) throws AccesoDatosEx {
        var archivo = new File(nombreRecurso);
        if (archivo.exists()) {
            archivo.delete();
        }
        System.out.println("Se ha borrado el archivo");
    }

}
