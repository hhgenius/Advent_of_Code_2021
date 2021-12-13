/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Héctor
 */
public class Day7 {
    
    public static void main(String[] args) throws IOException {
        URL path = Day7.class.getResource("input");
        File input = new File(path.getFile());
        ArrayList<String> lineas = lecturaFichero(input);
        String[] crabsPosition = lineas.get(0).split(",");
        int[] posiciones = stringToInt(crabsPosition);
        //System.out.println("media :" + media(posiciones));
        //int mediana = mediana(posiciones);
        //int resultado = lessFuel(posiciones, mediana);
        //System.out.println("El menor coste de gasolina es de: " + resultado);
        int resultado = lessFuelFuerzaBruta(posiciones);
        System.out.println("El menor coste de gasolina es de: " + resultado);
    }
    
    private static ArrayList lecturaFichero(File input) throws IOException {
        ArrayList<String> resultado = new ArrayList<>();
        String linea;
        try {
            FileReader fr = new FileReader(input);
            BufferedReader br = new BufferedReader(fr);
            while ((linea = br.readLine()) != null) {
                resultado.add(linea);
            }
            return resultado;

        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
        return resultado;
    }
    
    private static int[] stringToInt(String[] array){
        int[] arrayInt = new int[array.length];
        for(int i = 0; i < array.length; i++){
            arrayInt[i] = Integer.parseInt(array[i]);
        }
        return arrayInt;
    }

    private static int mediana(int[] posiciones) {
        Arrays.sort(posiciones);
        int mediana = 0;
        int mitad = posiciones.length/2;
        if(posiciones.length % 2 == 0){
            mediana = (posiciones[mitad - 1] + posiciones[mitad]) / 2;
        } else {
            mediana = posiciones[mitad];
        }
        return mediana;
    }

    private static int lessFuel(int[] crabsPosition, int mediana) {
        int resultado = 0;
        for(int posicion : crabsPosition){
            if(posicion < mediana){
                resultado = resultado  + (mediana - posicion);
            } else {
                resultado = resultado  + (posicion - mediana);
            }
        }
        return resultado;
    }
    
    private static int lessFuelFuerzaBruta(int[] crabsPosition) {
        int resultado = 0;
        
        Arrays.sort(crabsPosition);
        int min = crabsPosition[0];
        int max = crabsPosition[crabsPosition.length - 1];
        for (int i = min; i <= max; i++) {
            int posibleResultado = 0;
            for (int posicion : crabsPosition) {
                if (posicion < i) {
                    for(int j = 1; j <= i - posicion; j++)
                        posibleResultado = posibleResultado + j;
                } else {
                     for(int j = 1; j <= posicion - i; j++)
                        posibleResultado = posibleResultado + j;
                }
            }
            if(resultado == 0) resultado = posibleResultado;
            if(posibleResultado < resultado) resultado = posibleResultado;
        }

        return resultado;
    }

    private static int media(int[] posiciones){
        int resultado = 0;
        for(int posicion : posiciones) resultado += posicion;
        return resultado / posiciones.length;
    }
    
}
