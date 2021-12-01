/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day1;

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
public class Day1 {
    public static void main(String[] args) throws IOException{
        URL path = Day1.class.getResource("input");
        File input = new File(path.getFile());
        ArrayList<String> lineas = new ArrayList<>();
        lineas = lecturaFichero(input);
        //PARTE 1
        int incremento = measureIncrease(lineas);
        System.out.println("El aumento es de: " + incremento);
        //PARTE2
        incremento = measureIncrease2(lineas);
        System.out.println("El aumento en tripletes es de: " + incremento);
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
    
    private static int measureIncrease(ArrayList<String> lineas){
        int numberOfIncrease = 0;
        for(int i = 0; i<lineas.size() - 1; i++){
            if(Integer.parseInt(lineas.get(i)) < Integer.parseInt(lineas.get(i + 1))){
                numberOfIncrease++;
            }
        }
        return numberOfIncrease;
    }
    
    private static int measureIncrease2(ArrayList<String> lineas){
        int numberOfIncrease = 0;
        int a,b,c,d = 0;
        for(int i = 0; i<lineas.size() - 3; i++){
            a = Integer.parseInt(lineas.get(i));
            b = Integer.parseInt(lineas.get(i+1));
            c = Integer.parseInt(lineas.get(i+2));
            d = Integer.parseInt(lineas.get(i+3));
            if((a+b+c) < (b+c+d)){
                numberOfIncrease++;
            }
        }
        return numberOfIncrease;
    }
}
