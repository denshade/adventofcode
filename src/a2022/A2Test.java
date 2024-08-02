package a2022;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class A2Test
{
    @Test
    void checkOutcome()
    {
        assertEquals(A2.Outcome.Draw, A2.calculateOutcome(A2.Play.Paper, A2.Play.Paper));
        assertEquals(A2.Outcome.Draw, A2.calculateOutcome(A2.Play.Rock, A2.Play.Rock));
        assertEquals(A2.Outcome.Draw, A2.calculateOutcome(A2.Play.Scissor, A2.Play.Scissor));
        assertEquals(A2.Outcome.Win, A2.calculateOutcome(A2.Play.Paper, A2.Play.Rock));
        assertEquals(A2.Outcome.Win, A2.calculateOutcome(A2.Play.Rock, A2.Play.Scissor));
        assertEquals(A2.Outcome.Win, A2.calculateOutcome(A2.Play.Scissor, A2.Play.Paper));
        assertEquals(A2.Outcome.Lose, A2.calculateOutcome(A2.Play.Rock, A2.Play.Paper));
        assertEquals(A2.Outcome.Lose, A2.calculateOutcome(A2.Play.Scissor, A2.Play.Rock));
        assertEquals(A2.Outcome.Lose, A2.calculateOutcome(A2.Play.Paper, A2.Play.Scissor));
    }

    @Test
    void checkScoreText()
    {
        assertEquals(4, A2.calculateScore("A", "Y"));
        assertEquals(1, A2.calculateScore("B", "X"));
        assertEquals(7, A2.calculateScore("C", "Z"));
    }

    @Test
    void checkScoreTextFile() throws IOException {
        assertEquals(12, A2.calculate(new File("strategies.txt")));
    }

    @Test
    void checkScoreTextFileAct() throws IOException {
        assertEquals(13193, A2.calculate(new File("strategies_act.txt")));
    }

}