/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * SYST 17796 Project
 * @date December 2025
 * @author Dhrumil Rana
 * @author Nisha Rana
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

public class UNOGame extends Game {

    private UNODeck deck;
    private UNODiscardPile discardPile;

    private boolean directionClockwise = true;

    private int currentPlayerIndex = 0;

    private final int targetScore = 500;

    private int extraAdvanceSteps = 0;

    public UNOGame(ArrayList<Player> players) {
        super("UNO");
        setPlayers(players);
    }

    @Override
    public void play() {
        Scanner input = new Scanner(System.in);
        System.out.println("=== Welcome to UNO ===");

        while (findGameWinner() == null) {
            playRound(input);
            UNOPlayer winner = findGameWinner();
            if (winner != null) {
                break;
            }
            System.out.println("\nNo player has reached " + targetScore
                    + " points yet. A new round will begin...");
        }

        declareWinner();
    }

    public void playRound(Scanner input) {

        deck = new UNODeck();
        discardPile = new UNODiscardPile();
        directionClockwise = true;
        currentPlayerIndex = 0;

        for (Player p : getPlayers()) {
            UNOPlayer up = (UNOPlayer) p;
            up.clearHand();
        }

        for (int i = 0; i < 7; i++) {
            for (Player p : getPlayers()) {
                UNOPlayer up = (UNOPlayer) p;
                UNOCard drawn = deck.draw();
                if (drawn != null) {
                    up.receiveCard(drawn);
                }
            }
        }

        UNOCard firstCard = deck.draw();
        discardPile.addCard(firstCard);
        System.out.println("\n--- New Round Started ---");
        System.out.println("Starting card on discard pile: " + firstCard);

        boolean roundOver = false;

        while (!roundOver) {
            extraAdvanceSteps = 0;

            UNOPlayer currentPlayer = (UNOPlayer) getPlayers().get(currentPlayerIndex);
            UNOCard topCard = discardPile.getTopCard();

            System.out.println("\n========================================");
            System.out.println("It is " + currentPlayer.getName() + "'s turn.");
            System.out.println("Current direction: "
                    + (directionClockwise ? "Clockwise" : "Counter-clockwise"));

            int choice = currentPlayer.chooseCardIndexToPlay(topCard, input);

            if (choice == -1) {
                UNOCard drawn = deck.draw();
                if (drawn == null) {
                    System.out.println("Deck is empty! Round ends in a draw.");
                    roundOver = true;
                } else {
                    System.out.println(currentPlayer.getName() + " draws: " + drawn);
                    currentPlayer.receiveCard(drawn);
                }
            } else {
                UNOCard played = currentPlayer.removeCardAt(choice);
                System.out.println(currentPlayer.getName() + " plays: " + played);
                discardPile.addCard(played);

                applyCardEffect(played, currentPlayer, input);

                if (currentPlayer.hasEmptyHand()) {
                    System.out.println("\n*** " + currentPlayer.getName()
                            + " has played all their cards! ***");
                    calculateRoundScores(currentPlayer);
                    roundOver = true;
                }
            }

            if (!roundOver) {
                currentPlayerIndex = advanceIndex(1 + extraAdvanceSteps);
            }
        }

        System.out.println("\nScores after this round:");
        for (Player p : getPlayers()) {
            UNOPlayer up = (UNOPlayer) p;
            System.out.println("  " + up.getName() + ": " + up.getTotalScore());
        }
    }

