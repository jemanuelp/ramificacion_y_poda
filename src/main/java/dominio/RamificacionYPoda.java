package dominio;

import dominio.ElementoPatron;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

public class RamificacionYPoda {
    private ArrayList<Double> cortes = new ArrayList<>();

    public void podar(int L, double[] li) {
        for (double d : li) {
            cortes.add(d);
        }

        double menor = Arrays.stream(li).
                filter(i -> i != 0)
                .min()
                .orElse(0);

        System.out.println("MENOR: " + menor);

        ArrayList<ArrayList<ElementoPatron>> esquemas = new ArrayList<>();
        for (Double d : cortes) {
            ArrayList<ElementoPatron> esquemaTmp = new ArrayList<>();
            for (int i = 0; i <= L; i++) {
                ElementoPatron esquemaUnElemento = new ElementoPatron();
                if ((d * i) < L) {
                    esquemaUnElemento.setCantidad(i).setSumaMedida(d * i);
                } else {
                    break;
                }
                esquemaTmp.add(esquemaUnElemento);
            }
            esquemas.add(esquemaTmp);
        }

//        ramificar(esquemas, L, 0d, 0);
//        System.out.println("adfa");
    }

    public void ramificar(ArrayList<ArrayList<ElementoPatron>> esquemas, Double L, Double suma,ArrayList<ElementoPatron> esquema, double[] li,double menor, int index) {

        if (esquema.size() < li.length){
            for (int i=0; i<L ; i++){
                suma += (double)i*li[index];
                ElementoPatron elementoPatron = new ElementoPatron(0,(double)0,li[index]);
                if (suma<=L){
                    elementoPatron = new ElementoPatron(i,(double)i*li[index],li[index]);
                    esquema.add(elementoPatron);
                    ramificar(esquemas,L,suma,esquema,li,menor,index+1);
                    int indexOf = esquema.indexOf(elementoPatron);
                    esquema.remove(indexOf);
                }
                suma -= (double)i*li[index];
            }
        } else{
            if (L - suma + 0.01 < menor){
                esquemas.add(esquema);
                System.out.println(esquema);
                DecimalFormat df = new DecimalFormat("#.000");
                System.out.println(df.format(L - ElementoPatron.sumaTotal(esquema)));
                System.out.println(df.format(suma));
                System.out.println("Totales = "+esquemas.size());
            }
        }
    }

    private boolean exists(double li, ArrayList<ElementoPatron> esquema) {
        Optional<ElementoPatron> search = esquema.stream()
                .filter(e -> e.getMedida().equals(li))
                .findFirst();

        return search.isPresent();
    }

    public void combinaciones(int numero, ArrayList<Integer> numeros,int suma){
        if (suma == numero){
            System.out.println(numeros);
        }else{
            for (int i=1; i<=numero;i++){
                suma += i;
                if (suma<=numero){
                    numeros.add(i);
                    combinaciones(numero,numeros,suma);
                    numeros.remove(numeros.indexOf(i));
                }
                suma -= i;
            }
        }
    }
}