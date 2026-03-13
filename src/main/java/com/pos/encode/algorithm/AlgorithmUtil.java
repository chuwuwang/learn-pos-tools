package com.pos.encode.algorithm;

public final class AlgorithmUtil {

    private AlgorithmUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static byte[] xor(final byte[] src, final byte[] des) {
        byte[] bytes = new byte[src.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (src[i] ^ des[i]);
        }
        return bytes;
    }

    public static byte xorBitwise(final byte[] data) {
        byte result = 0x00;
        for (byte b : data) {
            result ^= b;
        }
        return result;
    }

}