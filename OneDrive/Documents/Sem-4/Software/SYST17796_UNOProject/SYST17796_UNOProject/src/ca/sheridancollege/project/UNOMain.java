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

public class UNOMain {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("        SYST17796 – UNO Game");
        System.out.println("        by Dhrumil Rana & Nisha Rana");
        System.out.println("========================================\n");

        int numPlayers = 0;

        while (numPlayers < 2 || numPlayers > 4) {
            System.out.print("Enter number of players (2–4): ");
            if (!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Please enter a valid number between 2 and 4.");
                continue;
            }
            numPlayers = input.nextInt();
            input.nextLine(); 

            if (numPlayers < 2 || numPlayers > 4) {
                System.out.println("UNO is best played with 2 to 4 players.");
            }
        }

        ArrayList<Player> players = new ArrayList<>();

        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String name = input.nextLine().trim();
            if (name.isEmpty()) {
                name = "Player" + i;
            }
            players.add(new UNOPlayer(name));
        }

        UNOGame game = new UNOGame(players);
        game.play();
    }
}
