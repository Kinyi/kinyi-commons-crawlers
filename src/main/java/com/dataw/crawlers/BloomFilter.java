package com.dataw.crawlers;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.BitSet;

/**
 * @author Kinyi_Chan
 * @since 2018-10-21
 */
@Slf4j
public class BloomFilter {
    /**
     * BitSet初始分配2^24个bit
     */
    private static final int DEFAULT_SIZE = 1 << 25;

    /**
     * 不同哈希函数的种子，一般应取质数
     */
    private static final int[] seeds = new int[]{5, 7, 11, 13, 31, 37, 61};
    private BitSet bits = new BitSet(DEFAULT_SIZE);

    /**
     * 哈希函数对象
     */
    private SimpleHash[] func = new SimpleHash[seeds.length];

    public BloomFilter() {
        for (int i = 0; i < seeds.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
        }
    }

    /**
     * 将字符串标记到bits中
     * @param value
     */
    public void add(String value) {
        for (SimpleHash f : func) {
            bits.set(f.hash(value), true);
        }
    }

    /**
     * 判断字符串是否已经被bits标记
     * @param value
     * @return
     */
    public boolean contains(String value) {
        if (value == null) {
            return false;
        }
        boolean ret = true;
        for (SimpleHash f : func) {
            ret = ret && bits.get(f.hash(value));
        }
        return ret;
    }

    /**
     * 哈希函数类
     */
    public static class SimpleHash {
        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        /**
         * hash函数，采用简单的加权和hash
         * @param value
         * @return
         */
        public int hash(String value) {
            int result = 0;
            int len = value.length();
            for (int i = 0; i < len; i++) {
                result = seed * result + value.charAt(i);
            }
            return (cap - 1) & result;
        }
    }

    /**
     * test case
     */
    private static final String[] URLS = {
            "http://www.csdn.net/",
            "http://www.baidu.com/",
            "http://www.google.com.hk",
            "http://www.cnblogs.com/",
            "http://www.zhihu.com/",
            "https://www.shiyanlou.com/",
            "http://www.google.com.hk",
            "https://www.shiyanlou.com/",
            "http://www.csdn.net/"
    };

    public static void main(String[] args) {
//        BloomFilter filter = new BloomFilter();
//        for (int i = 0; i < URLS.length; i++) {
//            if (filter.contains(URLS[i])) {
//                log.info("contain: " + URLS[i]);
//                continue;
//            }
//            filter.add(URLS[i]);
//        }

        System.out.println(1<<30);

    }
}