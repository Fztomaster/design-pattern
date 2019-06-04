package com.jack.design_pattern.strategy;

/**
 * @author fztomaster
 */
public class CatWeightComparator implements MyComparator<Cat> {

    @Override
    public int compare(Cat o1, Cat o2) {
        if (o1.weight > o2.weight) return 1;
        else if (o1.weight < o2.weight) return -1;
        else return 0;
    }
}
