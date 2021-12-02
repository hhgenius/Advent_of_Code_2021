/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day2;

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
public class Day2 {
    
    public static void main(String[] args) throws IOException{
        URL path = Day2.class.getResource("input");
        File input = new File(path.getFile());
        ArrayList<String> lineas = lecturaFichero(input);
        //PARTE 1
        int valores[]; //valores[0] = horizontal, valores[1] = depth
        valores = submarineMoves(lineas);
        System.out.println("La multiplicacion del valor horizontal por la profundidad es: " + valores[0] * valores[1]);
        //PARTE 2
        valores = submarineMoves2(lineas);
        System.out.println("La multiplicacion del valor horizontal por la profundidad es para la parte 2: " + valores[0] * valores[1]);
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
    
      private static int[] submarineMoves(ArrayList<String> lineas) {
        int horizontal = 0;
        int depth = 0;
        int[] valores = new int[2];
        for(String linea : lineas){
            String[] split = linea.split(" ");
            switch(split[0]){
                case "forward":
                    horizontal += Integer.parseInt(split[1]);
                    break;
                case "down":
                    depth += Integer.parseInt(split[1]);
                    break;
                case "up":
                    depth -= Integer.parseInt(split[1]);
                    break;
                default:
                    System.err.println("Algo ha fallado");
            }
        }
        valores[0] = horizontal;
        valores[1] = depth;
        return valores;
    }
      
    private static int[] submarineMoves2(ArrayList<String> lineas) {
      int horizontal = 0;
      int depth = 0;
      int aim = 0;
      int[] valores = new int[2];
      for(String linea : lineas){
          String[] split = linea.split(" ");
          switch(split[0]){
              case "forward":
                  horizontal += Integer.parseInt(split[1]);
                  depth = depth + aim * Integer.parseInt(split[1]);
                  break;
              case "down":
                  aim += Integer.parseInt(split[1]);
                  break;
              case "up":
                  aim -= Integer.parseInt(split[1]);
                  break;
              default:
                  System.err.println("Algo ha fallado");
          }
      }
      valores[0] = horizontal;
      valores[1] = depth;
      return valores;
    }
}
