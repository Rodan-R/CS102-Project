public class Card {
    private String suit;
    private String value;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    public int calculateValue() {
        if (value.equals("Jack") || value.equals("Queen") || value.equals("King")) {
            return 10;
        } else if (value.equals("Ace")) {
            return 1;
        } else {
            return Integer.parseInt(value);
        }
    }

    public String toString() {
        return suit + " of " + value;
    }
}