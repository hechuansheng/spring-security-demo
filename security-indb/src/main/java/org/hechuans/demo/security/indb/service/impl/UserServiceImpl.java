package org.hechuans.demo.security.indb.service.impl;

import org.hechuans.demo.security.indb.model.entity.User;
import org.hechuans.demo.security.indb.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : hechuansheng
 * @date : 2023/6/6 10:14
 * @description :
 * @since : version-1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public User findUserByUserName(String userName) {
        if (userName.equals("he")) {
            return User.builder()
                    .userId(1L)
                    .userName("he")
                    .password(passwordEncoder.encode("123456"))
                    .accountNonExpired(true)
                    .accountNonLocked(false)
                    .accountNonExpired(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .build();
        } else if (userName.equals("admin")) {
            return User.builder()
                    .userId(2L)
                    .userName("admin")
                    .password(passwordEncoder.encode("123456"))
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .accountNonExpired(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .build();
        }

        return null;
    }
}
