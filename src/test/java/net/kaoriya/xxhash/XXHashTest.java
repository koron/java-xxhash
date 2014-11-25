package net.kaoriya.xxhash;

import org.junit.Test;
import static org.junit.Assert.*;

public class XXHashTest {

    public static final int PRIME = 0x9E3779B1;
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
        int prime = PRIME;
        for (int i = 0; i < SANITY_BUFFER_SIZE; ++i) {
            b[i] = (byte)(prime >> 24);
            prime *= prime;
        }
        return b;
    }

    @Test
    public void check32_0() {
        testSequence32(new byte[0], 0, 0x02CC5D05);
        testSequence32(new byte[0], PRIME, 0x36B78AE7);
    }

    @Test
    public void check32_sanity_1() {
        byte[] sanityBuffer = getSanityBuffer();
        testSequence32(sanityBuffer, 1, 0,     0xB85CBEE5);
        testSequence32(sanityBuffer, 1, PRIME, 0xD5845D64);
    }

    @Test
    public void check32_sanity_14() {
        byte[] sanityBuffer = getSanityBuffer();
        testSequence32(sanityBuffer, 14, 0,     0xE5AA0AB4);
        testSequence32(sanityBuffer, 14, PRIME, 0x4481951D);
    }

    @Test
    public void check32_sanity_all() {
        byte[] sanityBuffer = getSanityBuffer();
        testSequence32(sanityBuffer, 0,     0x1F1AA412);
        testSequence32(sanityBuffer, PRIME, 0x498EC8E2);
    }

}
