package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.List;

public class Logic {
    private final List<Player> players;
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
            Player player = new Player(name, deck);
            players.add(player);
        }
        startGame();
    }


    private void startGame() {
        System.out.println("\nThe game has started!");
        currentPlayerIndex = 0;
        while (true) {
            if (currentPlayerIndex >= players.size()) {
                currentPlayerIndex = 0;
            }
            Player currentPlayer = players.get(currentPlayerIndex);
            if (currentPlayer.isDead(players)) {
                currentPlayerIndex++;
                continue;
            }
            playerTurn();
        }
    }

    private void playerTurn() {
        Player currentPlayer = players.get(currentPlayerIndex);
        currentPlayer.checkForWinner(players);
        if (playCardsOnTable(currentPlayer, players)){
            return;
        }
        if (currentPlayer.isJailed()) {
            currentPlayer.setJailed(false);
            currentPlayerIndex++;
            if (currentPlayerIndex >= players.size()) {
                currentPlayerIndex = 0;
            }
            return;
        }
        currentPlayer.getCardFromDeck(currentPlayer, deck, 2);

        currentPlayer.printCurrentPlayer(currentPlayer);

        boolean continuePlaying;
        do {
            if (currentPlayer.getHand().isEmpty()) {
                System.out.println("You have no cards in your hand. You can't play any cards.");
                break;
            }
            continuePlaying = playCard(currentPlayer, players, deck);
        } while (continuePlaying);
        discardCards(currentPlayer);
        currentPlayerIndex++;
        if (currentPlayerIndex >= players.size()) {
            currentPlayerIndex = 0;
        }
    }

    private boolean canPlayBlueCard(Player player, Cards card) {
        for (Cards tableCard : player.getCardsOnTable()) {
            if (tableCard.getClass() == card.getClass()) {
                return true;
            }
        }
        return false;
    }

    private List<Cards> getCardsOnTableInOrder(Player currentPlayer) {
        List<Cards> cardsToAdd = new ArrayList<>();
        for (Cards card : currentPlayer.getCardsOnTable()) {
            if (card instanceof Dynamite) {
                cardsToAdd.add(0, card);
            } else {
                cardsToAdd.add(card);
            }
        }
        return cardsToAdd;
    }

    private boolean playCard(Player currentPlayer, List<Player> players, Deck deck) {
        int cardIndex = -2;
        while (cardIndex < -1 || cardIndex >= currentPlayer.getHand().size()) {
            cardIndex = ZKlavesnice.readInt("Select a card to play (0 to end turn): ") - 1;
            if (cardIndex == -1) {
                return false;
            }
            if (cardIndex < 0 || cardIndex >= currentPlayer.getHand().size()) {
                System.out.println("Invalid card index. Please select a valid card index.");
            }
        }
        Cards card = currentPlayer.getHand().get(cardIndex);
        if (card.getColor() == Color.BLUE) {
            if (canPlayBlueCard(currentPlayer, card)) {
                System.out.println("You can't play this card. You already have this card on the table.");
                return true;
            }
            if (card instanceof Prison) {
                Player targetPlayer = card.selectPlayer(currentPlayer, players);
                if (canPlayBlueCard(targetPlayer, card)) {
                    System.out.println("You can't play this card on this player. He already has a Jail card on the table.");
                    return true;
                }
                currentPlayer.removeCard(cardIndex);
                targetPlayer.getCardsOnTable().add(card);
            } else {
                currentPlayer.addCardOnTable(card);
                currentPlayer.removeCard(cardIndex);
            }
        } else {
            card.effect(currentPlayer, players, deck);
        }
        currentPlayer.printCurrentPlayer(currentPlayer);
        return playAgain();
    }
    private void discardCards(Player currentPlayer) {
        while (currentPlayer.getHand().size() > currentPlayer.getHealth()) {
            currentPlayer.printCurrentPlayer(currentPlayer);
            int cardIndex = -1;
            while (cardIndex < 0 || cardIndex >= currentPlayer.getHand().size()) {
                cardIndex = ZKlavesnice.readInt("Select a card to discard: ") - 1;
                if (cardIndex < 0 || cardIndex >= currentPlayer.getHand().size()) {
                    System.out.println("Invalid card index. Please select a valid card index.");
                }
            }
            currentPlayer.removeCardToPile(cardIndex);
        }
    }

    private boolean playCardsOnTable(Player currentPlayer, List<Player> players) {
        List<Cards> cardsOnTableInOrder = getCardsOnTableInOrder(currentPlayer);
        for (Cards card : cardsOnTableInOrder) {
            if (!(card instanceof Barrel)) {
                card.effect(currentPlayer, players, deck);
            }
        }
        return false;
    }

    private boolean playAgain() {
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
