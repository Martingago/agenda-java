package cv.agenda.domain;

import cv.agenda.domain.telefono.ESubtipoTelefono;

public class Telefono {
    private String numero;
    private ESubtipoTelefono subtipo;


    public Telefono(ESubtipoTelefono subtipo, String numero) {
        this.subtipo = subtipo;
        this.numero = numero;
    }
    
    public Telefono(String numero){
    this.numero = numero;
    }

    public ESubtipoTelefono getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(ESubtipoTelefono subtipo) {
        this.subtipo = subtipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Telefono{" + "numero=" + numero + ", subtipo=" + subtipo + '}';
    }

}
