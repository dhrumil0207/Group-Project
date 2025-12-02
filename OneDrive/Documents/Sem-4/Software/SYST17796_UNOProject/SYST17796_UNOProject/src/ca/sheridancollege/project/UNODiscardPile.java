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

public class UNODiscardPile {

    private final ArrayList<UNOCard> cards = new ArrayList<>();

    public void addCard(UNOCard card) {
        cards.add(card);
    }

    public UNOCard getTopCard() {
        if (cards.isEmpty()) return null;
        return cards.get(cards.size() - 1);
    }

    @Override
    public String toString() {
        return "Discard pile: " + cards.toString();
    }
}
