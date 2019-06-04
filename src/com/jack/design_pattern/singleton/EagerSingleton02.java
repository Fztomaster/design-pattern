package com.jack.design_pattern.singleton;

/**
 * @author fztomaster
 * 饿汉式第二种：
 *  其实和第一种原理类似，都是类初始化加载，保证只有一个实例对象
 */
public class EagerSingleton02 {

    private static EagerSingleton02 INSTANCE;

    static {
        INSTANCE = new EagerSingleton02();
    }

    private EagerSingleton02() {}

    public static EagerSingleton02 getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        EagerSingleton02 eager01 = EagerSingleton02.getInstance();
        EagerSingleton02 eager02 = EagerSingleton02.getInstance();
        System.out.println(eager01 == eager02);
    }
}
