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

    public static String createJWT(String userJsonStr, List<String> auths) {
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
                .withClaim("user", userJsonStr)
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

    public static String getUserFromJWTToken(String jwtToken) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT decodedJWT = verifier.verify(jwtToken);
            return decodedJWT.getClaim("user").asString();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> getUserAuthFromJWTToken(String jwtToken) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT decodedJWT = verifier.verify(jwtToken);
            return decodedJWT.getClaim("auth").asList(String.class);
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
