package com.dataw.concurrent.imooc;

/**
 * 懒汉式 -> 双重同步锁单例模式
 * 单例实例在第一次使用时进行创建
 *
 * ps: 饿汉式是线程安全的, 但是如果构造函数中进行了大量初始化操作, 但后续没有使用到该对象, 会造成资源浪费
 *
 * @author Kinyi_Chan
 * @since 2018-09-06
 */
public class SingletonExample {
    //单例对象
    private volatile static SingletonExample instance = null;
    //私有构造函数
    private SingletonExample() {
    }
    /**
     * 静态的工厂方法
     *
     * 实例化对象的步骤
     * 1. memory = allocate() 分配对象的内存空间
     * 2. ctorInstance()      初始化对象
     * 3. instance = memory   设置instance指向刚分配的内存
     */
    public static SingletonExample getInstance() {
        if (instance == null) { //双重检测机制
            synchronized (SingletonExample.class) {
                if (instance == null) {
                    instance = new SingletonExample(); //2-3步骤有可能指令重排, 导致非空的instance没有进行初始化
                }
            }
        }
        return instance;
    }
}
