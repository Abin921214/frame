package com.jzhl.frame01.common.bean;

/**
 * api通用返回类
 *  code : 0 失败  1 成功
 *  msg : 返回信息
 *  data: 返回泛型数据
 * @author xiaobin
 */
public class BaseResult<T> {

    private T data;
    private Integer code;
    private String msg;

    public BaseResult(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public BaseResult(Integer code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
