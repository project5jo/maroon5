package com.spring.mood.projectmvc.config.interceptorConfig;

import com.spring.mood.projectmvc.interceptor.AdminChangeInterceptor;
import com.spring.mood.projectmvc.interceptor.AfterLoginInterceptor;
import com.spring.mood.projectmvc.interceptor.AutoLoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final AfterLoginInterceptor afterLoginInterceptor;
    private final AutoLoginInterceptor autoLoginInterceptor;
    private final AdminChangeInterceptor adminChangeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(afterLoginInterceptor)
//                동작을 실행할 url4
                .addPathPatterns("/sign-in");
        registry.addInterceptor(autoLoginInterceptor)
                .addPathPatterns("/**");
        registry.addInterceptor(adminChangeInterceptor)
                .addPathPatterns("/zzz");
    }
}
