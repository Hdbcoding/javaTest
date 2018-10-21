package test.blackjack;

import blackjack.BlackjackCard;
import blackjack.BlackjackHand;
import blackjack.Suit;
import org.junit.Test;

import static org.junit.Assert.*;
//Cracking the coding interview, 6th edition, problem 7.1 - Deck of Cards
public class Blackjack {
    @Test
    public void ScoreBasicBlackjack() {
        var hand = new BlackjackHand();
        hand.addCard(new BlackjackCard(12, Suit.Club));
        hand.addCard(new BlackjackCard(1, Suit.Club));

        var score = hand.score();
        assertEquals("Wrong score calculation", 21, score);
    }

    @Test
    public void TooManyAces() {
        var hand = new BlackjackHand();
        hand.addCard(new BlackjackCard(12, Suit.Club));
        hand.addCard(new BlackjackCard(1, Suit.Club));
        hand.addCard(new BlackjackCard(1, Suit.Heart));

        var score = hand.score();
        assertEquals("Wrong score calculation", 12, score);
    }

    @Test
    public void AceAce12() {
        var hand = new BlackjackHand();
        hand.addCard(new BlackjackCard(1, Suit.Club));
        hand.addCard(new BlackjackCard(1, Suit.Heart));

        var score = hand.score();
        assertEquals("Wrong score calculation", 12, score);
    }
}
