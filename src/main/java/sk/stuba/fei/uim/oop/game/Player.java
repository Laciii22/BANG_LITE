package sk.stuba.fei.uim.oop.game;
import sk.stuba.fei.uim.oop.cards.*;

import java.util.*;

public class Player {
    private final String name;
    private int health;
    private ArrayList<Cards> hand;
    private static List<Player> allPlayers = new ArrayList<>();

    public Player(String name, List<Cards> deck) {
        this.hand = new ArrayList<Cards>(4);
        this.name = name;
        this.health = 4;
        for (int i = 0; i < 4; i++) {
            Cards card = deck.remove(0);
            hand.add(card);
        }
        allPlayers.add(this);
    }


    public static List<Player> getAllPlayers() {
        return allPlayers;
    }

    public String getPlayerInfo() {
        int serialNumber = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("Player: ").append(name).append("\n");
        sb.append("Cards:\n");
        for (Cards card : hand) {
            sb.append(serialNumber).append(card.getClass().getSimpleName()).append("\n");
            serialNumber++;
        }
        return sb.toString();
    }

    public void printCurrentPlayer(Player currentPlayer) {
        System.out.print(currentPlayer.getName() + ": ");
        for (int i = 0; i < currentPlayer.getHand().size(); i++) {
            System.out.print((i + 1) + ") " + currentPlayer.getHand().get(i).getClass().getSimpleName() + " ");
        }
        System.out.println();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Cards> getHand() {
        return hand;
    }

    public void recieveDamage(Player player) {
        this.health -= 1;
    }

    public boolean hasMissed(Player player) {
        if (player.getHand().stream().anyMatch(card -> card instanceof Missed)) {
            System.out.println("Player " + player.getName() + " has missed card");
            return true;
            ///now he has to throw away the missed card

        } else {
            return true;

        }
    }

}
