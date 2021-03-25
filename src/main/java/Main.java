import dominio.ElementoPatron;
import dominio.RamificacionYPoda;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        RamificacionYPoda ramificacionYPoda = new RamificacionYPoda();

        int[] li = new int[]{600,620};
        String[] header = new String[li.length];
        for (int i=0;i<li.length;i++) {
            header[i] = String.valueOf(li[i]);
        }

        ramificacionYPoda.createExcel(header);
        int medidaBanco = 16560;
        if (li.length>2) {
            int menor = 300;
            for (int i = 0; i < li.length; i++) {
                ramificacionYPoda.ramificarConPivot(medidaBanco, 0, new ArrayList<>(), li, menor, 0, i);
            }
        }else{
            int menor = Arrays.stream(li)
                    .filter(i -> i != 0)
                    .min()
                    .orElse(0);
            ramificacionYPoda.ramificar(medidaBanco, 0, new ArrayList<>(), li, menor, 0);
        }
        ramificacionYPoda.closeExcel();
    }
}
