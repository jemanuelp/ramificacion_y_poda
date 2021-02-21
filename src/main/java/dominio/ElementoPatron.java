package dominio;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElementoPatron {
    private Integer cantidad;
    private Double sumaMedida;
    private Double medida;
    DecimalFormat df = new DecimalFormat("#.00");

    public ElementoPatron() {
    }

    public ElementoPatron(Integer cantidad, Double sumaMedida, Double medida) {
        this.cantidad = cantidad;
        this.sumaMedida = sumaMedida;
        this.medida = medida;
    }

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

    public Double getMedida() {
        return medida;
    }

    @Override
    public String toString() {
        return
//                "cantidad=" +
                cantidad +
                        "-" +
//                ", sumaMedida=" + sumaMedida +
//                ", medida=" +
                df.format(medida) + " : ";
    }

    public static Double sumaTotal(ArrayList<ElementoPatron> elementos){
        Double total = 0.0;
        for (ElementoPatron elemento:elementos) {
            total += elemento.getSumaMedida();
        }
        return total;
    }
}
