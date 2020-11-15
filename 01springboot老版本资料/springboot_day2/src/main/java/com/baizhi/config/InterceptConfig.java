package com.baizhi.config;

import com.baizhi.interceptors.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//用来注入自定义拦截器
@Component
public class InterceptConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private MyInterceptor myInterceptor;

    //覆盖父类中添加拦截器方法
    //参数1:InterceptorRegistry 拦截器注册对象
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor)
        .addPathPatterns("/file/**")
        .excludePathPatterns("/file/upload")
        ;//添加一个拦截器
    }
}
