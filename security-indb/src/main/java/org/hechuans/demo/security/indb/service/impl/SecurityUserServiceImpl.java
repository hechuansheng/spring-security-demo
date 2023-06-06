package org.hechuans.demo.security.indb.service.impl;

import org.hechuans.demo.security.indb.model.entity.User;
import org.hechuans.demo.security.indb.model.pojo.SecurityUser;
import org.hechuans.demo.security.indb.service.SecurityUserService;
import org.hechuans.demo.security.indb.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : hechuansheng
 * @date : 2023/6/6 10:15
 * @description :
 * @since : version-1.0
 */
@Service
public class SecurityUserServiceImpl implements SecurityUserService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userService.findUserByUserName(username);

        SecurityUser securityUser = new SecurityUser(userEntity);

        List<GrantedAuthority> authorityList = new ArrayList<>();
        SimpleGrantedAuthority teacherAuthority = new SimpleGrantedAuthority("teacher");
        SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority("admin");
        SimpleGrantedAuthority studentAuthority = new SimpleGrantedAuthority("student");
        authorityList.add(teacherAuthority);
        authorityList.add(adminAuthority);
        authorityList.add(studentAuthority);
        securityUser.setAuthorities(authorityList);

        return securityUser;
    }

}
