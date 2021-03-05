package dominio;

import dominio.ElementoPatron;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

public class RamificacionYPoda {
    private ArrayList<Integer> cortes = new ArrayList<>();

    public void podar(int L, int[] li) {
        for (Integer d : li) {
            cortes.add(d);
        }

        int menor = Arrays.stream(li).
                filter(i -> i != 0)
                .min()
                .orElse(0);

        System.out.println("MENOR: " + menor);

        ArrayList<ArrayList<ElementoPatron>> esquemas = new ArrayList<>();
        for (Integer d : cortes) {
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

    private ArrayList<ArrayList<ElementoPatron>> esquemas = new ArrayList<>();
    public void ramificar(int L, int suma,ArrayList<ElementoPatron> esquema, int[] li,int menor, int index,int pivot) {
        if (esquema.size() < li.length){
            for (int i=0; i<L ; i++){
                if((L-suma) > (24*li[index])){
                    if (pivot == index) {
                        if (i > 0) {
                            i += 24;
                        }
                        if (i == 0) {
                            i = 24;
                        }
                    }
                }
                suma += (double)i*li[index];
                ElementoPatron elementoPatron = new ElementoPatron(0,0,li[index]);
                if (suma<=L){
                    elementoPatron = new ElementoPatron(i,i*li[index],li[index]);
                    esquema.add(elementoPatron);
                    ramificar(L,suma,esquema,li,menor,index+1,pivot);
                    int indexOf = esquema.indexOf(elementoPatron);
                    esquema.remove(indexOf);
                }
                suma -= (double)i*li[index];
            }
        } else{
            if (L - suma + 0.01 < menor){
//              DecimalFormat df = new DecimalFormat("#");
                if (!existsEsquema(esquemas,esquema)){
                    esquemas.add((ArrayList<ElementoPatron>)esquema.clone());
                    System.out.println(esquema);
                    System.out.println(L - ElementoPatron.sumaTotal(esquema));
                    System.out.println(suma);
                    System.out.println("Totales = "+esquemas.size());
                    try {
                        writeExcel(esquema,esquemas.size());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private CellStyle style;

    public void createExcel(String[] headers){
        this.workbook = new HSSFWorkbook();
        this.sheet = workbook.createSheet();
        workbook.setSheetName(0, "Hoja excel");

        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; ++i) {
            String header = headers[i];
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }
    }
    public void writeExcel(ArrayList<ElementoPatron> esquema,int total) throws Exception {
        HSSFRow dataRow = sheet.createRow(total+1);

        for (int i = 0; i<esquema.size();i++){
            dataRow.createCell(i).setCellValue( ((ElementoPatron)esquema.get(i)).getCantidad());
        }

//        HSSFCell total1 = dataRow.createCell(1);
//        total1.setCellType(CellType.FORMULA);
//        total1.setCellStyle(style);
//        total1.setCellFormula(String.format("SUM(B2:B%d)", 1 + esquema.size()));
    }

    public void closeExcel(){
        FileOutputStream file = null;
        try {
            file = new FileOutputStream("data.xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            workbook.write(file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean existsEsquema(ArrayList<ArrayList<ElementoPatron>> esquemas, ArrayList<ElementoPatron> esquema) {
        boolean b = false;
        for (ArrayList<ElementoPatron> esq: esquemas){
            if(compararSync(esq,esquema)){
                b=true;
                break;
            }
        }
        return b;
    }

    public boolean compararSync(ArrayList<ElementoPatron> esq, ArrayList<ElementoPatron> esquema){
        int m = 0;
        int n = 0;
        for (int i=0;i<esq.size();i++){
            m++;
            if (
                    esq.get(i).getMedida().equals(esquema.get(i).getMedida()) &&
                    esq.get(i).getCantidad().equals(esquema.get(i).getCantidad()) &&
                    esq.get(i).getSumaMedida().equals(esquema.get(i).getSumaMedida())
            ){
                n++;
            }
        }

        return (m == n);
    }

    public boolean exists(int li, ArrayList<ElementoPatron> esquema) {
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