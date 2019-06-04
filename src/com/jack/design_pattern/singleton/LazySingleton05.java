package com.jack.design_pattern.singleton;

/**
 * @author fztomaster
 * 懒汉式:
 *  按需加载，解决了饿汉式的问题
 *  根据需要去创建对象，但是会有线程安全问题
 * 解决方法二：
 *  可以通过synchronized同步代码块解决(实际上并不能解决)，但是效率会下降
 */
public class LazySingleton05 {

    private static LazySingleton05 INSTANCE;

    private LazySingleton05() {}

    public static LazySingleton05 getInstance() {
        if (INSTANCE == null) {
            // 妄图通过减小同步代码块的方式提高效率，然后不可行
            synchronized (LazySingleton05.class) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new LazySingleton05();
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(LazySingleton05.getInstance().hashCode());
            }).start();
        }
    }
}
