package com.pos.encode.algorithm;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.*;

public final class SHAUtil {

    /**
     * BC support SHA1, SHA224, SHA256, SHA384, SHA512
     */
    private SHAUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static byte[] sha1(final byte[] bytes) {
        SHA1Digest digest = new SHA1Digest();
        return hashCalculator(bytes, digest);
    }

    public static byte[] sha224(final byte[] bytes) {
        SHA224Digest digest = new SHA224Digest();
        return hashCalculator(bytes, digest);
    }

    public static byte[] sha256(final byte[] bytes) {
        SHA256Digest digest = new SHA256Digest();
        return hashCalculator(bytes, digest);
    }

    public static byte[] sha384(final byte[] bytes) {
        SHA384Digest digest = new SHA384Digest();
        return hashCalculator(bytes, digest);
    }

    public static byte[] sha512(final byte[] bytes) {
        SHA512Digest digest = new SHA512Digest();
        return hashCalculator(bytes, digest);
    }

    public static byte[] hashCalculator(final byte[] bytes, final Digest digest) {
        try {
            digest.update(bytes, 0, bytes.length);
            int digestSize = digest.getDigestSize();
            byte[] dataBytes = new byte[digestSize];
            digest.doFinal(dataBytes, 0);
            return dataBytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}