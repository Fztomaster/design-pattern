package com.jack.design_pattern.strategy;

/**
 * @author fztomaster
 */
public class CatSorter {

    public void sort(Cat[] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            int minPos = i;
            for(int j = i + 1; j < arr.length; j++) {
                minPos = arr[j].compareTo(arr[minPos]) == -1? j : minPos;
            }
            swap(arr, i, minPos);
        }
    }

    public void swap(Cat[] arr, int i, int j) {
        Cat temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
