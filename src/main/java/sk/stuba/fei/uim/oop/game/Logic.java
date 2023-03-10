package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.Cards;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class Logic {
    private final ArrayList<Player> players;
    private final Deck deck;
    private int currentPlayerIndex;

    public Logic() {
        System.out.println("Game can start");
        int numberPlayers = 0;
        while (numberPlayers < 2 || numberPlayers > 4) {
            numberPlayers = ZKlavesnice.readInt("How many players are going to play?");
            if (numberPlayers < 2 || numberPlayers > 4) {
                System.out.println("This game can be played by 2-4 players.\nPlease try again.");
            }
        }
        this.players = new ArrayList<>(numberPlayers);
        this.deck = new Deck();
        for (int i = 0; i < numberPlayers; i++) {
            String name = ZKlavesnice.readString("Enter name of player " + (i + 1) + ": ");
            Player player = new Player(name, deck.getDeck(), players, deck);
            players.add(player);
        }
        startGame();
    }

    public void startGame() {
        System.out.println("\nThe game has started!");
        currentPlayerIndex = 0;
        while (true) {
            if (currentPlayerIndex >= players.size()) {
                currentPlayerIndex++;
            }
            playerTurn();
        }
    }

    public void playerTurn() {
        Player currentPlayer = players.get(currentPlayerIndex);
        currentPlayer.printCurrentPlayer(currentPlayer);
        boolean playAgain = true;
        while (playAgain) {
            int chooseCard = 0;
            while (true) {
                chooseCard = ZKlavesnice.readInt("Choose card: ");
                if (chooseCard < 1 || chooseCard > currentPlayer.getHand().size()) {
                    System.out.println("Invalid card choice. Please try again.");
                } else {
                    break;
                }
            }
            Cards chosenCard = currentPlayer.getHand().get(chooseCard - 1);
            chosenCard.effect(currentPlayer, players);
            currentPlayer.removeCard(currentPlayer, chooseCard, deck);
            System.out.println("\nCurrent state of the game:\n" + deck.getDeckSize());
            System.out.println();
            String input = ZKlavesnice.readString("Do you want to play again? (y/n): ");
            if (input.equalsIgnoreCase("n")) {
                playAgain = false;
                currentPlayerIndex++;
            }
        }
    }
}
