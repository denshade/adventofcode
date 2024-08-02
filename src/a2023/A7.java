package a2023;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class A7 {

    public static BigInteger calc(List<CardsAndScore> parse) {
        BigInteger total = BigInteger.ZERO;
        var l = sort(parse);
        for (int i = 1; i <= l.size(); i++) {
            total = total.add(BigInteger.valueOf(l.get(i - 1).score * i));
        }
        return total;
    }

    public enum HandType {
        HighCard(7), OnePair(6), TwoPair(5), ThreeOfAKind(4), FullHouse(3), FourOfAKind(2), FiveOfAKind(1);
        final int order;
        HandType(int order) {
            this.order = order;
        }
    }
    public enum Card {
        cA(13), cK(12), cQ(11), cJ(10), cT(9), c9(8),c8(7),c7(6),c6(5),c5(4),c4(3),c3(2),c2(1);
        final int order;
        Card(int order) {
            this.order = order;
        }

        public int compare(Card o) {
            return Integer.compare(order, o.order);
        }
    }

    public static class CardsAndScore implements Comparable<CardsAndScore>{
        String cards;
        int score;
        HandType handType;

        @Override
        public int compareTo(CardsAndScore o) {
            if(o.handType == handType) {
                for (int i = 0; i < cards.length(); i++) {
                    if (cards.charAt(i) == o.cards.charAt(i)) continue;
                    var currentCard = Card.valueOf("c" + cards.charAt(i));
                    var otherCard = Card.valueOf("c" + o.cards.charAt(i));
                    return currentCard.compare(otherCard);
                }
            }
            return Integer.compare(o.handType.order, handType.order);
        }
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
            cardObj.handType = detect(cardObj.cards);
            list.add(cardObj);
        }
        return list;
    }
    public static List<CardsAndScore> sort(List<CardsAndScore> list) {
        return list.stream().sorted().collect(Collectors.toList());
    }
}
