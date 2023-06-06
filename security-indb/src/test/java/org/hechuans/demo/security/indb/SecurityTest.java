package org.hechuans.demo.security.indb;

import lombok.extern.slf4j.Slf4j;
import org.hechuans.demo.security.indb.util.JWTUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @author : hechuansheng
 * @date : 2023/6/6 9:20
 * @description :
 * @since : version-1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityTest {

    @Test
    public void testJWT() {
        String jwtToken = JWTUtils.createJWT("{\"userid\":123456}", Arrays.asList("student", "teacher"));
        log.info("jwtToken: {}", jwtToken);

        Assert.assertTrue(JWTUtils.verifyJWTToken(jwtToken));

        String userJsonStr = JWTUtils.getUserFromJWTToken(jwtToken);

        log.info("userJsonStr: {}", userJsonStr);

    }



}
