package com.mp09uf2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Navidad {

        public static void main(String[] args) throws IOException {

            System.out.println("Usando Expresiones Regulares\n");
            RegEx();

            System.out.println("\n\nSin Usar Expresiones Regulares\n");
            NoRegEx();
        }



        static void RegEx() throws IOException {

            File file = new File("/home/dam2a/Escriptori/santako.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int noel = 0;
            int reindeer = 0;
            int elf = 0;

            while (true) {
                String linia = bufferedReader.readLine();
                try {
                    Pattern NoelPattern = Pattern.compile( "\\*<]:-DOo");
                    Matcher NoelMatcher = NoelPattern.matcher(linia);
                    while (NoelMatcher.find()){
                        noel++;
                    }
                    Pattern RdeerPattern = Pattern.compile( ">:o\\)");
                    Matcher RdeerMatcher = RdeerPattern.matcher(linia);
                    while (RdeerMatcher.find()){
                        reindeer++;
                    }
                    Pattern ElfPattern = Pattern.compile( "<]:-D");
                    Matcher ElfMatcher = ElfPattern.matcher(linia);
                    while (ElfMatcher.find()){
                        elf++;
                    }

                    // Los caracteres de duende tienen el mismo patron que una parte de Noel, por lo que Duende también suma cuando suma Noel pero no a la inversa, por eso restamos las veces que aparece Noel con las que ha aparecido Duende.
                    elf -= noel;

                    if (noel>0){
                        System.out.print("Noel -> " +  noel);
                        noel = 0;
                    }
                    if (reindeer>0){
                        System.out.print("Reindeer ->" + reindeer);
                        reindeer = 0;
                    }
                    if (elf>0){
                        System.out.print( "Elf ->  " + elf);
                        elf = 0;
                    }
                    System.out.println();

                } catch (NullPointerException e) {
                    bufferedReader.close();
                    break;
                }
            }
        }

        static void NoRegEx() throws IOException {

            // Con este metodo vamos guardando caracteres del mismo tamaño que los caracteres que buscamos, lo metemos en una string y luego los comparamos con lo que buscamos.
            // Ej. Noel es esta string *<]:-DOo tiene 8 chars, entonces recorremos la linea del archivo leido guardando 8 chars a un string para compararlo con Noel

            File file = new File("/home/dam2a/Escriptori/santako.txt");
            FileReader fReader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(fReader);

            int noel = 0;
            int reindeer = 0;
            int elf = 0;


            while (true) {
                String linea = bReader.readLine();
                try {
                    for (int i = 0; i < linea.length(); i++) {

                        // Strings que contienen el texto para comparar.
                        String compareNoel ="";
                        String compareReindeer ="";
                        String compareElf ="";

                        // Si la posicion de la i es menor o igual al tamaño de los caracteres de Noel entonces coge el char en la posición i y en las 7 siguientes para comparar si son iguales
                        if (i+8 <= linea.length()){
                            for (int j = i; j < i+8; j++) {
                                char d = linea.charAt(j);
                                compareNoel += d;
                            }
                        }
                        // Si la posicion de la i es menor o igual al tamaño de los caracteres de Reno entonces coge el char en la posición i y en las 3 siguientes para comparar si son iguales
                        if (i+4 <= linea.length()){
                            for (int j = i; j < i+4; j++) {
                                char d = linea.charAt(j);
                                compareReindeer += d;
                            }
                        }
                        // Si la posicion de la i es menor o igual al tamaño de los caracteres de Duende entonces coge el char en la posición i y en las 4 siguientes para comparar si son iguales
                        if (i+5 <= linea.length()){
                            for (int j = i; j < i+5; j++) {
                                char d = linea.charAt(j);
                                compareElf += d;
                            }
                        }
                        if ("*<]:-DOo".equals(compareNoel)){
                            noel++;
                        } else if (">:o)".equals(compareReindeer)){
                            reindeer++;
                        } else if ("<]:-D".equals(compareElf)){
                            elf++;
                        }
                    }

                    // Los caracteres de duende tienen el mismo patron que una parte de Noel, por lo que Duende también suma cuando suma Noel pero no a la inversa, por eso restamos las veces que aparece Noel con las que ha aparecido Duende.
                    elf -= noel;

                    if (noel>0){
                        System.out.print("Noel -> " +  noel);
                        noel = 0;
                    }
                    if (reindeer>0){
                        System.out.print("Reindeer -> " + reindeer);
                        reindeer = 0;
                    }
                    if (elf>0){
                        System.out.print( "Elf ->" + elf);
                        elf = 0;
                    }
                    System.out.println();
                } catch (NullPointerException e) {
                    bReader.close();
                    break;
                }
            }
        }
}
