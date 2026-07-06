package com.pramod.firstapi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void testAddition() {

        int result = 5 + 5;

        assertEquals(
                10,
                result);
    }
    @Test
    void testAssertions() {

        assertEquals(
                10,
                5 + 5);

        assertTrue(
                5 > 2);

        assertFalse(
                5 < 2);

        assertNotNull(
                "Pramod");
    }
}