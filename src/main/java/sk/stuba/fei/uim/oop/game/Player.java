package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

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


    protected boolean checkForWinner(List<Player> players) {
        int activePlayers = 0;
        for (Player player : players) {
            if (player.isActive()) {
                activePlayers++;
            }
        }

        return activePlayers == 1;
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

    protected Player(String name, Deck gameDeck) {
        this.hand = new ArrayList<>(4);
        this.name = name;
        this.health = 4;
        this.deck = gameDeck;
        getCardFromDeck(Player.this, gameDeck, 4);
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


    public List<Cards> getCardsOnTable() {
        return cardsOnTable;
    }

    protected int getHealth() {
        return health;
    }

    public void recieveDamage(int damage) {
        this.health -= damage;
        System.out.println("He has " + this.health + " HP");
        isDead();
    }
    public boolean hasBarrel(Player player) {
        for (int i = 0; i < player.getCardsOnTable().size(); i++) {
            if (player.getCardsOnTable().get(i) instanceof Barrel) {
                return true;
            }
        }
        return false;
    }

    public boolean hasBang(Player player) {
        for (int i = 0; i < player.getHand().size(); i++) {
            if (player.getHand().get(i) instanceof Bang) {
                player.removeCardToPile(i);
                return true;
            }
        }
        return false;
    }

    public boolean hasMissed(Player player) {
        for (int i = 0; i < player.getHand().size(); i++) {
            if (player.getHand().get(i) instanceof Missed) {
                player.removeCardToPile(i);
                return true;
            }
        }
        return false;
    }

    public void getCardFromDeck(Player player, Deck deck, int number) {
        if (deck.getDeck().size() < number) {
            deck.getDeck().addAll(deck.getDiscardPile());
            deck.getDiscardPile().clear();
            Collections.shuffle(deck.getDeck());
        }
        for (int i = 0; i < number; i++) {
            Cards card = deck.getDeck().remove(0);
            player.getHand().add(card);
        }
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

    protected boolean isDead() {
        if (this.health <= 0) {
            System.out.println(this.getName() + " is dead!");
            removeCardsFromDeadPlayer(this);
            return true;
        }
        return false;
    }
    public Player selectPlayer(Player sourcePlayer, List<Player> allPlayers) {
        ArrayList<Player> playersToShoot = new ArrayList<>();
        ArrayList<Integer> playerIndices = new ArrayList<>();
        for (int i = 0; i < allPlayers.size(); i++) {
            Player player = allPlayers.get(i);
            if (!player.equals(sourcePlayer) && player.isActive()) {
                playersToShoot.add(player);
                playerIndices.add(i);
            }
        }
        for (int i = 0; i < playersToShoot.size(); i++) {
            System.out.print((i + 1) + ") " + playersToShoot.get(i).getName() + " " + playersToShoot.get(i).getHealth() + " HP ");
            playersToShoot.get(i).printCardsOnTable(playersToShoot.get(i));

        }
        int index = ZKlavesnice.readInt("Select a player to target:") - 1;
        if (index >= 0 && index < playersToShoot.size()) {
            return allPlayers.get(playerIndices.get(index));
        } else {
            System.out.println("Invalid selection!");
            return selectPlayer(sourcePlayer, allPlayers);
        }
    }

    public void recieveHealth() {
        this.health += 1;
        System.out.println(this.getName() + " Drank a beer and now has more health!");
        System.out.println("He has " + this.health + " HP");
    }

    protected void addCardOnTable(Cards card) {
        this.cardsOnTable.add(card);
    }

    public void setJailed(boolean jailed){
        this.jailed = jailed;
    }

    protected boolean isJailed(){
        return this.jailed;
    }

}
