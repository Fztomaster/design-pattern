package com.jack.design_pattern.strategy;

/**
 * @author fztomaster
 */
public class Cat implements MyComparable<Cat> {

    public int height;

    public int weight;

    public Cat(int height, int weight) {
        this.height = height;
        this.weight = weight;
    }

    @Override
    public int compareTo(Cat cat) {
        if (this.weight > cat.weight) return 1;
        else if (this.weight < cat.weight) return -1;
        else return 0;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "height=" + height +
                ", weight=" + weight +
                '}';
    }
}
