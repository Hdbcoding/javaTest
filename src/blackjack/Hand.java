package blackjack;

import java.util.ArrayList;

public abstract class Hand <T extends Card> {
    protected ArrayList<T> cards = new ArrayList<>();

    public abstract int score();
    public void addCard(T card){
        cards.add(card);
    }
}
