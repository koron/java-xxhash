package net.kaoriya.xxhash;

public class XXHash {

    public static int PRIME32_1 = 0x9e3779b1;
    public static int PRIME32_2 = 0x85ebca77;
    public static int PRIME32_3 = 0xc2b2ae3d;
    public static int PRIME32_4 =  668265263;
    public static int PRIME32_5 =  374761393;

    public static int hash32(byte[] b, int off, int len, int seed) {
        int h32 = len;
        int p = off;
        int end = off + len;

        if (len >= 16) {
            int v1 = seed + PRIME32_1 + PRIME32_2;
            int v2 = seed + PRIME32_2;
            int v3 = seed + 0;
            int v4 = seed - PRIME32_1;

            for (int limit = end - 16; p < limit; ) {
                v1 += get32bits(b, p) * PRIME32_2;
                v1 = rotl32(v1, 13);
                v1 *= PRIME32_1;
                p += 4;
                v2 += get32bits(b, p) * PRIME32_2;
                v2 = rotl32(v2, 13);
                v2 *= PRIME32_1;
                p += 4;
                v3 += get32bits(b, p) * PRIME32_2;
                v3 = rotl32(v3, 13);
                v3 *= PRIME32_1;
                p += 4;
                v4 += get32bits(b, p) * PRIME32_2;
                v4 = rotl32(v4, 13);
                v4 *= PRIME32_1;
                p += 4;
            }

            h32 += rotl32(v1, 1) + rotl32(v2, 7) + rotl32(v3, 12)
                + rotl32(v4, 18);
        } else {
            h32 += seed + PRIME32_5;
        }

        while (p + 4 <= end) {
            h32 += get32bits(b, p) * PRIME32_3;
            h32 = rotl32(h32, 17) * PRIME32_4;
            p += 4;
        }

        while (p < end) {
            h32 += ((int)b[p] & 0xff) * PRIME32_5;
            h32 = rotl32(h32, 11) * PRIME32_1;
            p += 1;
        }

        h32 ^= h32 >>> 15;
        h32 *= PRIME32_2;
        h32 ^= h32 >>> 13;
        h32 *= PRIME32_3;
        h32 ^= h32 >>> 16;

        return h32;
    }


    public static int rotl32(int v, int r) {
        return (v << r) | (v >>> (32 - r));
    }

    public static int get32bits(byte[] b, int off) {
        return (((int)b[off] & 0xff) << 24)
            | (((int)b[off + 1] & 0xff) << 16)
            | (((int)b[off + 2] & 0xff) << 8)
            | ((int)b[off + 3] & 0xff);
    }

    /*
    public static int PRIME64_1 = 11400714785074694791L;
    public static int PRIME64_2 = 14029467366897019727L;
    public static int PRIME64_3 =  1609587929392839161L;
    public static int PRIME64_4 =  9650029242287828579L;
    public static int PRIME64_5 =  2870177450012600261L;
    */

    public static long xxh64(byte[] b, int off, int len, long seed) {
        // TODO:
        return 0L;
    }

}
