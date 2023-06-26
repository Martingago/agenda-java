package cv.agenda.domain;

import cv.agenda.domain.contacto.ESubtipoContacto;
import java.util.List;

public class Contacto {
    
    //private final IFuncionalidadesContacto subtipo;
    
    private String id; //Formato CX000005
    private ESubtipoContacto subtipoContacto;
    private String nombre;
    private String direccion;
    private List<Telefono> listNumerosTelefono;
    private List<Email> listEmail;

    public Contacto() {
    }

    public Contacto(String id, ESubtipoContacto subtipoContacto,  String nombre, List<Email> listEmail , List<Telefono> listNumerosTelefono, String direccion) {
        this.id = id;
        this.subtipoContacto = subtipoContacto;
        this.nombre = nombre;
        this.direccion = direccion;
        this.listNumerosTelefono = listNumerosTelefono;
        this.listEmail = listEmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ESubtipoContacto getSubtipoContacto() {
        return subtipoContacto;
    }

    public void setSubtipoContacto(ESubtipoContacto subtipoContacto) {
        this.subtipoContacto = subtipoContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Telefono> getListNumerosTelefono() {
        return listNumerosTelefono;
    }

    public void setListNumerosTelefono(List<Telefono> listNumerosTelefono) {
        this.listNumerosTelefono = listNumerosTelefono;
    }

    public List<Email> getListEmail() {
        return listEmail;
    }

    public void setListEmail(List<Email> listEmail) {
        this.listEmail = listEmail;
    }

    @Override
    public String toString() {
        return "Contacto{" + "id=" + id + ", subtipoContacto=" + subtipoContacto + ", nombre=" + nombre + ", direccion=" + direccion + ", listNumerosTelefono=" + listNumerosTelefono + ", listEmail=" + listEmail + '}';
    }
    
}
