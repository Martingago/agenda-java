package cv.agenda.listas;

import cv.agenda.domain.Email;
import cv.agenda.domain.Telefono;
import cv.agenda.domain.telefono.ESubtipoTelefono;
import cv.agenda.domain.telefono.FuncionalidadesTelefonoImpl;
import cv.agenda.domain.telefono.IFuncionalidadesTelefono;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class GestionListasImpl implements IGestionListas {

    private final IFuncionalidadesTelefono funcionalidadesTelefono;

    public GestionListasImpl() {
        this.funcionalidadesTelefono = new FuncionalidadesTelefonoImpl();
    }

    /**
     * Devuelve una lista de objetos [telefono - Email] que se encuentran dentro
     * de los datos de contacto extraidos de una linea CSV
     *
     * @param <T> Tipo de lista que se va a devolver, puede ser tanto de tipo
     * Telefono, como de tipo Email
     * @param id -> Sirve para que a la hora de crear los objetos, estes tengan
     * atribuidos el ID del objeto padre (Contacto)
     * @param data -> String de datos extraido del documento CSV
     * @param clazz -> Clase del objeto que se va a generar: Telefono.class o
     * Email.class
     * @return ArrayList: listData que contiene la informacion de los datos que
     * se han extraido de un String
     */
    @Override
    public <T> ArrayList<T> getSubdataList(String id, String data, Class<T> clazz) {
        ArrayList<T> listData = new ArrayList<>();
        String[] datos = data.replaceAll("\"", "").split(";"); //Convertimos el String en un Array de Strings para gestionar los datos recibidos
        try {
            for (String dato : datos) {
                T objeto;
                //En caso de que el objeto sea de clase Email:
                if (clazz.equals(Email.class)) {
                    objeto = clazz.getDeclaredConstructor(String.class).newInstance(dato);
                } else if (clazz.equals(Telefono.class)) {
                    //Subdivimos el String recibido en un Array[]: El array que recibimos conforma el objeto Telefono, por lo que debemos obtener sus atributos
                    String[] atributos = dato.split(":");
                    ESubtipoTelefono subtipo = funcionalidadesTelefono.mapearTelefono(atributos[0]);
                    String telefono = atributos[1]; //NUM TELEFONO;
                    objeto = clazz.getDeclaredConstructor(ESubtipoTelefono.class, String.class).newInstance(subtipo, telefono);
                } else {
                    throw new IllegalArgumentException("Clase no válida");
                }
                listData.add(objeto);
            }

        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            System.err.println(e);
        }
        return listData;
    }

    @Override
    /**
     * Obtiene un email desde una lista de Emails
     *
     * @param listaEmail
     * @return
     */
    public String obtenerEmails(List<Email> listaEmail) {
        StringBuilder emailBuilder = new StringBuilder();
        for (Email mail : listaEmail) {
            emailBuilder.append(mail.getMail()).append(";");
        }
        String mails = emailBuilder.toString();
        if (mails.length() > 0) {
            mails = mails.substring(0, mails.length() - 1);
        }
        return mails;
    }

    @Override
    public String obtenerTelefonos(List<Telefono> listaTelefono) {
        StringBuilder telefonoBuilder = new StringBuilder();
        for (Telefono telefono : listaTelefono) {
            telefonoBuilder.append(telefono.getSubtipo()).append(":").append(telefono.getNumero()).append(";");
        }
        String telefonos = telefonoBuilder.toString();
        if (telefonos.length() > 0) {
            telefonos = telefonos.substring(0, telefonos.length() - 1);
        }
        return telefonos;
    }

}
