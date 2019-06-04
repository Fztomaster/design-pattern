# 单例总结

单例：证一个类仅有一个实例，并提供一个访问它的全局访问点。 

## 饿汉式

​	类加载到内存后，就实例化一个单例，JVM保证线程安全

​	简单实用，推荐使用!

​	缺点：不管是否用到，类加载时就完成实例化

​	Class.forName("xxx") -> 类加载

第一种:

```java
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
```

第二种(原理和第一种一样)：

```java
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
```



## 懒汉式

按需加载，解决了饿汉式的问题， 根据需要去创建对象，但是会有线程安全问题

```java
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
```

### 加锁(同步方法块和同步代码块)

```java
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
```

```java
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
```

### 双重检查

```java
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
```

## 静态内部类

​	静态内部类方式，JVM保证单例，加载外部类时不会加载内部类，这是可以实现懒汉式

```java
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
```

## 枚举

```java
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
```

