package com.dataw.concurrent.imooc;

import com.google.common.collect.ImmutableList;

import java.util.Collections;
import java.util.HashMap;

/**
 * @author Kinyi_Chan
 * @since 2018-09-06
 */
public class Static {

    static {
        instance = new Static();
    }

    private static Static instance = null;

    private Static() {

    }

    public static Static getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        Collections.unmodifiableMap(new HashMap());
//        ImmutableList.of
        ThreadLocal<Long> holder = new ThreadLocal<>();
//        holder.
    }
}
