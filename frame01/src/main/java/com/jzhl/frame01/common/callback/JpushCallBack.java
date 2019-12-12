package com.jzhl.frame01.common.callback;

import cn.jiguang.common.resp.ResponseWrapper;

/**
 * 极光异步推送调用推送之后结果的回调
 * @author xiaobin
 */
public interface  JpushCallBack {
    public void result(ResponseWrapper responseWrapper);
}
