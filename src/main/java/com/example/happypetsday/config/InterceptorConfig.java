package com.example.happypetsday.config;

import com.example.happypetsday.interceptor.AdminInterceptor;
import com.example.happypetsday.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final LoginInterceptor loginInterceptor;
    private final AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        일반 회원 세션 확인
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/myPage/**") //해당 url로 시작하는 모든 경로
//             .addPathPatterns("/세션 검사를 할 url")
                .addPathPatterns("/stroll/write") //검사할 페이지를 지정
                .addPathPatterns("/sitter/apply");
//        관리자 여부 확인
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/admin/**");
    }
}
