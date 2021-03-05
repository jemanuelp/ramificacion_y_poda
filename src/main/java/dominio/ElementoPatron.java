package dominio;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElementoPatron {
    private Integer cantidad;
    private Integer sumaMedida;
    private Integer medida;
//    DecimalFormat df = new DecimalFormat("#.00");

    public ElementoPatron() {
    }

    public ElementoPatron(Integer cantidad, Integer sumaMedida, Integer medida) {
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

    public Integer getSumaMedida() {
        return sumaMedida;
    }

    public ElementoPatron setSumaMedida(Integer sumaMedida) {
        this.sumaMedida = sumaMedida;
        return this;
    }

    public Integer getMedida() {
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
                        medida + " : ";
    }

    public static Integer sumaTotal(ArrayList<ElementoPatron> elementos){
        Integer total = 0;
        for (ElementoPatron elemento:elementos) {
            total += elemento.getSumaMedida();
        }
        return total;
    }
}
