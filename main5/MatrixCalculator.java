package main5;

import java.util.Scanner;

public class MatrixCalculator {
    public static int[][] multiplicar(int[][] matriz1, int[][] matriz2, char operador) {
        int filas = matriz1.length;
        int columnas = matriz2[0].length;
        int comun = matriz2.length;
        int[][] resultado = new int[filas][columnas];
        Thread[][] hilos = new Thread[filas][columnas];
       
      
        // Iniciamos los hilos para calcular los elementos de la matriz resultante //
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                hilos[i][j] = new MatrixThread(resultado, matriz1, matriz2, i, j, comun, operador);
                hilos[i][j].start();
            }
        }

        // Esperamos a que todos los hilos terminen su ejecución //
        try {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    hilos[i][j].join();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public static int[][] sumar(int[][] matriz1, int[][] matriz2) {
        int filas = matriz1.length;
        int columnas = matriz1[0].length;
        int[][] resultado = new int[filas][columnas];

        // Sumamos los elementos correspondientes de las matrices //
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                resultado[i][j] = matriz1[i][j] + matriz2[i][j];
            }
        }

        return resultado;
    }

    public static int[][] restar(int[][] matriz1, int[][] matriz2) {
        int filas = matriz1.length;
        int columnas = matriz1[0].length;
        int[][] resultado = new int[filas][columnas];

        // Restamos los elementos correspondientes de las matrices //
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                resultado[i][j] = matriz1[i][j] - matriz2[i][j];
            }
        }

        return resultado;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Para ingresar los numeros del tamaño de la matriz //
        System.out.println("Ingrese las dimensiones de la primera matriz:");
        System.out.print("Ancho: ");
        int ancho1 = scanner.nextInt();
        System.out.print("Altura: ");
        int altura1 = scanner.nextInt();

        System.out.println("Ingrese las dimensiones de la segunda matriz:");
        System.out.print("Ancho: ");
        int ancho2 = scanner.nextInt();
        System.out.print("Altura: ");
        int altura2 = scanner.nextInt();

        System.out.println("Qué tipo de operación quieres hacer: (+, -, *)");
        char operador = scanner.next().charAt(0);

        // Verificamos que las dimensiones de las matrices sean válidas //
        if (ancho1 < 1 || ancho1 > 20 || altura1 < 1 || altura1 > 20 ||
            ancho2 < 1 || ancho2 > 20 || altura2 < 1 || altura2 > 20) {
            System.out.println("Los tamaños de las matrices deben estar acotados entre 1 y 20.");
            return;
        }

        if (ancho1 != altura2) {
            System.out.println("El número de columnas de la primera matriz debe ser igual al número de filas de la segunda matriz.");
            return;
        }

        int[][] matriz1 = new int[altura1][ancho1];
        int[][] matriz2 = new int[altura2][ancho2];

        // Pedimos los numeros de la primera matriz //
        System.out.println("Ingrese los elementos de la primera matriz:");
        for (int i = 0; i < altura1; i++) {
            System.out.println("Fila " + (i + 1) + ":");
            for (int j = 0; j < ancho1; j++) {
                matriz1[i][j] = scanner.nextInt();
            }
        }

        // Pedimos los numeros de la segunda matriz //
        System.out.println("Ingrese los elementos de la segunda matriz:");
        for (int i = 0; i < altura2; i++) {
            System.out.println("Fila " + (i + 1) + ":");
            for (int j = 0; j < ancho2; j++) {
                matriz2[i][j] = scanner.nextInt();
            }
        }

        int[][] resultado;
        if (operador == '+') {
            resultado = sumar(matriz1, matriz2);
        } else if (operador == '-') {
            resultado = restar(matriz1, matriz2);
        } else if (operador == '*') {
            resultado = multiplicar(matriz1, matriz2, operador);
        } else {
            System.out.println("Operador no válido.");
            return;
        }

        // Imprimimos el resultado de la matriz
        System.out.println("Matriz Resultante:");
        for (int[] fila : resultado) {
            for (int elem : fila) {
                System.out.print(elem + " ");
            }
            System.out.println();
        }
    }
}