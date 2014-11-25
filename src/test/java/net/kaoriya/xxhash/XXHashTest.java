package net.kaoriya.xxhash;

import org.junit.Test;
import static org.junit.Assert.*;

public class XXHashTest {

    public static final int PRIME32 = 0x9E3779B1;
    public static final long PRIME64 = 0x000000009E3779B1L;
    public static final int SANITY_BUFFER_SIZE = 101;

    private void testSequence32(byte[] b, int len, int seed, int expected) {
        int actual = XXHash.hash32(b, 0, len, seed);
        assertEquals(expected, actual);
    }

    private void testSequence32(byte[] b, int seed, int expected) {
        testSequence32(b, b.length, seed, expected);
    }

    private byte[] getSanityBuffer() {
        byte[] b = new byte[SANITY_BUFFER_SIZE];
        int prime = PRIME32;
        for (int i = 0; i < SANITY_BUFFER_SIZE; ++i) {
            b[i] = (byte)(prime >> 24);
            prime *= prime;
        }
        return b;
    }

    @Test
    public void check32_0() {
        testSequence32(new byte[0], 0,       0x02CC5D05);
        testSequence32(new byte[0], PRIME32, 0x36B78AE7);
    }

    @Test
    public void check32_sanity_1() {
        byte[] sanityBuffer = getSanityBuffer();
        testSequence32(sanityBuffer, 1, 0,       0xB85CBEE5);
        testSequence32(sanityBuffer, 1, PRIME32, 0xD5845D64);
    }

    @Test
    public void check32_sanity_14() {
        byte[] sanityBuffer = getSanityBuffer();
        testSequence32(sanityBuffer, 14, 0,       0xE5AA0AB4);
        testSequence32(sanityBuffer, 14, PRIME32, 0x4481951D);
    }

    @Test
    public void check32_sanity_all() {
        byte[] sanityBuffer = getSanityBuffer();
        testSequence32(sanityBuffer, 0,       0x1F1AA412);
        testSequence32(sanityBuffer, PRIME32, 0x498EC8E2);
    }

    private void testSequence64(byte[] b, int len, long seed, long expected) {
        long actual = XXHash.hash64(b, 0, len, seed);
        assertEquals(expected, actual);
    }

    private void testSequence64(byte[] b, long seed, long expected) {
        testSequence64(b, b.length, seed, expected);
    }

    @Test
    public void check64_0() {
        testSequence64(new byte[0], 0,       0xEF46DB3751D8E999L);
        testSequence64(new byte[0], PRIME64, 0xAC75FDA2929B17EFL);
    }

    @Test
    public void check64_sanity_1() {
        byte[] sanityBuffer = getSanityBuffer();
        testSequence64(sanityBuffer, 1, 0,       0x4FCE394CC88952D8L);
        testSequence64(sanityBuffer, 1, PRIME64, 0x739840CB819FA723L);
    }

    @Test
    public void check64_sanity_14() {
        byte[] sanityBuffer = getSanityBuffer();
        testSequence64(sanityBuffer, 14, 0,       0xCFFA8DB881BC3A3DL);
        testSequence64(sanityBuffer, 14, PRIME64, 0x5B9611585EFCC9CBL);
    }

    @Test
    public void check64_sanity_all() {
        byte[] sanityBuffer = getSanityBuffer();
        testSequence64(sanityBuffer, 0,       0x0EAB543384F878ADL);
        testSequence64(sanityBuffer, PRIME64, 0xCAA65939306F1E21L);
    }

}
