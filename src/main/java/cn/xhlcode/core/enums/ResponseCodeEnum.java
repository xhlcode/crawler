package cn.xhlcode.core.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ResponseCodeEnum {

    UNKNOW(-1,"未知错误"),
    SUCCESS(200,"请求成功"),
    ERROR(201,"请求失败");

    private Integer code;
    private String text;
    private static final Map<Integer, ResponseCodeEnum> maps = new HashMap<Integer, ResponseCodeEnum>();

    static {
        for (ResponseCodeEnum type : EnumSet.allOf(ResponseCodeEnum.class)) {
            maps.put(type.code,type);
        }
    }


    private ResponseCodeEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public static ResponseCodeEnum getValue (Integer code) {
        return maps.get(code);
    }

    public Integer getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

}
