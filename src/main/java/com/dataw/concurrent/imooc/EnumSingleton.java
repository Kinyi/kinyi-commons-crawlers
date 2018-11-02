package com.dataw.concurrent.imooc;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 枚举模式单例
 *
 * @author Kinyi_Chan
 * @since 2018-09-06
 */
public class EnumSingleton {

    private EnumSingleton() {

    }

    public static EnumSingleton getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;
        private EnumSingleton instance;
        //JVM会保证此方法绝对只被调用一次
        Singleton() {
            instance = new EnumSingleton();
            System.out.println("invoke");
        }
        public EnumSingleton getInstance() {
            return instance;
        }
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
