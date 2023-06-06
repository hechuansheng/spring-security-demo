package org.hechuans.demo.security.inmemory.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

/**
 * @author : hechuansheng
 * @date : 2023/6/5 16:20
 * @description :
 * @since : version-1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityTest {

    @Test
    public void testBCrypt() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String encode = encoder.encode("123456");

        log.info("encode: {}" , encode);

        assertTrue(encoder.matches("1234561", encode));
    }
}
