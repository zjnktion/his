package cn.zjnktion.his.server.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author zjnktion
 */
public class JsonUtil {
    private static ObjectMapper mapper = null;

    static {
        mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, Boolean.FALSE);
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, Boolean.FALSE);
        mapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss:SSS"));
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static <T> String getJsonString(T object) {
        try {
            return mapper.writeValueAsString(object);
        }
        catch (IOException e) {
            return null;
        }
    }


    public static <T> T json2Object(String json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        T obj = null;
        try {
            obj = mapper.readValue(json, clazz);
        }
        catch (IOException e) {
        }
        return obj;
    }

    public static <T> T json2Object(String json, TypeReference<T> typeRef) {
        T obj = null;
        try {
            obj = mapper.readValue(json, typeRef);
        }
        catch (IOException e) {
        }
        return obj;
    }

    public static <T> T json2Object(String json, Class<T> parametrized, Class<?>... parameterClasses) {
        if (json == null) return null;
        T obj = null;
        try {
            obj = mapper.readValue(json, mapper.getTypeFactory().constructParametricType(parametrized, parameterClasses));
        }
        catch (IOException e) {
        }
        return obj;
    }
}
