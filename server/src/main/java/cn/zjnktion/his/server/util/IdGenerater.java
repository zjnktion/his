package cn.zjnktion.his.server.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zjnktion
 */
public class IdGenerater {

    private static final AtomicInteger IG = new AtomicInteger(0);

    public static int get() {
        return IG.getAndIncrement();
    }
}
