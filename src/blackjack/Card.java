package blackjack;

public abstract class Card {
    protected int faceValue;
    protected Suit suit;

    public Card(int value, Suit s){
        faceValue = value;
        suit = s;
    }

    public abstract int value();

}
