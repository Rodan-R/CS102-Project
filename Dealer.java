import java.util.ArrayList;
import java.util.List;

public class Dealer {
  
    private List<Card> hand;

    public Dealer() {
        this.hand = new ArrayList<>();
    }

    public List<Card> getHand() {
        return hand;
    }

    public void clearHand() {
        hand.clear();
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

    public boolean checkBust() {
        if (calculateValue() < 18) {
            return true;
        }
        return false;
    }
}