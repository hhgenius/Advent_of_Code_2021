/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author Héctor
 */
public class Day3 {
    
    public static void main(String[] args) throws IOException{
        URL path = Day3.class.getResource("input");
        File input = new File(path.getFile());
        ArrayList<String> lineas = lecturaFichero(input);
        String gamma = gammaRate(lineas);
        int gammaDecimal = Integer.parseInt(gamma, 2);
        String epsilon = gammaToEpsilon(gamma);
        int epsilonDecimal = Integer.parseInt(epsilon, 2);
        int resultado = gammaDecimal * epsilonDecimal;
        System.out.println("La consumicion del submarino es de: " + resultado);
        //PART2
    }
        
    private static ArrayList lecturaFichero(File input) throws IOException{
        ArrayList<String> resultado = new ArrayList<>();
        String linea;
        try {
            FileReader fr = new FileReader(input);
            BufferedReader br = new BufferedReader (fr);
            while((linea = br.readLine()) != null){
                resultado.add(linea);
            }
            return resultado;
            
        }catch(FileNotFoundException e){
            System.err.println(e);
        }
        return resultado;
    }
    
    /**
     * Calcula el ratio gamma a una entrada binaria de un Arraylist
     * @param lineas
     * @return un array donde cada campo si es positivo es 1 o 0.
     */
    private static String gammaRate(ArrayList<String> lineas){
        int[] valores = new int[lineas.get(0).length()];
        for(String linea : lineas){
            for(int i = 0; i < linea.length(); i++){
                char valor = linea.charAt(i);
                if( valor == '1'){
                    valores[i] += 1;
                } else {
                    valores[i] -= 1;
                }
            }
        }
        //funcion que parsea los valores a 1s y 0s
        String resultado = parseGammaRate(valores);
        return resultado;
    }
    
    private static String parseGammaRate(int[] valores){
        String resultado = "";
        for(int i = 0; i < valores.length; i++){
            if(valores[i] < 0){
                resultado = resultado + "1";
            } else {
                resultado = resultado + "0";
            }
        }
        return resultado;
    }
    
    private static String gammaToEpsilon(String gamma){
        String epsilon = "";
        for(int i = 0; i<gamma.length(); i++){
            if(gamma.charAt(i)=='1'){
                epsilon = epsilon + '0';
            } else {
                epsilon = epsilon + '1';
            }
        }
        return epsilon;
    }
    
}
