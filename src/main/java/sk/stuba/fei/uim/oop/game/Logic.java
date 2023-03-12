package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.Cards;
import sk.stuba.fei.uim.oop.cards.Color;
import sk.stuba.fei.uim.oop.cards.Jail;
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
        ArrayList<Player> allPlayers = new ArrayList<>();
        for (int i = 0; i < numberPlayers; i++) {
            String name = ZKlavesnice.readString("Enter name of player " + (i + 1) + ": ");
            Player player = new Player(name, deck.getDeck(), allPlayers, deck);
            allPlayers.add(player);
            players.add(player);
        }
        startGame();
    }


    public void startGame() {
        System.out.println("\nThe game has started!");
        currentPlayerIndex = 0;
        while (true) {
            Player currentPlayer = players.get(currentPlayerIndex);
            if (currentPlayer.isDead()) {
                currentPlayerIndex++;
                continue;
            }
            playerTurn();
            if (currentPlayerIndex >= players.size()) {
                currentPlayerIndex = 0;
            }
        }
    }


    public void playerTurn() {
        Player currentPlayer = players.get(currentPlayerIndex);
        currentPlayer.getCardFromDeck(currentPlayer, deck, 2);
        boolean continuePlaying = true;
        while (continuePlaying) {
            currentPlayer.printCurrentPlayer(currentPlayer);
            if (currentPlayer.getCardsOnTable() != null) {
                for (int n = 0; n < currentPlayer.getCardsOnTable().size(); n++) {
                    Cards blueCard = currentPlayer.getCardsOnTable().get(n);
                    if (blueCard.getColor() == Color.BLUE) {
                        continuePlaying = playAgain();
                        if (!continuePlaying) {
                            discardCardsUntilFit(currentPlayer, deck);
                            break;
                        }
                        int cardIndex = ZKlavesnice.readInt("Select a card to play: ");
                        Cards card = currentPlayer.getHand().get(cardIndex - 1);
                        handleBlueCard(currentPlayer, card, players, deck);
                        currentPlayer.removeCard(currentPlayer, cardIndex, deck);
                    }
                }
            }
            if (!continuePlaying) {
                discardCardsUntilFit(currentPlayer, deck);
                break;
            }
            continuePlaying = playAgain();
            if (!continuePlaying) {
                discardCardsUntilFit(currentPlayer, deck);
                break;
            }
            int cardIndex = ZKlavesnice.readInt("Select a card to play: ");
            Cards card = currentPlayer.getHand().get(cardIndex - 1);
            if (card.getColor() == Color.BLUE) {
                handleBlueCard(currentPlayer, card, players, deck);
            } else {
                card.effect(currentPlayer, players);
            }
            currentPlayer.removeCard(currentPlayer, cardIndex, deck);
            if (!continuePlaying) {
                discardCardsUntilFit(currentPlayer, deck);
                break;
            }
        }
        currentPlayerIndex++;
        if (currentPlayerIndex >= players.size()) {
            currentPlayerIndex = 0;
        }
    }



    public void discardCardsUntilFit(Player player, Deck deck) {
        while (player.getHand().size() > player.getHealth()) {
            player.printCurrentPlayer(player);
            int cardIndex = ZKlavesnice.readInt("Select a card to discard: ");
            player.removeCard(player, cardIndex, deck);
        }
    }

    private void handleBlueCard(Player currentPlayer, Cards card, ArrayList<Player> players, Deck deck) {
        if (card instanceof Jail) {
            currentPlayer.printCurrentPlayer(currentPlayer);
            Player target = currentPlayer.selectPlayer(currentPlayer, players);
            target.addCardToTable(card);
        } else {
            currentPlayer.addCardToTable(card);
        }
    }



    public boolean playAgain() {
        String input = ZKlavesnice.readString("Do you want to play in this turn? (y/n) ");
        if (input.equalsIgnoreCase("y")) {
            return true;
        } else if (input.equalsIgnoreCase("n")) {

            return false;
        } else {
            System.out.println("Please enter y or n");
            return playAgain();
        }
    }
}
