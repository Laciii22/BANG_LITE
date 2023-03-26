package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Player {
    private final String name;
    private int health;
    private final List<Cards> hand;
    private final List<Cards> cardsOnTable = new ArrayList<>();

    private final Deck deck;
    private boolean jailed;

    protected Player(String name, Deck gameDeck) {
        this.hand = new ArrayList<>(4);
        this.name = name;
        this.health = 4;
        this.deck = gameDeck;
        getCardFromDeck(Player.this, gameDeck, 4);
    }

    public Deck getDeck() {
        return deck;
    }
    public String getName() {
        return this.name;
    }

    public List<Cards> getHand() {
        return hand;
    }

    public List<Cards> getCardsOnTable() {
        return cardsOnTable;
    }

    public int getHealth() {
        return health;
    }

    public void setJailed(boolean jailed){
        this.jailed = jailed;
    }

    public void addHealth(int health) {
        this.health += health;
    }
    protected void addCardOnTable(Cards card) {
        this.cardsOnTable.add(card);
    }
    protected boolean isJailed(){
        return this.jailed;
    }

    public void getCardFromDeck(Player player, Deck deck, int number) {
        if (deck.getDeck().size() < number) {
            if (deck.getDiscardPile().size() == 0) {
                System.out.println("No more cards in deck");
                return;
            }
            deck.getDeck().addAll(deck.getDiscardPile());
            deck.getDiscardPile().clear();
            Collections.shuffle(deck.getDeck());
        }
        for (int i = 0; i < number; i++) {
            Cards card = deck.getDeck().remove(0);
            player.getHand().add(card);
        }
    }

    protected void checkForWinner(List<Player> players) {
        int activePlayers = 0;
        for (Player player : players) {
            if (player.isActive()) {
                activePlayers++;
            }
        }
        if (activePlayers == 1) {
            for (Player player : players) {
                if (player.isActive()) {
                    System.out.println(player.getName() + " has won the game!");
                    System.out.println("Game over");
                    System.exit(0);
                }
            }
        }
    }

    protected void printCurrentPlayer(Player currentPlayer) {
        printHand(currentPlayer);
        printCardsOnTable(currentPlayer);
    }

    private void printHand(Player currentPlayer) {
        System.out.print(currentPlayer.getName() + "'s hand: ");
        for (int i = 0; i < currentPlayer.getHand().size(); i++) {
            System.out.print((i + 1) + ") " + currentPlayer.getHand().get(i).getClass().getSimpleName() + " ");
        }
        System.out.println();
    }

    public void printCardsOnTable(Player currentPlayer) {
        if (currentPlayer.getCardsOnTable() != null) {
            System.out.print("Cards on table: ");
            for (int i = 0; i < currentPlayer.getCardsOnTable().size(); i++) {
                System.out.print((i + 1) + ") " + currentPlayer.getCardsOnTable().get(i).getClass().getSimpleName()+ " ");
            }
        }
        System.out.println();
    }

    public void recieveDamage(List<Player> allPlayers,int damage) {
        this.health -= damage;
        System.out.println("He has " + this.health + " HP");
        isDead(allPlayers);
    }

    public void removeCard(int index) {
        this.hand.remove(index);
    }

    public void removeCardFromTable(int index) {
        this.cardsOnTable.remove(index);
    }

    public void removeCardToPile(int index) {
        Cards removedCard = this.hand.get(index);
        this.hand.remove(removedCard);
        deck.getDiscardPile().add(removedCard);
    }

    public void removeCardFromTableToPile(int index) {
        Cards removedCard = this.cardsOnTable.get(index);
        this.cardsOnTable.remove(removedCard);
        deck.getDiscardPile().add(removedCard);
    }

    private void removeCardsFromDeadPlayer(Player player) {
        for (int i = player.getHand().size() - 1; i >= 0; i--) {
            player.removeCardToPile(i);
        }
        for (int i = player.getCardsOnTable().size() - 1; i >= 0; i--) {
            player.removeCardFromTableToPile(i);
        }
    }
    public boolean isActive() {
        return health > 0;
    }

    protected boolean isDead( List<Player> players) {
        if (this.health <= 0) {
            System.out.println(this.getName() + " is dead!");
            removeCardsFromDeadPlayer(this);
            checkForWinner(players);
            return true;
        }
        return false;
    }
}
