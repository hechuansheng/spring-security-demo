package org.hechuans.demo.security.indb.model.pojo;

import lombok.Getter;
import lombok.Setter;
import org.hechuans.demo.security.indb.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author : hechuansheng
 * @date : 2023/6/6 10:12
 * @description :
 * @since : version-1.0
 */

public class SecurityUser implements UserDetails {

    @Getter
    private final User user;

    @Setter
    private List<GrantedAuthority> Authorities;

    public SecurityUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Authorities;
    }

    @Override
    public String getPassword() {
        String password = user.getPassword();
        user.setPassword(null);
        return password;
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    public long getUserId() {
        return user.getUserId();
    }
}
