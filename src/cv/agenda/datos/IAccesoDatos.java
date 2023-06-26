package cv.agenda.datos;

import cv.agenda.domain.Contacto;
import cv.agenda.excepciones.*;
import java.util.*;

public interface IAccesoDatos {

    boolean existe(String nombreRecurso) throws AccesoDatosEx;

    HashMap<String, Contacto> listar(String nombreRecurso) throws LecturaDatosEx;

    void escribir(String nombreRecurso, Contacto contacto, boolean anexo) throws EscrituraDatosEx;

    Contacto buscar(String nombreRecurso, String nombreContacto) throws LecturaDatosEx;

    void crear(String nombreRecurso) throws AccesoDatosEx;

    void eliminar(String nombreRecurso) throws AccesoDatosEx;
    
    Contacto extraerContactoFichero(String data);

}
