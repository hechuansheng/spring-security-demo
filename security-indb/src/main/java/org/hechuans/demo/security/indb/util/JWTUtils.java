package org.hechuans.demo.security.indb.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.*;

/**
 * @author : hechuansheng
 * @date : 2023/6/6 9:01
 * @description :
 * @since : version-1.0
 */
public class JWTUtils {

    private static final String ALG = "HS256";

    private static final String SECRET = "hechuans_secret";

    public static String createJWT(long userId, List<String> auths) {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("alg", ALG);
        headerMap.put("typ", "JWT");

        Date current = new Date();
        Date expDate = new Date(current.getTime() + 1000 * 60 * 60 * 6);

        return JWT.create()
                .withHeader(headerMap)
                .withIssuer("hechuans")
                .withIssuedAt(current)
                .withExpiresAt(expDate)
                .withClaim("userId", userId)
                .withClaim("auth", auths)
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static boolean verifyJWTToken(String jwtToken) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        try {
            verifier.verify(jwtToken);
            return true;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Long getUserIdFromJWTToken(String jwtToken) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT decodedJWT = null;
        try {
            decodedJWT = verifier.verify(jwtToken);
            return decodedJWT.getClaim("userId").asLong();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return -1L;
        }
    }
}
