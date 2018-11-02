package com.dataw.practice.thread;

//import org.apache.log4j.Logger;

/**
 * Created by Kinyi_Chan on 12/04/2017.
 * <p>
 * 变形金刚类
 */
public class Transformation {
//    private static final Logger LOG = Logger.getLogger(Transformation.class);
    //    private static Transformation transformation;
    private static ThreadLocal<Transformation> map = new ThreadLocal<Transformation>();

    private String type = "擎天柱1";

    private Transformation() {
    }

    public static Transformation getInstance() {
        Transformation transformation = map.get();
        if (transformation == null) {
            transformation = new Transformation();
            map.set(transformation);

        }
        return transformation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
