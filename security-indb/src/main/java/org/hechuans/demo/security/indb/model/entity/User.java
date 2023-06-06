package org.hechuans.demo.security.indb.model.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author : hechuansheng
 * @date : 2023/6/6 10:13
 * @description :
 * @since : version-1.0
 */
@Data
@Builder
public class User {

    private long userId;

    private String userName;

    private String password;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

}
