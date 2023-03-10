package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.*;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private int health;
    private ArrayList<Cards> hand;
    private Deck deck;

    public Deck getDeck() {
        return deck;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Cards> getHand() {
        return hand;
    }

    public Player(String name, List<Cards> deck, ArrayList<Player> allPlayers, Deck gameDeck) {
        this.hand = new ArrayList<>(4);
        this.name = name;
        this.health = 4;
        this.deck = gameDeck;
        getCardFromDeck(Player.this, gameDeck, 4);
    }

    public void printCurrentPlayer(Player currentPlayer) {
        System.out.print(currentPlayer.getName() + ": ");
        for (int i = 0; i < currentPlayer.getHand().size(); i++) {
            System.out.print((i + 1) + ") " + currentPlayer.getHand().get(i).getClass().getSimpleName() + " ");
        }
        System.out.println();
    }

    public void recieveDamage(Player player) {
        this.health -= 1;
    }

    public boolean hasMissed(Player player, Deck deck) {
        for (int i = 0; i < player.getHand().size(); i++) {
            if (player.getHand().get(i) instanceof Missed) {
                player.removeCard(player,i+1, deck);
                return true;
            }
        }
        return false;
    }

    public void getCardFromDeck(Player player, Deck deck, int number) {
        for (int i = 0; i < number; i++) {
            Cards card = deck.getDeck().remove(0);
            player.getHand().add(card);
        }
    }

    public void removeCard(Player player, int index, Deck deck) {
        Cards removedCard = this.hand.remove(index - 1);
        deck.getDeck().add(deck.getDeck().size(), removedCard);
    }

    public boolean isDead() {
        return this.health <= 0;
    }
}
