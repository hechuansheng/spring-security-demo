package org.hechuans.demo.security.indb.service;

import org.hechuans.demo.security.indb.model.entity.User;

/**
 * @author : hechuansheng
 * @date : 2023/6/6 10:14
 * @description :
 * @since : version-1.0
 */
public interface UserService {

    User findUserByUserName(String userName);

}
