package com.jack.design_pattern.strategy;

/**
 * @author fztomaster
 */
public class MySorter {

    public void sort(MyComparable[] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            int minPos = i;
            for(int j = i + 1; j < arr.length; j++) {
                minPos = arr[j].compareTo(arr[minPos]) == -1? j : minPos;
            }
            swap(arr, i, minPos);
        }
    }

    public void swap(MyComparable[] arr, int i, int j) {
        MyComparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
