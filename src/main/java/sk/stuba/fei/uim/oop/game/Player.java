package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private int health;
    private ArrayList<Cards> hand;
    private ArrayList<Cards> cardsOnTable = new ArrayList<>();
    private Deck deck;
    private boolean jailed;


    public void checkForWinner(ArrayList<Player> players) {
        if (players.size() == 1) {
            System.out.println("The winner is " + players.get(0).getName());
            System.exit(0);
        }
    }

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
        if (currentPlayer.getCardsOnTable() != null) {
            System.out.println("Cards on table: ");
            for (int i = 0; i < currentPlayer.getCardsOnTable().size(); i++) {
                System.out.println(currentPlayer.getCardsOnTable().get(i).getClass().getSimpleName());
            }
        }
    }

    public ArrayList<Cards> getCardsOnTable() {
        return cardsOnTable;
    }

    public int getHealth() {
        return health;
    }

    public void recieveDamage(Player player, int damage) {
        this.health -= damage;
        System.out.println(this.getName() + " has been hit!");
        System.out.println("He has" + this.health);
    }
    public boolean hasBarrel(Player player) {
        for (int i = 0; i < player.getCardsOnTable().size(); i++) {
            if (player.getHand().get(i) instanceof Barrel) {
                return true;
            }
        }
        return false;
    }

    public boolean hasBang(Player player, Deck deck) {
        for (int i = 0; i < player.getHand().size(); i++) {
            if (player.getHand().get(i) instanceof Bang) {
                player.removeCard(player,i, deck);
                return true;
            }
        }
        return false;
    }

    public boolean hasMissed(Player player, Deck deck) {
        for (int i = 0; i < player.getHand().size(); i++) {
            if (player.getHand().get(i) instanceof Missed) {
                player.removeCard(player,i, deck);
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
        Cards removedCard = this.hand.remove(index);
        deck.getDeck().add(deck.getDeck().size(), removedCard);
    }
    public void removeCardFromTable(Player player, int index, Deck deck) {
        Cards removedCard = this.cardsOnTable.remove(index );
        deck.getDeck().add(deck.getDeck().size(), removedCard);
    }

    public void removeCard(Player player, int index) {
        Cards removedCard = this.hand.remove(index);
    }

    public boolean isDead( ArrayList<Player> players) {
        if (this.health <= 0) {
            System.out.println(this.getName() + " is dead!");
            players.remove(this);
            checkForWinner(players);
            return true;
        }
        return false;
    }
    public Player selectPlayer(Player sourcePlayer, ArrayList<Player> allPlayers) {
        ArrayList<Player> playersToShoot = new ArrayList<>();
        ArrayList<Integer> playerIndices = new ArrayList<>();
        for (int i = 0; i < allPlayers.size(); i++) {
            Player player = allPlayers.get(i);
            if (!player.equals(sourcePlayer)) {
                playersToShoot.add(player);
                playerIndices.add(i);
            }
        }
        for (int i = 0; i < playersToShoot.size(); i++) {
            System.out.println((i + 1) + ". " + playersToShoot.get(i).getName());
        }
        int index = ZKlavesnice.readInt("Select a player to target:") - 1;
        if (index >= 0 && index < playersToShoot.size()) {
            return allPlayers.get(playerIndices.get(index));
        } else {
            System.out.println("Invalid selection!");
            return selectPlayer(sourcePlayer, allPlayers);
        }
    }

    public void recieveHealth(Player fromPlayer) {
        this.health += 1;
        System.out.println(this.getName() + " Drank a beer and now has more health!");
        System.out.println("He has" + this.health);
    }

    public void addCardOnTable(Cards card) {
        this.cardsOnTable.add(card);
    }

    public void setJailed(boolean b){
        this.jailed = b;
    }

    public boolean isJailed(){
        return this.jailed;
    }



}
