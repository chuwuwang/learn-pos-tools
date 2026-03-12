package com.pos.encode.algorithm.jdk;

import com.pos.encode.algorithm.Algorithm;

import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class JdkTripleDESUtil {

    /**
     * javax.crypto.Cipher
     * java.security.MessageDigest
     * JDK support CBC, CFB, CTR, CTS, ECB, OFB, padding support ISO10126Padding, NoPadding, PKCS5Padding
     */
    private JdkTripleDESUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static byte[] encryptECB(final byte[] key, final byte[] data, final String padding) {
        return encrypt(key, data, null, Algorithm.ECB, padding);
    }

    public static byte[] decryptECB(final byte[] key, final byte[] data, final String padding) {
        return decrypt(key, data, null, Algorithm.ECB, padding);
    }

    public static byte[] encryptCBC(final byte[] key, final byte[] data, final byte[] iv, final String padding) {
        return encrypt(key, data, iv, Algorithm.CBC, padding);
    }

    public static byte[] decryptCBC(final byte[] key, final byte[] data, final byte[] iv, final String padding) {
        return decrypt(key, data, iv, Algorithm.CBC, padding);
    }

    public static byte[] encrypt(final byte[] key, final byte[] data, final byte[] iv, final String mode, final String padding) {
        return symmetricAlgorithm(key, data, iv, mode, padding, true);
    }

    public static byte[] decrypt(final byte[] key, final byte[] data, final byte[] iv, final String mode, final String padding) {
        return symmetricAlgorithm(key, data, iv, mode, padding, false);
    }

    public static byte[] symmetricAlgorithm(final byte[] key, final byte[] data, final byte[] iv, final String mode, final String padding, final boolean encrypt) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, Algorithm.DES_EDE);
            Cipher cipher = Cipher.getInstance(Algorithm.DES_EDE + "/" + mode + "/" + padding);
            if (iv == null || iv.length == 0) {
                cipher.init(encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, secretKey);
            } else {
                AlgorithmParameterSpec params = new IvParameterSpec(iv);
                cipher.init(encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, secretKey, params);
            }
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}