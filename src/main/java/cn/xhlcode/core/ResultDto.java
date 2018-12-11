package cn.xhlcode.core;

import cn.xhlcode.core.enums.ResponseCodeEnum;

import java.io.Serializable;

public class ResultDto<T> implements Serializable {


    private static final long serialVersionUID = -7224469610250722986L;


    /**
     * 返回数据
     */
    private T result;


    /**
     * 错误码
     */
    private int code = 0;


    /**
     * 成功 / 失败 标识
     */
    private boolean success = true;

    /**
     * 提示信息
     */
    private String message;

    public static <T> ResultDto<T> success(T result) {
        ResultDto<T> response = new ResultDto<T>();
        response.result = result;
        return response;
    }


    public static <T> ResultDto<T> success(){
        return new ResultDto<T>();
    }



    public static <T> ResultDto<T> error() {
        ResultDto<T> resultDto = new ResultDto<T>();
        resultDto.success = false;
        resultDto.code = ResponseCodeEnum.ERROR.getCode();
        return resultDto;
    }

    public static <T> ResultDto<T> error(int code,String message) {
        ResultDto<T> resultDto  = new ResultDto<T>();
        resultDto.success = false;
        resultDto.code = code;
        resultDto.message = message;
        return resultDto;
    }

    public static <T> ResultDto<T> error (int code) {
        ResultDto<T> resultDto = new ResultDto<T>();
        resultDto.success = false;
        resultDto.code = code;
        return resultDto;
    }



    public T getResult() {
        return result;
    }

    public ResultDto<T> setResult(T result) {
        this.result = result;
        return this;
    }

    public int getCode() {
        return code;
    }

    public ResultDto<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public ResultDto<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultDto<T> setMessage(String message) {
        this.message = message;
        return this;
    }
}
