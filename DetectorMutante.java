package com.mycompany.tpmutantes;


import java.util.Scanner;

// Casos de prueba: mutante:["ATCCCC","ATGCGT","AGCTAT","ACCCCG","GAAGCT","TAGTCA"], no mutante: ["ATCCGA", "CAGTGC", "TTATGT", "AGAATG", "TCCCTA", "TCACTG"]

public class DetectorMutante {
    public static boolean caracteresPorCaracter(String palabraIngresada) {
        String[] letras = {"A", "T", "C", "G"};
        for (char c : palabraIngresada.toCharArray()) {
            if (!contiene(letras, String.valueOf(c))) {
                return false;
            }
        }
        return true;
    }

    public static boolean contiene(String[] arreglo, String valorObjetivo) {
        for (String s : arreglo) {
            if (s.equals(valorObjetivo)) {
                return true;
            }
        }
        return false;
    }

    public static boolean esMutante(String[] adn) {
        int contador = 0;

        // Verificar filas
        for (String fila : adn) {
            for (int i = 0; i < fila.length() - 3; i++) {
                if (fila.charAt(i) == fila.charAt(i + 1) &&
                    fila.charAt(i) == fila.charAt(i + 2) &&
                    fila.charAt(i) == fila.charAt(i + 3)) {
                    contador++;
                    break;
                }
            }
        }

        // Verificar columnas
        int numFilas = adn.length;
        int numColumnas = adn[0].length();
        for (int i = 0; i < numColumnas; i++) {
            for (int j = 0; j < numFilas - 3; j++) {
                if (adn[j].charAt(i) == adn[j + 1].charAt(i) &&
                    adn[j].charAt(i) == adn[j + 2].charAt(i) &&
                    adn[j].charAt(i) == adn[j + 3].charAt(i)) {
                    contador++;
                    break;
                }
            }
        }

        // Verificar diagonales
        for (int i = 0; i < numFilas - 3; i++) {
            for (int j = 0; j < numColumnas - 3; j++) {
                if (adn[j + 3].charAt(i) == adn[j + 2].charAt(i + 1) &&
                    adn[j + 3].charAt(i) == adn[j + 1].charAt(i + 2) &&
                    adn[j + 3].charAt(i) == adn[j].charAt(i + 3)) {
                    contador++;
                    break;
                }
                if (adn[j].charAt(i) == adn[j + 1].charAt(i + 1) &&
                    adn[j].charAt(i) == adn[j + 2].charAt(i + 2) &&
                    adn[j].charAt(i) == adn[j + 3].charAt(i + 3)) {
                    contador++;
                    break;
                }
            }
        }

        return contador >= 2;
    }

    public static void main(String[] args) {
        String[] adn = new String[6];
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 6; i++) {
            while (true) {
                System.out.print("Ingrese la fila " + (i + 1) + " del ADN: ");
                String filaAdn = scanner.next().toUpperCase();

                if (filaAdn.length() == 6 && caracteresPorCaracter(filaAdn)) {
                    adn[i] = filaAdn;
                    break;
                } else {
                    System.out.println("La fila ingresada es incorrecta, ingrese nuevamente.");
                }
            }
        }

        if (esMutante(adn)) {
            System.out.println("El ADN ingresado pertenece a un mutante.");
        } else {
            System.out.println("El ADN ingresado no pertenece a un mutante.");
        }
    }
}