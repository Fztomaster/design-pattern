package com.jack.design_pattern.singleton;

/**
 * @author fztomaster
 * 懒汉式：
 *  根据需要加载，然后创建对象
 * 解决方案三:
 *  双重检查
 */
public class LazySingleton06 {

    private static volatile LazySingleton06 INSTANCE;

    private LazySingleton06() {}

    public static LazySingleton06 getInstance() {
        if (INSTANCE == null) {
            // 双重检查
            synchronized (LazySingleton05.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new LazySingleton06();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(LazySingleton06.getInstance().hashCode());
            }).start();
        }
    }
}
