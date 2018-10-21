package blackjack;

import java.util.ArrayList;

public class BlackjackHand extends Hand<BlackjackCard> {
    @Override
    public int score() {
        int score = 0;
        int aceCount = 0;

        for (int i = 0; i < cards.size(); i++) {
            var value = cards.get(i).value();
            score += value;
            if (value == 1) aceCount++;
        }

        while (aceCount > 0 && (score + 10) <= 21) {
            score += 10;
            aceCount--;
        }

        return score;
    }
}
