package com.jack.design_pattern.strategy;

/**
 * @author fztomaster
 * 只有一个抽象方法的接口叫函数式接口
 * @FunctionalInterface
 */
@FunctionalInterface
public interface MyComparator<T> {

    int compare(T o1, T o2);

    // jdk1.8之后，接口里面还可以写default方法
    default void test() {
        System.out.println("test...");
    }
}
