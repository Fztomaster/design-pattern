package com.jack.design_pattern.singleton;

/**
 * @author fztomaster
 * 枚举-最优方案
 * 不仅可以解决线程同步，还可以防止反序列化
 */
public enum LazySingleton08 {

    INSTANCE;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(LazySingleton08.INSTANCE.hashCode());
            }).start();
        }
    }
}
