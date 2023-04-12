package ru.dungeon.aimasters.backend;

import java.security.SecureRandom;
import java.util.Base64;

public class SecretKeyGenerator {

    public static String generateSecretKey(int length) {
        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[length];
        random.nextBytes(keyBytes);
        return Base64.getEncoder().encodeToString(keyBytes);
    }

    public static void main(String[] args) {
        String secretKey = generateSecretKey(32); // You can choose the length of the secret key
        System.out.println("Generated secret key: " + secretKey);
    }
}