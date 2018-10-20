package blackjack;

public class BlackjackCard extends Card {

    public BlackjackCard(int value, Suit s) {
        super(value, s);
    }

    @Override
    public int value() {
        if (isFace()) return 10;
        return faceValue;
    }

    public boolean isAce() { return  faceValue == 1; }

    public boolean isFace() { return faceValue >= 11; }
}
