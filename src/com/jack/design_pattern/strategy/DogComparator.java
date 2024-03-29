package com.jack.design_pattern.strategy;

/**
 * @author fztomaster
 */
public class DogComparator implements MyComparator<Dog> {
    @Override
    public int compare(Dog o1, Dog o2) {
        if (o1.food > o2.food) return 1;
        else if (o1.food < o2.food) return -1;
        else return 0;
    }
}
