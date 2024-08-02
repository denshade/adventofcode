package a2023;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class A7Test {

    String s = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
            """;

    @Test
    void testParse() {
        assertEquals(5, A7.parse(s).size());
    }

    @Test
    void detectType() {
        assertEquals(A7.HandType.FiveOfAKind, A7.detect("AAAAA"));
        assertEquals(A7.HandType.FourOfAKind, A7.detect("BAAAA"));
        assertEquals(A7.HandType.ThreeOfAKind, A7.detect("B2AAA"));
        assertEquals(A7.HandType.TwoPair, A7.detect("BBAAC"));
        assertEquals(A7.HandType.OnePair, A7.detect("BBADC"));
        assertEquals(A7.HandType.HighCard, A7.detect("ABCDE"));
    }

}