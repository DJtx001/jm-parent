//package com.djh.filter;
//
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import utils.JwtUtils;
//
//import java.io.IOException;
//
//@Slf4j
//@WebFilter(urlPatterns = "/*")
//public class FilterToken implements Filter {
//    @Override
//    public void doFilter(ServletRequest servletRequest,
//                         ServletResponse servletResponse,
//                         FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
////        是否是登入验证
//        if("/login".equals(request.getRequestURI())){
//            log.info("验证是为登入，放行");
//            chain.doFilter(request,response);
//            return;
//        }
////        验证令牌
//        String token = request.getHeader("token");
//        if(token == null || token.equals("")){
//            log.info("验证令牌是为空，401");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
////        解析令牌
//        try {
//            Claims claims = JwtUtils.parseToken( token);
//        } catch (Exception e) {
//            log.info("令牌无法解析，401");
//            response.setStatus (HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
//
//        log.info("令牌验证成功，放行");
//        chain.doFilter(request,response);
//
//
//    }
//}
