package org.hechuans.demo.security.inmemory.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author : hechuansheng
 * @date : 2023/6/5 16:45
 * @description :
 * @since : version-1.0
 */
@RestController
@RequestMapping("/user/login/current")
public class CurrentLoginUserController {

    @GetMapping("authentication")
    public Authentication getAuthentication(Authentication authentication) {
        return authentication;
    }

    @GetMapping("principal")
    public Principal getPrincipal(Principal principal) {
        return principal;
    }

    @GetMapping("context")
    public Principal getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
