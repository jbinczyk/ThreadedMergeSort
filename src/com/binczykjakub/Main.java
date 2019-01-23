package com.binczykjakub;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println("Watkow wolnych: "+Runtime.getRuntime().availableProcessors());

        ArrayList<Integer> tablica = new ArrayList<>();

        Random rand = new Random();
        for(int i=0; i<10; i++){
            tablica.add(rand.nextInt(50));
        }
        System.out.println("Przed sortowaniem: ");
        printArray(tablica);

        MergeSortConcurrent.sort(tablica);

        System.out.println("\n\nPo sortowaniu: ");
        printArray(tablica);

     }

     public static void printArray(ArrayList<Integer> arr){
        for(int i=0; i<arr.size(); i++){
            System.out.print(arr.get(i));
            if(i<arr.size()-1)
                System.out.print(" ");
        }
     }
}
