package com.drmagdiamer.logAnnotation.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class EncoderHelper {
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String SECRET_KEY_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int KEY_LENGTH = 256;
    private static final int ITERATIONS = 65536;
    private static final String CHARSET = "UTF-8";

    // Sample key and salt (in a real application, generate these securely)
    private static final String SAMPLE_KEY = "sampleEncryptionKey123!";
    private static final String SAMPLE_SALT = "sampleSaltValue!@#";

    private EncoderHelper() {
    }

    // Encrypt a string
    public static String encrypt(String data) throws Exception {
        byte[] saltBytes = SAMPLE_SALT.getBytes(CHARSET);

        SecretKeySpec secretKey = generateSecretKey(SAMPLE_KEY, saltBytes);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        IvParameterSpec iv = generateIv();

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

        byte[] encryptedBytes = cipher.doFinal(data.getBytes(CHARSET));
        byte[] ivBytes = iv.getIV();

        return Base64.getEncoder().encodeToString(ivBytes) + ":" + Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decrypt a string
    public static String decrypt(String encryptedData) throws Exception {
        String[] parts = encryptedData.split(":");
        byte[] ivBytes = Base64.getDecoder().decode(parts[0]);
        byte[] encryptedBytes = Base64.getDecoder().decode(parts[1]);

        byte[] saltBytes = SAMPLE_SALT.getBytes(CHARSET);
        SecretKeySpec secretKey = generateSecretKey(SAMPLE_KEY, saltBytes);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        IvParameterSpec iv = new IvParameterSpec(ivBytes);

        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        return new String(decryptedBytes, CHARSET);
    }

    // Generate a SecretKeySpec from the given key and salt
    private static SecretKeySpec generateSecretKey(String key, byte[] salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(key.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(SECRET_KEY_ALGORITHM);
        byte[] secretKeyBytes = factory.generateSecret(spec).getEncoded();
        return new SecretKeySpec(secretKeyBytes, "AES");
    }

    // Generate a random IV (Initialization Vector)
    private static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        return new IvParameterSpec(iv);
    }
}
