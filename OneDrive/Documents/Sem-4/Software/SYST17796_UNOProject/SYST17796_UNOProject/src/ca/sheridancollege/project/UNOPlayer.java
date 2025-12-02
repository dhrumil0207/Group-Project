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

public class UNOPlayer extends Player {

    private GroupOfCards hand;

    private int totalScore;

    public UNOPlayer(String name) {
        super(name);
        this.hand = new GroupOfCards(108);
        this.totalScore = 0;
    }

    public GroupOfCards getHand() {
        return hand;
    }

    public ArrayList<Card> getHandCards() {
        return hand.getCards();
    }

    public int getHandSize() {
        return getHandCards().size();
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void addScore(int points) {
        totalScore += points;
    }

    public void clearHand() {
        this.hand = new GroupOfCards(108);
    }

    public void receiveCard(UNOCard card) {
        getHandCards().add(card);
    }

    public UNOCard removeCardAt(int index) {
        return (UNOCard) getHandCards().remove(index);
    }

    public boolean hasUno() {
        return getHandSize() == 1;
    }

    public boolean hasEmptyHand() {
        return getHandSize() == 0;
    }

    @Override
    public void play() {
        System.out.println("Turn handling for " + getName()
                + " is managed by UNOGame.");
    }

    public int chooseCardIndexToPlay(UNOCard topCard, Scanner input) {

        while (true) {
            System.out.println("\n----------------------------------------");
            System.out.println("Player: " + getName());
            System.out.println("Top of discard pile: " + topCard);
            System.out.println("Your hand:");

            for (int i = 0; i < getHandSize(); i++) {
                UNOCard card = (UNOCard) getHandCards().get(i);
                System.out.println("  [" + i + "] " + card);
            }

            System.out.println("Enter the index of the card to play.");
            System.out.println("Or enter -1 to DRAW a card instead.");
            System.out.print("Your choice: ");

            if (!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Please enter a number.");
                continue;
            }

            int choice = input.nextInt();
            input.nextLine(); 

            if (choice == -1) {
                return -1;
            }

            if (choice < 0 || choice >= getHandSize()) {
                System.out.println("Invalid index. Try again.");
                continue;
            }

            UNOCard chosen = (UNOCard) getHandCards().get(choice);
            if (!chosen.isPlayableOn(topCard)) {
                System.out.println("You cannot play " + chosen
                        + " on top of " + topCard + ". Try again.");
                continue;
            }

            return choice;
        }
    }
}
