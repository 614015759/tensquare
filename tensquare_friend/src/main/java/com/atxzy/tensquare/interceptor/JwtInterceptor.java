package com.atxzy.tensquare.interceptor;

import com.atxzy.tensquare.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器");

        //无论如何都是要放行的
        //拦截器只是负责把请求头中的token进行解析
        String header = request.getHeader("Authorization");
        if(!StringUtils.isEmpty(header)){
            //如果有头信息，就对其进行解析
            if(header.startsWith("Bearer ")){
                String token = header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String role = (String) claims.get("roles");
                    if (role!=null || role.equals("admin")){
                        request.setAttribute("claims_admin",claims);
                    }
                    if (role!=null || role.equals("user")){
                        request.setAttribute("claims_user",claims);
                    }
                }catch (Exception e){
                   throw new RuntimeException("令牌不正确");
                }
            }

        }
        return true;

    }
}
