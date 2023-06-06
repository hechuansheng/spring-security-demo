package org.hechuans.demo.security.indb.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hechuans.demo.security.indb.model.pojo.ReturnData;
import org.hechuans.demo.security.indb.model.pojo.SecurityUser;
import org.hechuans.demo.security.indb.util.JWTUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : hechuansheng
 * @date : 2023/6/6 11:12
 * @description :
 * @since : version-1.0
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) securityUser.getAuthorities();
        List<String> authorityStrList = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        String userJsonStr = objectMapper.writeValueAsString(securityUser.getUser());
        String jwtToken = JWTUtils.createJWT(userJsonStr, authorityStrList);
        ReturnData returnData = ReturnData.builder().code(200).msg("success").data(jwtToken).build();
        printlnReturnData(response, returnData);

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
