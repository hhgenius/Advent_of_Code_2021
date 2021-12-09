/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day4;

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
public class Day4 {
    
    public static void main(String[] args) throws IOException{
       URL path = Day4.class.getResource("input");
       File input = new File(path.getFile());
       ArrayList<String> lineas = lecturaFichero(input);
       String[] nCantar = lineas.get(0).split(",");
       lineas.remove(0);
       lineas.remove(0);
       ArrayList<String[][]> cartones = creaCartones(lineas);
       //System.out.println(comprobarCarton(nCantar, cartones));
       System.out.println(comprobarCarton2(nCantar, cartones));  
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
    
    private static ArrayList<String[][]> creaCartones(ArrayList<String> lineaCartones){
        ArrayList<String[][]> cartones = new ArrayList<>();
        String[][] carton = new String[5][5];
        int contador = 0;
        for(String linea : lineaCartones){
            if(!linea.equals("")){
                String[] valores = Arrays.stream(linea.split(" ")).filter(e -> e.trim().length() > 0).toArray(String[]::new);
                carton[contador] = valores;
                contador++;
            } else {
                contador = 0;
                cartones.add(carton);
                carton = new String[5][5];
            }
        }
        cartones.add(carton);
        return cartones;
    }
    
    private static ArrayList<String[][]> copyArray(ArrayList<String[][]> cartones){
        ArrayList<String[][]> copyArray = new ArrayList<>();
        for(int i = 0; i < cartones.size(); i++){
            String[][] cartonCopy = new String[5][5];
            for(int j = 0; j < cartonCopy.length; j++){
                for(int k = 0; k < cartonCopy.length; k++){
                cartonCopy[j][k]="*";
                }
            }
            copyArray.add(cartonCopy);
        }
        return copyArray;
    }
    
    private static String comprobarCarton(String[] nCantar, ArrayList<String[][]> cartones){
        ArrayList<String[][]> copyCartones = copyArray(cartones);
        for(String numero : nCantar){
            for(int i = 0; i < cartones.size(); i++){ //carton
                String[][] carton = cartones.get(i);
                String[][] copyCarton = copyCartones.get(i);
                for(int j = 0; j < carton.length; j++){ //fila
                    for(int k = 0; k < carton.length; k++){ //columna
                        if(carton[j][k].equals(numero)){
                            copyCarton[j][k] = numero;
                            carton[j][k] = "*";
                            if(compruebaGanador(copyCarton)){
                                return "El resultado es: " + finalScore(numero, carton);
                            }
                        }
                    }
                }
            }
        }
        return "No hay ganadores";
    }
    
    private static boolean compruebaGanador(String[][] cartonMarcado){
        int contador = 0;
        
        for(int i = 0; i < cartonMarcado.length; i++){
            for(int j = 0; j < cartonMarcado.length; j++){
                if(!cartonMarcado[i][j].equals("*")) contador++;
            }
            if(contador == cartonMarcado.length){
                return true;
            } else {
                contador = 0;
            }
        }
        
        for(int j = 0; j < cartonMarcado.length; j++){
            for(int i = 0; i < cartonMarcado.length; i++){
                if(!cartonMarcado[i][j].equals("*")) contador++;
            }
            if(contador == cartonMarcado.length){
                return true;
            } else {
                contador = 0;
            }
        }
        return false;
    }
    
    private static int finalScore(String numero, String[][] carton){
        int suma = 0;
        for(int i = 0; i < carton.length; i++){
            for(int j = 0; j < carton.length; j++){
                if(!carton[i][j].equals("*")) suma = suma + Integer.parseInt(carton[i][j]);
            }
        }
        return suma * Integer.parseInt(numero);
    }
    
    private static String comprobarCarton2(String[] nCantar, ArrayList<String[][]> cartones){
        ArrayList<String[][]> copyCartones = copyArray(cartones);
        ArrayList<String[][]> cartonesGanadores = new ArrayList<>();
        for(String numero : nCantar){
            for(int i = 0; i < cartones.size(); i++){ //carton
                String[][] carton = cartones.get(i);
                String[][] copyCarton = copyCartones.get(i);
                if(!cartonesGanadores.contains(carton)){
                   for(int j = 0; j < carton.length; j++){ //fila
                        for(int k = 0; k < carton.length; k++){ //columna
                            if(carton[j][k].equals(numero)){
                                copyCarton[j][k] = numero;
                                carton[j][k] = "*";
                                if(compruebaGanador(copyCarton)){
                                    cartonesGanadores.add(carton);
                                    if(cartonesGanadores.size() == cartones.size())
                                    return "El resultado es: " + finalScore(numero, carton);
                                }
                            }
                        }
                    } 
                }
            }
        }
        return "No hay ganadores";
    }
}
