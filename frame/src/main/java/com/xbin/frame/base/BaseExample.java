package com.xbin.frame.base;

/**
 * 基础 Example
 * @author xiaobin
 */
public class BaseExample {
    private Integer limit;

    private Long offset;

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }
}
