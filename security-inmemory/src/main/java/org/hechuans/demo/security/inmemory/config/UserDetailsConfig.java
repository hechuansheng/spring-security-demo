package org.hechuans.demo.security.inmemory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author : hechuansheng
 * @date : 2023/6/5 15:40
 * @description :
 * @since : version-1.0
 */
@Configuration
public class UserDetailsConfig {

    @Bean
    public UserDetailsService myUserDetailsService(PasswordEncoder myPasswordEncoder) {
        UserDetails userHe = User.builder()
                .username("he")
                .password(myPasswordEncoder.encode("123456"))
                .roles("student").build();
        UserDetails userDing = User.builder()
                .username("ding")
                .password(myPasswordEncoder.encode("123456"))
                .roles("student").build();

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(userHe);
        manager.createUser(userDing);
        return manager;
    }

    @Bean
    public PasswordEncoder myPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
