package blackjack;

public enum Suit {
    Club(0), Diamond(1), Heart(2), Spade(4);

    private int value;

    Suit(int v){ value = v; }

    public int getValue() { return value; }

    public static Suit getSuitFromValue(int v)
    {
        switch (v){
            case 0: return Club;
            case 1: return Diamond;
            case 2: return Heart;
            case 4: return Spade;
        }
        return null;
    }
}
