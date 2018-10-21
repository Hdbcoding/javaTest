package blackjack;

public class BlackjackCard extends Card {

    public BlackjackCard(int value, Suit s) {
        super(value, s);
    }

    @Override
    public int value() {
        return Math.min(faceValue, 10);
    }
}
