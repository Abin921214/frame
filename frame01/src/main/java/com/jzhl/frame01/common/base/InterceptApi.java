package com.jzhl.frame01.common.base;

import java.lang.annotation.*;

/**
  * 自定义操作日志注解
  * @author xiaobin
 */
@Target({ElementType.METHOD, ElementType.TYPE}) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented
public @interface InterceptApi {
    InterceptEnum value() default InterceptEnum.TOKEN;
}
