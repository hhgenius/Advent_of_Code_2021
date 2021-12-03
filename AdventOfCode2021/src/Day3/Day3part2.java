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
public class Day3part2 {
    public static void main(String[] args) throws IOException{
       URL path = Day3.class.getResource("input");
       File input = new File(path.getFile());
       ArrayList<String> lineas = lecturaFichero(input);
       String OGR = lifeSupportRating(lineas, 0, 1);
       String CO2 = lifeSupportRating(lineas, 0, 0);
       int resultado = Integer.parseInt(OGR,2) * Integer.parseInt(CO2, 2);
       System.out.println("El ratio de soporte vital es de: " + resultado);
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
    
    private static String lifeSupportRating(ArrayList<String> numbers, int position, int bit){
        String resultado = "";
        if(numbers.size() == 1){
            return numbers.get(0);
        } else {
            //Si el bit es 1, se busca oxigeno, y te quedas con el mas común y viceversa.
            //Si comun es positivo, es mas comun los 1 y viceversa.
            int common = 0;
            ArrayList<String> ones = new ArrayList<>();
            ArrayList<String> zeroes = new ArrayList<>();
            for(String number : numbers){
                if(number.charAt(position) == '1'){
                    common += 1;
                    ones.add(number);
                } else {
                    common -= 1;
                    zeroes.add(number);
                }
            }
            if(bit == 1){
                if(common >= 0){
                    resultado = lifeSupportRating(ones, position + 1, bit);
                } else {
                    resultado = lifeSupportRating(zeroes, position + 1, bit);
                }
            } else {
                if(common >= 0){
                    resultado = lifeSupportRating(zeroes, position + 1, bit);
                } else {
                    resultado = lifeSupportRating(ones, position + 1, bit);
                }
            }
        }
        return resultado;
    }
}
