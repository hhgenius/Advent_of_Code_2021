/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day5;

import Day4.Day4;
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
public class Day5 {

    public static void main(String[] args) throws IOException {
        URL path = Day5.class.getResource("input");
        File input = new File(path.getFile());
        ArrayList<String> lineas = lecturaFichero(input);
        int[] dimensions = dimensions(lineas);
        int[][] matrix = new int[dimensions[0] + 1][dimensions[1] + 1];
        //int matrix[][] = new int[1000][1000];
        //matrix = hydroVents(lineas, matrix);
        matrix = hydroVentsWithDiagonal(lineas, matrix);
        int overlapping = countOverlapping(matrix);
        System.out.println("Hay " + overlapping + " lineas que se solapan");
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

    private static int[] dimensions(ArrayList<String> coordenadas){
        int[] dimensions = new int[2];
        for(String linea : coordenadas){
            String[] coordenada = linea.split(" -> ");
            String x1 = coordenada[0].split(",")[1];
            String y1 = coordenada[0].split(",")[0];
            String x2 = coordenada[1].split(",")[1];
            String y2 = coordenada[1].split(",")[0];
            
            int maxX = Math.max(Integer.parseInt(x1), Integer.parseInt(x2));
            int maxY = Math.max(Integer.parseInt(y1), Integer.parseInt(y2));
            
            if(dimensions[0] < maxX ) dimensions[0] = maxX;
            if(dimensions[1] < maxY ) dimensions[1] = maxY;            
        }
        return dimensions;
    }
    
    private static int[][] hydroVents(ArrayList<String> coordenadas, int[][] matrix){
        for(String linea : coordenadas){
            String[] coordenada = linea.split(" -> ");
            int x1 = Integer.parseInt(coordenada[0].split(",")[1]);
            int y1 = Integer.parseInt(coordenada[0].split(",")[0]);
            int x2 = Integer.parseInt(coordenada[1].split(",")[1]);
            int y2 = Integer.parseInt(coordenada[1].split(",")[0]);
            
            if(x1 == x2){
                int maxY = Math.max(y1, y2);
                int minY = Math.min(y1, y2);
                for(int i = minY; i <= maxY; i++){
                    matrix[x1][i] += 1;
                }
                
            } else if(y1 == y2){
                int maxX = Math.max(x1, x2);
                int minX = Math.min(x1, x2);
                for(int i = minX; i <= maxX; i++){
                    matrix[i][y1] += 1;
                }
            }
        }
        return matrix;
    }
    
    private static int[][] hydroVentsWithDiagonal(ArrayList<String> coordenadas, int[][] matrix){
        for(String linea : coordenadas){
            String[] coordenada = linea.split(" -> ");
            int x1 = Integer.parseInt(coordenada[0].split(",")[1]);
            int y1 = Integer.parseInt(coordenada[0].split(",")[0]);
            int x2 = Integer.parseInt(coordenada[1].split(",")[1]);
            int y2 = Integer.parseInt(coordenada[1].split(",")[0]);
            
            if(x1 == x2){
                int maxY = Math.max(y1, y2);
                int minY = Math.min(y1, y2);
                for(int i = minY; i <= maxY; i++){
                    matrix[x1][i] += 1;
                }
                
            } else if(y1 == y2){
                int maxX = Math.max(x1, x2);
                int minX = Math.min(x1, x2);
                for(int i = minX; i <= maxX; i++){
                    matrix[i][y1] += 1;
                }
            } else {
                if(x1 < x2 && y1 < y2){
                    for(int i = x1, j = y1; i <= x2 && j <= y2; i++, j++){
                        matrix[i][j] += 1;
                    }
                }
                else if(x1 < x2 && y1 > y2){
                    for(int i = x1, j = y1; i <= x2 && j >= y2; i++, j--){
                        matrix[i][j] += 1;
                    }
                }
                else if(x1 > x2 && y1 < y2){
                    for(int i = x1, j = y1; i >= x2 && j <= y2; i--, j++){
                        matrix[i][j] += 1;
                    }
                }
                else if(x1 > x2 && y1 > y2){
                    for(int i = x1, j = y1; i >= x2 && j >= y2; i--, j--){
                        matrix[i][j] += 1;
                    }
                }
            }
        }
        return matrix;
    }

    private static int countOverlapping(int[][] matrix) {
        int resultado = 0;
        for(int i = 0; i<matrix.length; i++){
            for(int j = 0; j<matrix[i].length; j++){
                System.out.print(matrix[i][j] + " ");
                if(matrix[i][j] >= 2){
                    resultado += 1;
                }
            }
            System.out.println("");
        }
        return resultado;
    }
}
