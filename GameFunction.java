public class GameFunction {
    public Player player;
    public Dealer dealer;
    public Deck deck;

    public GameFunction(Player player, Dealer dealer, Deck deck) {
        this.player = player;
        this.dealer = dealer;
        this.deck = deck;
    }

    public void distributeCards() {
        player.addCardToHand(deck.dealCard());
        player.addCardToHand(deck.dealCard());
        dealer.addCardToHand(deck.dealCard());
        dealer.addCardToHand(deck.dealCard());
    }

    public void playerHit() {
        System.out.println("You are hitting\n");
        player.addCardToHand(deck.dealCard());
    }

    public void dealerTurn() {
        System.out.println("Dealer is thinking...");
        if (dealer.calculateValue() < 18) {
            System.out.println("Dealer chooses to hit\n");
            dealer.addCardToHand(deck.dealCard());
        } else {
            System.out.println("Dealer chooses to stand\n");
        }
    }

    public int determineWin() {
        // Return -2 when player busts
        // Return -1 when dealer wins
        // Return 0 when it's a draw
        // Return 1 when player wins
        // Return 2 when dealer busts

        if (player.checkBust()) {
            // Player busts
            return -2;
        } else if (dealer.checkBust()) {
            // Dealer busts
            return 2;
        } else if (player.calculateValue() < dealer.calculateValue()) {
            // Dealer wins
            return -1;
        } else if (player.calculateValue() > dealer.calculateValue()) {
            // Player wins
            return 1;
        } else {
            // Draw
            return 0;
        }
    }

    public void dragon() {
        System.out.println("Yo you can dragon");
    }
}
