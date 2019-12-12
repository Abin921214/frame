//package com.jzhl.frame01.common.config;
//
//
//import com.jzhl.frame01.common.base.InterceptApi;
//import com.jzhl.frame01.common.base.InterceptEnum;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Method;
//import java.security.Permission;
//
///**
// * 根据注解拦截
// * @author xiaobin
// */
//public class webConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(new HandlerInterceptor() {
//            @Override
//            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//                //添加拦截内容
//                System.out.println(1);
//                HandlerMethod handlerMethod = (HandlerMethod) handler;
//                Method method = handlerMethod.getMethod();
//                InterceptApi tt = method.getAnnotation(InterceptApi.class);
//                System.out.println(tt);
//
//                return super.preHandle(request, response, handler);
//                return true;
//            }
//
//            @Override
//            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//                System.out.println(2);
//            }
//
//            @Override
//            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//                System.out.println(3);
//            }
//        });
//    }
//
//}
