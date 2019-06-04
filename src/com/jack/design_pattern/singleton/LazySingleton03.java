package com.jack.design_pattern.singleton;

/**
 * @author fztomaster
 * 懒汉式:
 *  按需加载，解决了饿汉式的问题
 *  根据需要去创建对象，但是会有线程安全问题
 */
public class LazySingleton03 {

    private static LazySingleton03 INSTANCE;

    private LazySingleton03() {}

    public static LazySingleton03 getInstance() {
        if (INSTANCE == null) {
            // 为了测试
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new LazySingleton03();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        // 多个线程同时访问getInstance()，不能保证单例
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(LazySingleton03.getInstance().hashCode());
            }).start();
        }
    }
}
