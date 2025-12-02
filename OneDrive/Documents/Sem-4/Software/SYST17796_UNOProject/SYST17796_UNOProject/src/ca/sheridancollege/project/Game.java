/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 * @date December 2025
 * @author Dhrumil Rana
 * @author Nisha Rana
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

public abstract class Game {

    private final String name;
    private ArrayList<Player> players;

    public Game(String name) {
        this.name = name;
        players = new ArrayList();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public abstract void play();

    public abstract void declareWinner();

}
