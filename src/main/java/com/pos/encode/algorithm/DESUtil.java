package com.pos.encode.algorithm;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Provider;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;

public final class DESUtil {

    /**
     * BC support CBC, CFB, CTR, CTS, ECB, OFB, padding support ISO10126d2Padding, ZeroBytePadding, P KCS7Padding, ISO7816d4Padding, X923Padding, TBCPadding
     */
    private DESUtil() {
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

    public static byte[] encryptCFB(final byte[] key, final byte[] data, final byte[] iv, final String padding) {
        return encrypt(key, data, iv, Algorithm.CFB, padding);
    }

    public static byte[] decryptCFB(final byte[] key, final byte[] data, final byte[] iv, final String padding) {
        return decrypt(key, data, iv, Algorithm.CFB, padding);
    }

    public static byte[] encryptOFB(final byte[] key, final byte[] data, final byte[] iv, final String padding) {
        return encrypt(key, data, iv, Algorithm.OFB, padding);
    }

    public static byte[] decryptOFB(final byte[] key, final byte[] data, final byte[] iv, final String padding) {
        return decrypt(key, data, iv, Algorithm.OFB, padding);
    }

    public static byte[] encrypt(final byte[] key, final byte[] data, final byte[] iv, final String mode, final String padding) {
        return symmetricAlgorithm(key, data, iv, mode, padding, true);
    }

    public static byte[] decrypt(final byte[] key, final byte[] data, final byte[] iv, final String mode, final String padding) {
        return symmetricAlgorithm(key, data, iv, mode, padding, false);
    }

    public static byte[] symmetricAlgorithm(final byte[] key, final byte[] data, final byte[] iv, final String mode, final String padding, final boolean encrypt) {
        try {
            Algorithm.initBC();
            Provider provider = Security.getProvider("BC");
            SecretKey secretKey = new SecretKeySpec(key, Algorithm.DES);
            Cipher cipher = Cipher.getInstance(Algorithm.DES + "/" + mode + "/" + padding, provider);
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