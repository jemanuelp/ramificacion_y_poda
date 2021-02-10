import dominio.ElementoPatron;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RamificacionYPoda {
    private ArrayList<Double> cortes = new ArrayList<>();

    public void podar(int L,double[] li) {
        for (double d: li) {
            cortes.add(d);
        }

        double menor = Arrays.stream(li).
                filter(i -> i != 0)
                .min()
                .orElse(0);

        System.out.println("MENOR: "+menor);

        ArrayList<ElementoPatron> esquemas = new ArrayList<>();
        for (Double d: cortes) {
            ElementoPatron esquemaUnElemento = new ElementoPatron();
            for (int i=0;i<=L;i++){
                if ((d * i) < L){
                    esquemaUnElemento.setCantidad(i).setSumaMedida(d*i);
                }else{
                    break;
                }
            }
            esquemas.add(esquemaUnElemento);
        }

        ramificar(esquemas,L,0d);
        System.out.println("adfa");
    }

    private void ramificar(ArrayList<ElementoPatron> esquemas, int L,Double suma) {
        for (ElementoPatron pivot:esquemas) {
            esquemas.remove(0);
            System.out.println(pivot.getCantidad()+"-"+pivot.getSumaMedida()+suma);
            ramificar(esquemas,L,pivot.getSumaMedida() + suma);
        }
    }
}