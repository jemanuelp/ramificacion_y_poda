package dominio;

public class ElementoPatron {
    private Integer cantidad;
    private Double sumaMedida;

    public Integer getCantidad() {
        return cantidad;
    }

    public ElementoPatron setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
        return this;
    }

    public Double getSumaMedida() {
        return sumaMedida;
    }

    public ElementoPatron setSumaMedida(Double sumaMedida) {
        this.sumaMedida = sumaMedida;
        return this;
    }
}
