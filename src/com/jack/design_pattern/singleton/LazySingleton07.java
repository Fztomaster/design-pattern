package com.jack.design_pattern.singleton;

/**
 * @author fztomaster
 * 静态内部类方式
 * JVM保证单例
 * 加载外部类时不会加载内部类，这是可以实现懒汉式
 */
public class LazySingleton07 {

    private LazySingleton07() {}

    private static class LazySingleton07Holder {
        private static final LazySingleton07 INSTANCE = new LazySingleton07();
    }

    public static LazySingleton07 getInstance() {
        return LazySingleton07Holder.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(LazySingleton07.getInstance().hashCode());
            }).start();
        }
    }
}
