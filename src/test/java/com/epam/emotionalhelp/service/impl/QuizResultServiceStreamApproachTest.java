package com.epam.emotionalhelp.service.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuizResultServiceStreamApproachTest {
    QuizResultServiceStreamApproach quizResultService
            = new QuizResultServiceStreamApproach(null, null, null);

    @Test
    public void getHexagonsQuantity() {
        assertEquals(0, quizResultService.getHexagonsQuantity(0));

        assertEquals(1, quizResultService.getHexagonsQuantity(1));
        assertEquals(1, quizResultService.getHexagonsQuantity(2));
        assertEquals(1, quizResultService.getHexagonsQuantity(3));

        assertEquals(2, quizResultService.getHexagonsQuantity(4));
        assertEquals(2, quizResultService.getHexagonsQuantity(5));
        assertEquals(2, quizResultService.getHexagonsQuantity(6));

        assertEquals(3, quizResultService.getHexagonsQuantity(7));
        assertEquals(3, quizResultService.getHexagonsQuantity(8));
        assertEquals(3, quizResultService.getHexagonsQuantity(9));

        assertEquals(4, quizResultService.getHexagonsQuantity(10));
        assertEquals(4, quizResultService.getHexagonsQuantity(11));
        assertEquals(4, quizResultService.getHexagonsQuantity(12));
    }
}