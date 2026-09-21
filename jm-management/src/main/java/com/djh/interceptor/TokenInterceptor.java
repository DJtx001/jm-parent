package com.djh.interceptor;

import com.djh.utils.CurrentUserHoler;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import utils.JwtUtils;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
//        获取URL
        String requestURL =request.getRequestURL().toString();
//        验证是否为登入
        if (requestURL.contains("/login")) {
            log.info("验证令牌为登入，放行");
            return true;
        }
        String token = request.getHeader("token");
//        验证是否存在令牌
        if(token == null || token.equals(" ")){
            log.info("令牌不存在，401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
//        验证令牌是否可以解析
        try {
            Claims claims = JwtUtils.parseToken(token);
            //获取用户id, 存入ThreadLocal
            Integer userId = claims.get("id", Integer.class);
            CurrentUserHoler.setCurrentUser(userId);
        } catch (Exception e) {
           log.info("令牌无法解析，401");
           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           return false;
        }
        log.info("令牌合法，放行");
        return true;
    }

    // 请求处理完成后清理 ThreadLocal，避免线程复用导致数据错乱和内存泄漏
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) {
        CurrentUserHoler.removeCurrentUser();
    }

}
