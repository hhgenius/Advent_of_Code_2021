/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day8;

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
public class Day8 {

    public static void main(String[] args) throws IOException {
        URL path = Day8.class.getResource("input");
        File input = new File(path.getFile());
        ArrayList<String> lineas = lecturaFichero(input);
        String[] outputs = getOutputs(lineas);
        int resultado = cuentaValores(outputs);
        System.out.println("Los 1, 4, 7 y 8 aparecen " + resultado + " veces");
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

    private static String[] getOutputs(ArrayList<String> lineas) {
        String[] outputs = new String[lineas.size()];
        for(int i = 0; i < lineas.size(); i++){
            outputs[i] = lineas.get(i).split("\\|")[1];
        }
        return outputs;
    }

    private static int cuentaValores(String[] outputs) {
        int resultado = 0;
        for(String output : outputs){
            String[] numeros = output.split(" ");
            for(String numero : numeros){
                if(numero.length() == 2 || numero.length() == 3 || numero.length() == 4 || numero.length() == 7){
                    resultado += 1;
                }
            }
        }
        return resultado;
    }
}
