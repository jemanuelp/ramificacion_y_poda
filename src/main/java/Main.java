import dominio.ElementoPatron;
import dominio.RamificacionYPoda;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        RamificacionYPoda ramificacionYPoda = new RamificacionYPoda();

        double[] li = new double[]{1,1.2f,1.4f,1.6f,1.8f,2,2.2f,2.4f,2.6f,2.8f,3};
        double menor = Arrays.stream(li).
                filter(i -> i != 0)
                .min()
                .orElse(0);
//        ramificacionYPoda.podar(147,);
//        int numero = 4;
//        ArrayList<Integer> numeros = new ArrayList<>();
//        ramificacionYPoda.combinaciones(numero,numeros,0);
        ArrayList<ArrayList<ElementoPatron>> esquemas = new ArrayList<>();
        ramificacionYPoda.ramificar(esquemas, 147.2,(double)0, new ArrayList<>(),li,menor,0);
        System.out.println(esquemas);
    }
}
