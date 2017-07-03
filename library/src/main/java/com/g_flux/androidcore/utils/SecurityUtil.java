package com.g_flux.androidcore.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public class SecurityUtil {

    private static byte[] hash(String message) {
        return hash(message.getBytes());
    }

    private static byte[] hash(byte[] message) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(message);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] trim(byte[] bytes, int length) {
        return Arrays.copyOfRange(bytes, 0, length);
    }

    private static byte[] pad(String message, int length) {
        byte[] messageBytes = message.getBytes();
        int paddingLength = (length - (messageBytes.length % 8)) % 8;
        return Arrays.copyOfRange(messageBytes, 0, messageBytes.length + paddingLength);
    }
}
