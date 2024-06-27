package com.spring.mood.projectmvc.config.localConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 로컬 파일경로를 웹서버 URL 로 변경하기
@Configuration
public class LocalConfig implements WebMvcConfigurer {

    @Value("${maroon5upload.root-path}")
    private String rootPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler("/local/**") // URL 변환 경로 (/local/파일명)
                .addResourceLocations("file:" + rootPath); // 로컬 경로 (file:D:\) = /local
    }
}
