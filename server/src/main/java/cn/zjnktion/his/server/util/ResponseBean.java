package cn.zjnktion.his.server.util;

/**
 * @author zjnktion
 */
public class ResponseBean<T> {

    public static ResponseBean SUCCESS = new ResponseBean(0);

    private int code;
    private String msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private T data;

    public ResponseBean(int code) {
        this.code = code;
        this.msg = ErrorCode.codeMsgMap.get(code);
    }

    public ResponseBean(int code, String message) {
        this.code = code;
        this.msg = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
