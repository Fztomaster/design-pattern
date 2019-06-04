package com.jack.design_pattern.strategy;

/**
 * @author fztomaster
 */
public class Dog implements MyComparable<Dog> {

    public int food;

    public Dog(int food) {
        this.food = food;
    }

    @Override
    public int compareTo(Dog dog) {
        if (this.food > dog.food) return 1;
        else if (this.food < dog.food) return -1;
        else return 0;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "food=" + food +
                '}';
    }
}
