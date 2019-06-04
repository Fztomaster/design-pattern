package com.jack.design_pattern.singleton;

/**
 * @author fztomaster
 * 饿汉式：
 *  类加载到内存后，就实例化一个单例，JVM保证线程安全
 *  简单实用，推荐使用!
 *  缺点：不管是否用到，类加载时就完成实例化
 *  Class.forName("xxx") -> 类加载
 */
public class EagerSingleton01 {

    private static final EagerSingleton01 INSTANCE = new EagerSingleton01();

    /**
     * 构造方式私有化：让外部不能通过new的方式创建对象
     */
    private EagerSingleton01() {}

    /**
     * 对外提供一个获取实例的方法
     * @return INSTANCE
     */
    public static EagerSingleton01 getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        EagerSingleton01 eager01 = EagerSingleton01.getInstance();
        EagerSingleton01 eager02 = EagerSingleton01.getInstance();
        System.out.println(eager01 == eager02); // true
    }
}
