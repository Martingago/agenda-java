package cv.agenda.domain.telefono;

public enum ESubtipoTelefono {
    FIJO("F"),
    MOVIL("M"),
    OFICINA("O"),
    OTRO("U");

    private String codigo;

    ESubtipoTelefono(String codigo) {
        this.codigo = codigo;
    }

    public String getTelefono() {
        return this.codigo;
    }

}
