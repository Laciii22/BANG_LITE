package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private final String name;
    private int health;
    private final ArrayList<Cards> hand;
    private ArrayList<Cards>  cardsOnTable = new ArrayList<>();
    private final Deck deck;


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

    public ArrayList<Cards> getCardsOnTable() {
        return cardsOnTable;
    }
    public void addCardToTable(Cards card) {
        this.cardsOnTable.add(card);
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
        if  (currentPlayer.getCardsOnTable() != null) {
            System.out.print("Cards on table: ");
            for (int i = 0; i < currentPlayer.getCardsOnTable().size(); i++) {
                System.out.print(currentPlayer.getCardsOnTable().get(i).getClass().getSimpleName() + " ");
            }
        }
        System.out.println();
    }

    public int getHealth() {
        return health;
    }

    public void recieveDamage(Player player) {
        this.health -= 1;
        System.out.println(this.getName() + " has been hit!");
        System.out.println("He has" + this.health);
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

    public boolean hasBarrel(Player player, Deck deck) {
        for (int i = 0; i < player.getCardsOnTable().size(); i++) {
            if (player.getCardsOnTable().get(i) instanceof Barrel) {
                Random random = new Random();
                random.nextInt(4);
                if (random.nextInt(4) == 0) {
                    System.out.println("Barrel saved you!");
                    return true;
                }
                System.out.println("Barrel didn't save you!");
                return false;
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

    public boolean setInJail(boolean inJail) {
        inJail = true;
        return inJail;
    }
}