    private void applyCardEffect(UNOCard card, UNOPlayer currentPlayer, Scanner input) {
        UNOType type = card.getType();

        switch (type) {
            case SKIP:
                System.out.println("Effect: Next player is skipped!");
                extraAdvanceSteps = 1; 
                break;

            case REVERSE:
                System.out.println("Effect: Play direction is reversed!");
                directionClockwise = !directionClockwise;
                break;

            case DRAW_TWO: {
                System.out.println("Effect: Next player draws two cards and is skipped!");
                int targetIndex = advanceIndex(1);
                UNOPlayer target = (UNOPlayer) getPlayers().get(targetIndex);
                for (int i = 0; i < 2; i++) {
                    UNOCard drawn = deck.draw();
                    if (drawn != null) {
                        target.receiveCard(drawn);
                    }
                }
                extraAdvanceSteps = 1; 
                break;
            }

            case WILD:
                System.out.println("Effect: WILD card played.");
                UNOColor chosenColor = askForColorChoice(currentPlayer, input);
                System.out.println(currentPlayer.getName() + " chooses colour: " + chosenColor);
                break;

            case WILD_DRAW_FOUR: {
                System.out.println("Effect: WILD DRAW FOUR!");
                UNOColor chosen = askForColorChoice(currentPlayer, input);
                System.out.println(currentPlayer.getName() + " chooses colour: " + chosen);
                int targetIndex = advanceIndex(1);
                UNOPlayer target = (UNOPlayer) getPlayers().get(targetIndex);
                for (int i = 0; i < 4; i++) {
                    UNOCard drawn = deck.draw();
                    if (drawn != null) {
                        target.receiveCard(drawn);
                    }
                }
                System.out.println(target.getName() + " draws 4 cards and is skipped.");
                extraAdvanceSteps = 1;
                break;
            }

            default:
                break;
        }
    }

    private UNOColor askForColorChoice(UNOPlayer player, Scanner input) {
        while (true) {
            System.out.println(player.getName() + ", choose a colour: ");
            System.out.println("  1 – RED");
            System.out.println("  2 – YELLOW");
            System.out.println("  3 – GREEN");
            System.out.println("  4 – BLUE");
            System.out.print("Enter choice (1-4): ");

            if (!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Please enter a number between 1 and 4.");
                continue;
            }

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    return UNOColor.RED;
                case 2:
                    return UNOColor.YELLOW;
                case 3:
                    return UNOColor.GREEN;
                case 4:
                    return UNOColor.BLUE;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void calculateRoundScores(UNOPlayer roundWinner) {
        int points = 0;

        for (Player p : getPlayers()) {
            UNOPlayer up = (UNOPlayer) p;
            if (up == roundWinner) {
                continue;
            }

            for (Card c : up.getHandCards()) {
                UNOCard uc = (UNOCard) c;
                switch (uc.getType()) {
                    case NUMBER:
                        points += uc.getValue();
                        break;
                    case SKIP:
                    case REVERSE:
                    case DRAW_TWO:
                        points += 20;
                        break;
                    case WILD:
                    case WILD_DRAW_FOUR:
                        points += 50;
                        break;
                }
            }
        }

        roundWinner.addScore(points);
        System.out.println("Round winner " + roundWinner.getName()
                + " earns " + points + " points. Total score: " + roundWinner.getTotalScore());
    }

    private UNOPlayer findGameWinner() {
        UNOPlayer best = null;
        for (Player p : getPlayers()) {
            UNOPlayer up = (UNOPlayer) p;
            if (up.getTotalScore() >= targetScore) {
                if (best == null || up.getTotalScore() > best.getTotalScore()) {
                    best = up;
                }
            }
        }
        return best;
    }

    @Override
    public void declareWinner() {
        UNOPlayer winner = findGameWinner();
        if (winner == null) {
            System.out.println("\nGame over – no winner reached " + targetScore + " points.");
            System.out.println("Final scores:");
            for (Player p : getPlayers()) {
                UNOPlayer up = (UNOPlayer) p;
                System.out.println("  " + up.getName() + ": " + up.getTotalScore());
            }
        } else {
            System.out.println("\n========================================");
            System.out.println("      GAME WINNER: " + winner.getName());
            System.out.println("      Final score: " + winner.getTotalScore());
            System.out.println("========================================");
        }
    }

    private int advanceIndex(int steps) {
        int numPlayers = getPlayers().size();
        int index = currentPlayerIndex;
        for (int i = 0; i < steps; i++) {
            if (directionClockwise) {
                index = (index + 1) % numPlayers;
            } else {
                index = (index - 1 + numPlayers) % numPlayers;
            }
        }
        return index;
    }
}
