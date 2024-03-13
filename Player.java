import java.util.ArrayList;
import java.util.List;

public class Player {
  
    private String name;
    private int chips;
    private List<Card> hand;
    private int highScore;

    public Player(String name, int chips) {
        this.name = name;
        this.chips = chips;
        this.hand = new ArrayList<>();
        this.highScore = 100;
    }

    public String getName() {
        return name;
    }

    public int getChips() {
        return chips;
    }

    public void addChips(int amount) {
        this.chips += amount;
    }

    public void subtractChips(int amount) {
        if (amount > chips) {
            chips = 0;
        }
        this.chips -= amount;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void clearHand() {
        hand.clear();
    }

    public void setHighScore(int highestChipSoFar) {
        this.highScore = highestChipSoFar;
    }

    public int getHighScore() {
        return highScore;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public int calculateValue() {
        int total = 0;
        for (Card card : hand) {
            if (card.getValue().equals("Jack") || card.getValue().equals("Queen") || card.getValue().equals("King")) {
                total += 10;
            } else if (card.getValue().equals("Ace")) {
                total += 1;
            } else {
                total += Integer.parseInt(card.getValue());
            }
        }
        return total;
    }

    public boolean checkBust() {
        if (calculateValue() > 21) {
            return true;
        }
        return false;
    }

    public String toString() {
        return name + " - Chips: " + chips;
    }

    public boolean checkStartingAceAce() {
        Card firstCard = hand.get(0);
        Card secondCard = hand.get(1);
        if (firstCard.getValue().equals("Ace") && secondCard.getValue().equals("Ace")) {
            return true;
        }
        return false;
    }

    public boolean checkStartingAceTen() {
        Card firstCard = hand.get(0);
        Card secondCard = hand.get(1);
        if ((firstCard.calculateValue() == 1 && secondCard.calculateValue() == 10)
            || (firstCard.calculateValue() == 10 && secondCard.calculateValue() == 1)) {
            return true;
        }
        return false;
    }

}