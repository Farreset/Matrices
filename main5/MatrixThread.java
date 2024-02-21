package main5;
import java.util.Scanner;

class MatrixThread extends Thread {
    private int[][] resultado;
    private int[][] matriz1;
    private int[][] matriz2;
    private int fila;
    private int columna;
    private int comun;
    private char operador;
    	// Y aqui hemos declarado todas las matrices, resultados, columnas, filas, comun y operador //
    public MatrixThread(int[][] resultado, int[][] matriz1, int[][] matriz2, int fila, int columna, int comun, char operador) {
        this.resultado = resultado;
        this.matriz1 = matriz1;
        this.matriz2 = matriz2;
        this.fila = fila;
        this.columna = columna;
        this.comun = comun;
        this.operador = operador;
    }
    	 // Esta funcion es para poner la operacion que quieres si (+, - y *) //
    public void run() {
        int valor = 0;
        for (int i = 0; i < comun; i++) {
            if (operador == '+') {
                valor += matriz1[fila][i] + matriz2[i][columna]; // Suma de los elementos correspondientes de las matrices
            } else if (operador == '-') {
                valor += matriz1[fila][i] - matriz2[i][columna]; // Resta de los elementos correspondientes de las matrices
            } else if (operador == '*') {
                valor += matriz1[fila][i] * matriz2[i][columna]; // Multiplicación de los elementos correspondientes de las matrices
            }
        }
        resultado[fila][columna] = valor; // Asignación del valor al resultado
    }
}