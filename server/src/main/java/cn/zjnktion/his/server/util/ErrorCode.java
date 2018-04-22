package cn.zjnktion.his.server.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zjnktion
 */
public class ErrorCode {

    public static final int SUCCESS = 0;
    public static final int ERROR = -1;

    public static final Map<Integer, String> codeMsgMap = new HashMap<Integer, String>() {
        {
            put(SUCCESS, "系统调用成功");
            put(ERROR, "系统调用失败");
        }
    };
}
