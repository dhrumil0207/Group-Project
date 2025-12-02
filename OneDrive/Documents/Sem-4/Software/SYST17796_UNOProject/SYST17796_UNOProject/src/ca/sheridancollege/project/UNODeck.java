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
import java.util.Collections;

public class UNODeck extends GroupOfCards {

    public UNODeck() {
        super(108); 
        initializeStandardDeck();
        shuffle();
    }

    private void initializeStandardDeck() {

        ArrayList<Card> cards = getCards(); // inherited from GroupOfCards

        UNOColor[] colors = {UNOColor.RED, UNOColor.YELLOW, UNOColor.GREEN, UNOColor.BLUE};

        for (UNOColor color : colors) {
            cards.add(new UNOCard(color, UNOType.NUMBER, 0));

            for (int i = 1; i <= 9; i++) {
                cards.add(new UNOCard(color, UNOType.NUMBER, i));
                cards.add(new UNOCard(color, UNOType.NUMBER, i));
            }
        }

        for (UNOColor color : colors) {
            cards.add(new UNOCard(color, UNOType.SKIP, -1));
            cards.add(new UNOCard(color, UNOType.SKIP, -1));

            cards.add(new UNOCard(color, UNOType.REVERSE, -1));
            cards.add(new UNOCard(color, UNOType.REVERSE, -1));

            cards.add(new UNOCard(color, UNOType.DRAW_TWO, -1));
            cards.add(new UNOCard(color, UNOType.DRAW_TWO, -1));
        }

        for (int i = 0; i < 4; i++) {
            cards.add(new UNOCard(UNOColor.NONE, UNOType.WILD, -1));
            cards.add(new UNOCard(UNOColor.NONE, UNOType.WILD_DRAW_FOUR, -1));
        }
    }

    public void shuffle() {
        Collections.shuffle(getCards());
    }

    public UNOCard draw() {
        ArrayList<Card> cards = getCards();
        if (cards.isEmpty()) return null;

        return (UNOCard) cards.remove(cards.size() - 1);
    }
}
