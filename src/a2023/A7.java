package a2023;

import java.util.*;

public class A7 {

    public static enum HandType {
        HighCard, OnePair, TwoPair, ThreeOfAKind,FullHouse, FourOfAKind, FiveOfAKind
    }

    public static class CardsAndScore {
        String cards;
        int score;
        HandType handType;
    }

    public static HandType detect(String cards) {
        Map<Character, Integer> count = new HashMap<>();
        for (var card : cards.toCharArray()) {
            count.putIfAbsent(card, 0);
            count.computeIfPresent(card, (a,b) -> b + 1);
        }
        Collection<Integer> values = count.values();
        var maxKey = values.stream().max(Integer::compare).get();
        if (values.contains(5)) return HandType.FiveOfAKind;
        if (values.contains(4)) return HandType.FourOfAKind;
        if (values.contains(3) && values.contains(2)) return HandType.FullHouse;
        if (values.contains(3)) return HandType.ThreeOfAKind;
        if (has2TimesTwo(values)) return HandType.TwoPair;
        if (values.contains(2)) return HandType.OnePair;

        return HandType.HighCard;
    }

    private static boolean has2TimesTwo(Collection<Integer> values) {
        int counter = 0;
        for (var val: values) {
            if (val == 2) counter++;
        }
        return counter == 2;
    }

    public static List<CardsAndScore> parse(String data) {
        var list = new ArrayList<CardsAndScore>();
        for (String l : data.split("\n")) {
            var lineArr = l.split(" ");
            var cardObj = new CardsAndScore();
            cardObj.cards = lineArr[0];
            cardObj.score = Integer.parseInt(lineArr[1]);
            list.add(cardObj);
        }
        return list;
    }
}
