package cv.agenda.domain.telefono;

public interface IFuncionalidadesTelefono {
    
    public boolean validarTelefono(String telefono);
    
    public String trimTelefono(String telefono);
    
    public String salidaTelefonoFormateado(String telefono);
    
    public String solicitarTelefonoUsuario();
    
    public ESubtipoTelefono mapearTelefono(String telefono);
    
    public ESubtipoTelefono seleccionarTipoTelefono();
    
    
}
