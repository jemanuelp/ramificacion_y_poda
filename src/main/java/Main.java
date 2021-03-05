import dominio.ElementoPatron;
import dominio.RamificacionYPoda;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        RamificacionYPoda ramificacionYPoda = new RamificacionYPoda();

        int[] li = new int[]{440,460,480};
        String[] header = new String[]{"440","460","480"};
        int menor = Arrays.stream(li)
                .filter(i -> i != 0)
                .min()
                .orElse(0);
//        ramificacionYPoda.podar(147,);
//        int numero = 4;
//        ArrayList<Integer> numeros = new ArrayList<>();
//        ramificacionYPoda.combinaciones(numero,numeros,0);
        ramificacionYPoda.createExcel(header);
        for (int i=0;i<li.length;i++){
            ramificacionYPoda.ramificar(14520,0, new ArrayList<>(),li,menor,0,i);
        }
        ramificacionYPoda.closeExcel();
    }
}
