package com.binczykjakub;

import java.util.ArrayList;

public class MergeSortConcurrent extends Thread {


    // Publiczna metoda sortujaca
    static void sort(ArrayList<Integer> array){
        if(array.size()>1){
            mergeSortMulti(array, 0, array.size()-1);
        }
    }

    private static void mergeSortMulti(ArrayList<Integer> array, int left, int right){
        if(left < right){
            int middle = (left+right)/2;
            if(Runtime.getRuntime().availableProcessors()>1){
                Thread t1 = new Thread(()->mergeSortMulti(array, left, middle));
                Thread t2 = new Thread(()->mergeSortMulti(array,middle+1,right));
                t1.start();
                t2.start();
                try{
                    t1.join();
                    t2.join();
                } catch (InterruptedException e){

                }
            } else
                mergeSort(array, left, right);

            merge(array, left, middle, right);
        }
    }

    private static void mergeSort(ArrayList<Integer> array, int left, int right){
        if(left < right){
            int middle = (left+right)/2;

            mergeSort(array, left, middle);
            mergeSort(array, middle+1, right);

            mergeMulti(array, left, middle, right);
        }
    }

    private static void mergeMulti(ArrayList<Integer> array, int left, int middle, int right){
        if(Runtime.getRuntime().availableProcessors()>1){
            Thread t1 = new Thread(()->merge(array, left, middle, right));
            t1.start();
            try{
                t1.join();
            } catch (InterruptedException e){

            }
        } else
            merge(array, left, middle, right);
    }

    private static void merge(ArrayList<Integer> array, int left, int middle, int right){
        int size = right - left + 1;
        int[] temp = new int[size];

        int i1 = left;
        int i2 = middle + 1;

        int j = 0;
        while (i1 <= middle && i2 <= right) {
            if (array.get(i1) < array.get(i2)) {
                temp[j] = array.get(i1);
                i1++;
            } else {
                temp[j] = array.get(i2);
                i2++;
            }
            j++;
        }

        while (i1 <= middle) {
            temp[j] = array.get(i1);
            i1++;
            j++;
        }

        while (i2 <= right) {
            temp[j] = array.get(i2);
            i2++;
            j++;
        }

        for (j = 0; j < size; j++) {
            array.set(left + j, temp[j]);
        }
    }
}
