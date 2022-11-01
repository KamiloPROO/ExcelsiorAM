package ammovil.com.excelsior.Model;

public class ModelTeam {

    String nombre, estado , plan, invitados ,saldo;


    public ModelTeam() {
    }

    public ModelTeam(String nombre, String estado, String plan, String invitados, String saldo) {
        this.nombre = nombre;
        this.estado = estado;
        this.plan = plan;
        this.invitados = invitados;
        this.saldo = saldo;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getInvitados() {
        return invitados;
    }

    public void setInvitados(String invitados) {
        this.invitados = invitados;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
}
