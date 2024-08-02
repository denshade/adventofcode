package a2022;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class A2
{
    enum Outcome {
        Win(6), Lose(0), Draw(3);
        Outcome(int score) {
            this.score = score;
        }
        private int score;

        public int getScore() {
            return score;
        }
    }
    enum Play {
        Paper(2), Scissor(3), Rock(1);
        Play(int score) {
            this.score = score;
        }
        private int score;

        public int getScore() {
            return score;
        }
    }
    public static Outcome calculateOutcome(Play yourPlay, Play theirPlay) {
        if (yourPlay == theirPlay)
            return Outcome.Draw;
        return switch (yourPlay) {
            case Rock -> theirPlay == Play.Scissor ? Outcome.Win : Outcome.Lose;
            case Paper -> theirPlay == Play.Rock ? Outcome.Win : Outcome.Lose;
            case Scissor -> theirPlay == Play.Paper ? Outcome.Win : Outcome.Lose;
        };
    }

    public static int calculateScore(Play yourPlay, Outcome expectedOutcome)
    {
        return yourPlay.getScore() + expectedOutcome.score;
    }

    public static Outcome convertToOutcome(String ch)
    {
        switch (ch) {
            case "X":
                return Outcome.Lose;
            case "Y":
                return Outcome.Draw;
            case "Z":
                return Outcome.Win;
        }
        throw new IllegalArgumentException("Unknown "+ ch);

    }
    public static Play convertToPlay(String ch)
    {
        switch (ch) {
            case "A":
                return Play.Rock;
            case "B":
                return Play.Paper;
            case "C":
                return Play.Scissor;
        }
        throw new IllegalArgumentException("Unknown "+ ch);
    }
    public static Play getExpectedPlay(Play theirPlay, Outcome outcome)
    {
        for (Play p: Play.values()) {
            if (calculateOutcome(p, theirPlay) == outcome) return p;
        }
        throw new IllegalArgumentException("No expectedPlay");
    }
    public static long calculate(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        return lines.stream().mapToInt(A2::calculateScore).sum();
    }
    public static int calculateScore(String line)
    {
        String[] parts = line.split(" ");
        return calculateScore(parts[0], parts[1]);
    }
    public static int calculateScore(String theirPlay, String expectedOutcome)
    {
        return calculateScore(getExpectedPlay(convertToPlay(theirPlay), convertToOutcome(expectedOutcome)), convertToOutcome(expectedOutcome));
    }
}
