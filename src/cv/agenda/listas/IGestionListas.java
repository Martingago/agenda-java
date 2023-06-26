package cv.agenda.listas;

import cv.agenda.domain.*;
import java.util.ArrayList;
import java.util.List;

public interface IGestionListas {

    public <T> ArrayList<T> getSubdataList(String id, String data, Class<T> clazz);

    public String obtenerEmails(List<Email> listaEmail);

    public String obtenerTelefonos(List<Telefono> listaTelefono);
    
}
