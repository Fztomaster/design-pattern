package com.jack.design_pattern.strategy;

import java.util.Arrays;

/**
 * @author fztomaster
 */
public class Main {

    public static void main(String[] args) {
        testIntSort();
        testCatWeightSort();
        testMyComparableSort();
        testTSorter();
    }

    public static void testIntSort() {
        int[] arr = {4, 3, 8, 6, 1, 2};
        IntSorter sorter = new IntSorter();
        sorter.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void testCatWeightSort() {
        Cat[] cats = {new Cat(1, 3), new Cat(1, 2), new Cat(1, 5)};
        CatSorter sorter = new CatSorter();
        sorter.sort(cats);
        System.out.println(Arrays.toString(cats));
    }

    public static void testMyComparableSort() {
        Dog[] dogs = {new Dog(3), new Dog(2), new Dog(1)};
        MySorter mySorter = new MySorter();
        mySorter.sort(dogs);
        System.out.println(Arrays.toString(dogs));
    }

    public static void testTSorter() {
        Dog[] dogs = {new Dog(3), new Dog(2), new Dog(1)};
        TSorter<Dog> tSorter = new TSorter<>();
        tSorter.sort(dogs, new DogComparator());
        System.out.println(Arrays.toString(dogs));

        Cat[] cats = {new Cat(9, 3), new Cat(5, 2), new Cat(2, 5)};
        TSorter<Cat> catTSorter = new TSorter<>();
        catTSorter.sort(cats, new CatHeightComparator());
        System.out.println(Arrays.toString(cats));

        catTSorter.sort(cats, new CatWeightComparator());
        System.out.println(Arrays.toString(cats));
    }


}
