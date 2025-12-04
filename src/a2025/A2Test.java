package a2025;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class A2Test {
    static String s = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124";
    static String t = "4487-9581,755745207-755766099,954895848-955063124,4358832-4497315,15-47,1-12,9198808-9258771,657981-762275,6256098346-6256303872,142-282,13092529-13179528,96201296-96341879,19767340-19916378,2809036-2830862,335850-499986,172437-315144,764434-793133,910543-1082670,2142179-2279203,6649545-6713098,6464587849-6464677024,858399-904491,1328-4021,72798-159206,89777719-90005812,91891792-91938279,314-963,48-130,527903-594370,24240-60212";

    @Test
    void checkPattern() {
        var a2 = new A2();
        assertEquals(BigInteger.valueOf(1227775554), a2.sumInvalidIds(s));
    }

    @Test
    void checkPattern2() {
        var a2 = new A2();
        assertEquals(BigInteger.valueOf(1227775554), a2.sumInvalidIds(t));
    }


    @Test
    void checkOnePattern() {
        var a2 = new A2();
        var res = a2.invalidIdForRange(new A2.Range(11,22));
        assertEquals(2, res.size());
    }

}