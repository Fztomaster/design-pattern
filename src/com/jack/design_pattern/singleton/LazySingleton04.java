package com.jack.design_pattern.singleton;

/**
 * @author fztomaster
 * 懒汉式:
 *  按需加载，解决了饿汉式的问题
 *  根据需要去创建对象，但是会有线程安全问题
 * 解决方法一：
 *  可以通过synchronized同步方法解决，但是效率会下降
 */
public class LazySingleton04 {

    private static LazySingleton04 INSTANCE;

    private LazySingleton04() {}

    // 加锁保证线程安全
    public static synchronized LazySingleton04 getInstance() {
        if (INSTANCE == null) {
            // 为了测试
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new LazySingleton04();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(LazySingleton04.getInstance().hashCode());
            }).start();
        }
    }
}
