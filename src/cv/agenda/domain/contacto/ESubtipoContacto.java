package cv.agenda.domain.contacto;

public enum ESubtipoContacto {
    EMPRESA("E"),
    AMIGO("A"),
    FAMILIA("F"),
    SIN_ASIGNAR("U");

    private String codigo;

    ESubtipoContacto(String codigo) {
        this.codigo = codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return this.codigo;
    }

}
