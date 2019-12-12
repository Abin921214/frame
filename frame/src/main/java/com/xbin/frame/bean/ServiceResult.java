package com.xbin.frame.bean;

import java.util.List;

/**
 * service 层通用返回
 */
public class ServiceResult<T> {

    private Boolean isSuccess;

    private String msg;

    private Pages<T> dataPage;

    private List<T> dataList;

    private T data;

    private Integer count;

    /**
     * 失败处理
     * @param msg
     */
    public ServiceResult(String msg){
        this.isSuccess = false;
        this.msg = msg;
    }

    public ServiceResult(String msg, Boolean flag){
        this.isSuccess = flag;
        this.msg = msg;
    }

    /**
     * service 按条件查询某一个对象
     * @param msg     消息
     * @param data    数据
     */
    public ServiceResult(String msg, T data){
        this.isSuccess = true;
        this.msg = msg;
        this.data = data;
    }

    /**
     * service 按条件查询对List对象
     * @param msg       消息
     * @param dataList  List数据
     */
    public ServiceResult(String msg, List<T> dataList){
        this.isSuccess = true;
        this.msg = msg;
        this.dataList = dataList;
    }

    /**
     * service 按条件分页查询对应的数据
     * @param msg
     * @param dataPage
     */
    public ServiceResult(String msg, Pages<T> dataPage){
        this.isSuccess = true;
        this.dataPage = dataPage;
        this.msg = msg;
    }

    /**
     * service 按条件查询对应的数量
     * @param msg
     * @param count
     */
    public ServiceResult(String msg, Integer count){
        this.msg = msg;
        this.count = count;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Pages<T> getDataPage() {
        return dataPage;
    }

    public void setDataPage(Pages<T> dataPage) {
        this.dataPage = dataPage;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ServiceResult{" +
                "isSuccess=" + isSuccess +
                ", msg='" + msg + '\'' +
                ", dataPage=" + dataPage +
                ", dataList=" + dataList +
                ", data=" + data +
                ", count=" + count +
                '}';
    }
}
