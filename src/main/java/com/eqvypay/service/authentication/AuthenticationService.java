package com.eqvypay.service.authentication;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthenticationService {

    private static String hashAlgorithm = "SHA-256";

    public static String getHashedPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
        byte[] generatedHash = md.digest(password.getBytes(StandardCharsets.UTF_8));
        BigInteger bigInteger = new BigInteger(1, generatedHash);
        StringBuilder hex = new StringBuilder(bigInteger.toString(16));
        while (hex.length() < 32) {
            hex.insert(0, '0');
        }
        return hex.toString();
    }

}
