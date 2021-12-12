/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day6;

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
public class Day6 {
    
    public static void main(String[] args) throws IOException {
        URL path = Day6.class.getResource("input");
        File input = new File(path.getFile());
        ArrayList<String> lineas = lecturaFichero(input);
        ArrayList<Integer> peces = creaPeces(lineas);
        peces = reproduccionPeces(peces, 256);
        System.out.println("El numero de peces total es de: " + peces.size());
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
    
    private static ArrayList<Integer> creaPeces(ArrayList<String> lineas){
        String[] peces = lineas.get(0).split(",");
        ArrayList<Integer> nPeces = new ArrayList<>();
        for(String pez : peces){
            nPeces.add(Integer.parseInt(pez));
        }
        return nPeces;
    }

    private static ArrayList<Integer> reproduccionPeces(ArrayList<Integer> peces, int dias) {
        for (int d = 0; d < dias; d++) {
            ArrayList<Integer> copiaPeces = new ArrayList<>(peces);
            for (int i = 0; i < copiaPeces.size(); i++) {
                int valorPez = copiaPeces.get(i);
                if (valorPez == 0) {
                    peces.set(i, 6);
                    peces.add(8);
                } else {
                    peces.set(i, valorPez - 1);
                }
            }
        }

        return peces;
    }
}
