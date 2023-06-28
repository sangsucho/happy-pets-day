package com.example.happypetsday.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object userNumber =request.getSession().getAttribute("userNumber");
        Object adminNumber = request.getSession().getAttribute("adminNumber");

        if(userNumber==null){
            response.sendRedirect("/user/login");
            return false;
        }else if(adminNumber==null){
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
