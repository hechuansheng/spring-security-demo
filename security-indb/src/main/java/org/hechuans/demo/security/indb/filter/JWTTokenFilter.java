package org.hechuans.demo.security.indb.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hechuans.demo.security.indb.model.entity.User;
import org.hechuans.demo.security.indb.model.pojo.ReturnData;
import org.hechuans.demo.security.indb.util.JWTUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : hechuansheng
 * @date : 2023/6/6 17:49
 * @description :
 * @since : version-1.0
 */
@Component
public class JWTTokenFilter extends OncePerRequestFilter {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        //这里不能读取到WebSecurityConfig中的permitAll配置自动跳过
        if ("/login".equals(requestURI)) {
            doFilter(request, response, filterChain);
            return;
        }

        String authorizationStr = request.getHeader("Authorization");
        if (!StringUtils.hasLength(authorizationStr)) {
//            ReturnData returnData = ReturnData.builder().code(601).msg("failed").data("token为空").build();
//            printlnReturnData(response, returnData);
            //可以不返回ReturnData，继续往下走会跳转到登录界面
            doFilter(request, response, filterChain);
            return;
        }
        String jwtToken = authorizationStr.replace("Bearer ", "");

        if (!StringUtils.hasLength(jwtToken)) {
//            ReturnData returnData = ReturnData.builder().code(601).msg("failed").data("token为空").build();
//            printlnReturnData(response, returnData);
            doFilter(request, response, filterChain);
            return;
        }

        if (!JWTUtils.verifyJWTToken(jwtToken)) {
            ReturnData returnData = ReturnData.builder().code(601).msg("failed").data("用户登录过期！").build();
            printlnReturnData(response, returnData);
            return;
        }

        //从token中获取用户信息
        String userJsonString = JWTUtils.getUserFromJWTToken(jwtToken);
        User user = objectMapper.readValue(userJsonString, User.class);
        List<String> userAuthStrList = JWTUtils.getUserAuthFromJWTToken(jwtToken);
        List<SimpleGrantedAuthority> authorityList = userAuthStrList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        //添加用户信息到安全上下文
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, null, authorityList);
        SecurityContextHolder.getContext().setAuthentication(token);

        doFilter(request, response, filterChain);
    }

    private void printlnReturnData(HttpServletResponse response, ReturnData returnData) throws IOException {
        String returnDataStr = objectMapper.writeValueAsString(returnData);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println(returnDataStr);
        writer.flush();
    }
}
