package ammovil.com.excelsior.data.response;

public class TiposMembresiaResponseDto {
    public Double Id = 0.0;
    public String Descripcion = "";
    public Double Fee = 0.0;
    public Double Rendimiento = 0.0;
    public Double RangoInicial = 0.0;
    public Double RangoFinal = 0.0;
    public Double IdEstado = 0.0;


    public TiposMembresiaResponseDto() {
    }


    public TiposMembresiaResponseDto(Double id, String descripcion, Double fee, Double rendimiento, Double rangoInicial, Double rangoFinal, Double idEstado) {
        Id = id;
        Descripcion = descripcion;
        Fee = fee;
        Rendimiento = rendimiento;
        RangoInicial = rangoInicial;
        RangoFinal = rangoFinal;
        IdEstado = idEstado;
    }

    public Double getId() {
        return Id;
    }

    public void setId(Double id) {
        Id = id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Double getFee() {
        return Fee;
    }

    public void setFee(Double fee) {
        Fee = fee;
    }

    public Double getRendimiento() {
        return Rendimiento;
    }

    public void setRendimiento(Double rendimiento) {
        Rendimiento = rendimiento;
    }

    public Double getRangoInicial() {
        return RangoInicial;
    }

    public void setRangoInicial(Double rangoInicial) {
        RangoInicial = rangoInicial;
    }

    public Double getRangoFinal() {
        return RangoFinal;
    }

    public void setRangoFinal(Double rangoFinal) {
        RangoFinal = rangoFinal;
    }

    public Double getIdEstado() {
        return IdEstado;
    }

    public void setIdEstado(Double idEstado) {
        IdEstado = idEstado;
    }

    @Override
    public String toString() {
        return "TiposMembresiaResponseDto{" +
                "Id=" + Id +
                ", Descripcion='" + Descripcion + '\'' +
                ", Fee=" + Fee +
                ", Rendimiento=" + Rendimiento +
                ", RangoInicial=" + RangoInicial +
                ", RangoFinal=" + RangoFinal +
                ", IdEstado=" + IdEstado +
                '}';
    }
}
