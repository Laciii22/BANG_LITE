package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.*;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private int health;
    private ArrayList<Cards> hand;
    private List<Player> allPlayers;
    private Deck deck;

    public Player(String name, List<Cards> deck, List<Player> allPlayers, Deck gameDeck) {
        this.hand = new ArrayList<>(4);
        this.name = name;
        this.health = 4;
        for (int i = 0; i < 4; i++) {
            Cards card = deck.remove(0);
            hand.add(card);
        }
        this.allPlayers = allPlayers;
        allPlayers.add(this);
        this.deck = gameDeck;
    }

    public Deck getDeck() {
        return deck;
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

    public boolean hasMissed(Player player, Deck deck) {
        for (int i = 0; i < player.getHand().size(); i++) {
            if (player.getHand().get(i) instanceof Missed) {
                System.out.println("Player " + player.getName() + " has missed card");
                player.removeCard(player,i+1, deck);
                return true;
            }
        }
        return false;
    }


    public void removeCard(Player player, int index, Deck deck) {
        Cards removedCard = this.hand.remove(index - 1);
        deck.getDeck().add(deck.getDeck().size(), removedCard);
    }
}
