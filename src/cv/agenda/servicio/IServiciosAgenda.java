package cv.agenda.servicio;

import cv.agenda.domain.*;

public interface IServiciosAgenda {

    String NOMBRE_RECURSO = "SALIDA.csv";

    void agregarContacto(Contacto newContacto);

    void listarContactos();

    void buscarContacto(String idContacto);

    void iniciarAgendaContactos();
    
    int obtenerUltimoValorID();
    
    void setUltimoValorID(int ultimoValorID); 
    
    public Contacto pedirDatosContacto();

}
