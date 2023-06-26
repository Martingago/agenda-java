package cv.agenda.domain.contacto;

import cv.agenda.domain.Contacto;

public interface IFuncionalidadesContacto {

    public int getNumberID(String id);

    public String crearID(ESubtipoContacto tipo, int ultimoID);

    public ESubtipoContacto mapearTipoContacto(String tipoContactoTexto);

    public ESubtipoContacto seleccionarTipoContacto();
    
    //public Contacto pedirDatosContacto();
    
}
