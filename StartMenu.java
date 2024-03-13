import java.util.Scanner;

public class StartMenu {

    public void display() {
        System.out.println("==== Start Menu ====");
        System.out.println("1. Play (Single Player)");
        System.out.println("2. Play (Multiplayer)");
        System.out.println("3. Read Rules");
        System.out.println("4. Quit Application");
        System.out.print("Please enter your choice:");
    }

    public void startOption() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            display();
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Please enter your name: ");
                    String name = sc.nextLine();
                    startGame(name);
                    break;
                case 2:
                    // System.out.print("Please enter your name: ");
                    // String name = sc.nextLine();
                    // startMultiplayerGame(name);
                    break;
                case 3:
                    displayRules();
                    break;
                case 4:
                    System.out.println("Thank you for playing, see you again!");
                    break;
                default:
                    System.out.println("Please enter a choice between 1 and 3.\n");
            }
        } while (choice != 1 && choice != 4);
        sc.close();
    }

    public void startGame(String name) {
        // Initialize player, dealer, and deck
        Player player = new Player(name, 100); // Initial chips: 100
        int roundNo = 1;
        Scanner scanner = new Scanner(System.in); 

        while (true) {
            // Start a new round
            Dealer dealer = new Dealer();
            Deck deck = new Deck();
            deck.shuffle();
            
            System.out.println("=== Round " + roundNo + " ===");
            System.out.print("Place your bet (Your chips: " + player.getChips() + "): ");
            int bet = scanner.nextInt();  // EXCEPTION FOR IF THEY BET LARGER THAN THEY CAN
            scanner.nextLine();

            GameFunction game = new GameFunction(player, dealer, deck);
            
            // Deal cards to the player and dealer
            game.distributeCards();

            if (game.player.checkStartingAceAce()) {
                System.out.println("Player's hand: " + player.getHand());
                System.out.println("Dealer's hand: " + dealer.getHand().get(0) + ", [Hidden Card]\n");

                System.out.println(player.getName() + " obtained Lucky Double Ace!");
                System.out.println(player.getName() + " have received double the amount they betted!");
                System.out.println(player.getChips() + " + 2 * " + bet + " = " + (player.getChips() + 2 * bet));
                player.addChips(2 * bet);

            } else if (game.player.checkStartingAceTen()) {
                System.out.println("Player's hand: " + player.getHand());
                System.out.println("Dealer's hand: " + dealer.getHand().get(0) + ", [Hidden Card]\n");

                System.out.println(player.getName() + " obtained Lucky Ace Ten!");
                System.out.println(player.getName() + " has received triple the amount they betted!");
                System.out.println(player.getChips() + " + 3 * " + bet + " = " + (player.getChips() + 3 * bet));
                player.addChips(3 * bet);

            } else if (game.dealer.checkStartingAceAce()) {
                System.out.println("Player's hand: " + player.getHand());
                System.out.println("Dealer's hand: " + dealer.getHand().get(0) + ", [Hidden Card]\n");

                try {
                    Thread.sleep(200);
                    System.out.println("Wait");
                    Thread.sleep(300);
                    System.out.print(".");
                    Thread.sleep(300);
                    System.out.print(".");
                    Thread.sleep(300);
                    System.out.print(".");
                    Thread.sleep(300);

                    System.out.println("Dealer's hand: " + dealer.getHand());
                    Thread.sleep(300);
                    System.out.println("Dealer obtained Lucky Double Ace!");
                    Thread.sleep(300);
                    System.out.println(player.getName() + " gives dealer double the amount they betted!");
                    Thread.sleep(300);
                    System.out.println(player.getChips() + " - 2 * " + bet + " = " + (player.getChips() - 2 * bet));
                    player.subtractChips(2 * bet);
                } catch (InterruptedException e) {
                    // Handle the InterruptedException
                    e.printStackTrace();
                }
            } else if (game.dealer.checkStartingAceTen()) {
                System.out.println("Player's hand: " + player.getHand());
                System.out.println("Dealer's hand: " + dealer.getHand().get(0) + ", [Hidden Card]\n");

                try {
                    Thread.sleep(900);
                    System.out.print("Wait");
                    Thread.sleep(900);
                    System.out.print(".");
                    Thread.sleep(900);
                    System.out.print(".");
                    Thread.sleep(900);
                    System.out.println(".\n");
                    Thread.sleep(900);

                    System.out.println("Dealer's hand: " + dealer.getHand());
                    Thread.sleep(1000);
                    System.out.println("Dealer obtained Lucky Ace Ten!");
                    Thread.sleep(1000);
                    System.out.println(player.getName() + " gives dealer triple the amount they betted!");
                    Thread.sleep(1000);
                    System.out.println(player.getChips() + " - 3 * " + bet + " = " + (player.getChips() - 3 * bet));
                    player.subtractChips(3 * bet);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // Handle the InterruptedException
                    e.printStackTrace();
                }
            } else {
        
                while(true) {
                    // Display initial hands
                    System.out.println("Player's hand: " + game.player.getHand());
                    System.out.print("Dealer's hand: " + game.dealer.getHand().get(0));
                    for (int i = 1; i < dealer.getHand().size(); i++) {
                        System.out.print(", [Hidden Card]");
                    }
                    System.out.println();
            
                    System.out.print("Would you like to hit or stand? (h/s): ");
                    String action = scanner.nextLine();
                    if (action.equals("s")) {
                        if (game.player.calculateValue() < 18) {
                            System.out.println("Your total value is " + game.player.calculateValue() + ", lower than 18");
                            System.out.print("Are you sure you wish to stand? (h/s): ");
                            String action2 = scanner.nextLine();
                            if (action2.equals("s")) {
                                break;
                            } else if (action2.equals("h")) {
                                game.playerHit();
                                if (player.checkBust()) {
                                    break;
                                }
                            }
                        } else {
                            break;
                        }
                    } else if (action.equals("h")) {
                        game.playerHit();
                        if (player.checkBust()) {
                            break;
                        }
                        if (game.player.getHand().size() == 4) {
                            System.out.println("SPECIAL");
                            game.dragon();
                        }
                    }

                    game.dealerTurn();
                }

                while (dealer.calculateValue() < 18) {
                    game.dealerTurn();
                }

                int outcome = game.determineWin();
                // Return -2 when player busts
                // Return -1 when dealer wins
                // Return 0 when it's a draw
                // Return 1 when player wins
                // Return 2 when dealer busts
                if (outcome == -2) {
                    System.out.println("Player's hand: " + player.getHand());
                    System.out.print("Dealer's hand: " + game.dealer.getHand().get(0));
                    for (int i = 1; i < dealer.getHand().size(); i++) {
                        System.out.print(", [Hidden Card]");
                    }
                    System.out.println();

                    System.out.println("\nOh no! " + player.getName() + " Busted!");
                    System.out.println(player.getName() + " loses!");
                    System.out.println(player.getChips() + " - " + bet + " = " + (player.getChips() - bet) + "\n");
                    player.subtractChips(bet);
                } else if (outcome == -1) {
                    System.out.println("Player's hand: " + player.getHand());
                    System.out.print("Dealer's hand: " + game.dealer.getHand().get(0));
                    for (int i = 1; i < dealer.getHand().size(); i++) {
                        System.out.print(", [Hidden Card]");
                    }
                    System.out.println();

                    System.out.println("\nThe dealer busted!");
                    System.out.println(player.getName() + " wins!");
                    System.out.println(player.getChips() + " + " + bet + " = " + (player.getChips() + bet) + "\n");
                    player.addChips(bet);
                } else if (outcome == 0) {
                    System.out.println("Player's hand: " + player.getHand());
                    System.out.print("Dealer's hand: " + game.dealer.getHand().get(0));
                    for (int i = 1; i < dealer.getHand().size(); i++) {
                        System.out.print(", [Hidden Card]");
                    }
                    System.out.println();

                    System.out.println("\nPlayer value: " + player.calculateValue());
                    System.out.println("Dealer value: " + dealer.calculateValue());

                    System.out.println("\nIt's a draw!");
                    System.out.println("Number of chips remains the same: " + player.getChips() + "\n");
                } else if (outcome == 1) {
                    System.out.println("Player's hand: " + player.getHand());
                    System.out.print("Dealer's hand: " + game.dealer.getHand().get(0));
                    for (int i = 1; i < dealer.getHand().size(); i++) {
                        System.out.print(", [Hidden Card]");
                    }
                    System.out.println();

                    System.out.println("\nPlayer value: " + player.calculateValue());
                    System.out.println("Dealer value: " + dealer.calculateValue());

                    System.out.println("\n" + player.getName() + " BlackJacks!");
                    System.out.println(player.getName() + " won " + bet + " chips!");
                    System.out.println(player.getChips() + " + " + bet + " = " + (player.getChips() + bet) + "\n");
                    player.addChips(bet);
                } else if (outcome == 2) {
                    System.out.println("Player's hand: " + player.getHand());
                    System.out.print("Dealer's hand: " + game.dealer.getHand().get(0));
                    for (int i = 1; i < dealer.getHand().size(); i++) {
                        System.out.print(", [Hidden Card]");
                    }
                    System.out.println();

                    System.out.println("\nPlayer value: " + player.calculateValue());
                    System.out.println("Dealer value: " + dealer.calculateValue());

                    System.out.println("\nThe dealer BlackJacks!");
                    System.out.println(player.getName() + " lost " + bet + " chips :(");
                    System.out.println(player.getChips() + " - " + bet + " = " + (player.getChips() - bet) + "\n");
                    player.subtractChips(bet);
                }

                if (player.getChips() > player.getHighScore()) {
                    player.setHighScore(player.getChips());
                }
            
                game.player.clearHand();
                game.dealer.clearHand();
            }
        
            // Check if the player has run out of chips
            if (player.getChips() <= 0) {
                System.out.println("\nYou've run out of chips. Game over!");
                System.out.println("Your highest score: " + player.getHighScore());
                System.out.println("Thank you for playing!");
                break;
            }
    
            // Check if the player wants to play another round
            System.out.print("Would you like to play another round? (yes/no): ");
            String playAgain = scanner.nextLine();
            System.out.println("");    // EXCEPTION FOR IF THEY PRINT SOMETHING WEIRD
            if (playAgain.equals("no")) {
                System.out.println("Your highest score: " + player.getHighScore());
                System.out.println("Final number of chips: " + player.getChips());
                System.out.println("Thank you for playing!");
                break;
            }
            roundNo++;
        }
        scanner.close(); // Close the scanner at the end
    }

    public void displayRules() {
        try {
            Thread.sleep(300);
            System.out.print("\nPreparing Rules.");
            Thread.sleep(400);
            System.out.print(".");
            Thread.sleep(400);
            System.out.println(".\n");
            Thread.sleep(600);
            System.out.println("==== RULES ====");
            Thread.sleep(200);
            System.out.println("1. Blah Blah Blah");
            Thread.sleep(200);
            System.out.println("2. Blah Blah Blah");
            Thread.sleep(200);
            System.out.println("3. Blah Blah Blah");
            Thread.sleep(200);
            System.out.println("4. Blah Blah Blah");
            Thread.sleep(200);
            System.out.println("5. Blah Blah Blah");
            Thread.sleep(200);
            System.out.println("6. Blah Blah Blah");
            Thread.sleep(200);
            System.out.println("7. Blah Blah Blah");
            Thread.sleep(200);
            System.out.println("8. Blah Blah Blah");
            Thread.sleep(300);

            Scanner sc = new Scanner(System.in);
            System.out.print("Press Enter to go back to Start Menu: ");
            sc.nextLine();
            sc.close();
            System.out.println("\n");
        } catch (InterruptedException e) {
            // Handle the InterruptedException
            e.printStackTrace();
        }
    }
}