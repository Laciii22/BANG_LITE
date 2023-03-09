package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.Cards;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.List;


public class Logic {
    private final Player[] players;



    private int currentPlayerIndex;

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public Logic() {
        System.out.println("Game can start");
        int numberPlayers = 0;
        while (numberPlayers < 2 || numberPlayers >4){
            numberPlayers = ZKlavesnice.readInt("How many players is going to play??");
            if (numberPlayers < 2 || numberPlayers >4){
                System.out.println("This game can be play 2-4 players\n" +
                                    "Please try again.");
            }
        }
        this.players = new Player[numberPlayers];
        Deck deck = new Deck();
        for (int i = 0; i < numberPlayers; i++){
            this.players[i] = new Player(ZKlavesnice.readString("What is the name of player " + (i+1)), deck.getDeck());
        }
        startGame();
    }
    public void startGame(){
        for (int i = 0; i < players.length; i++){
            playerTurn();
        }
    }
/*    public void startGame(){
        int playerIndex = 0;
        while (true){
            if (playerIndex >= this.players.length){
                playerIndex = 0;
            }
            Player player = this.players[playerIndex];
            System.out.println(player.getPlayerInfo());
            int serialNumber = 1;
            for (Cards card : player.getHand()) {
                System.out.println(serialNumber + ") " + card.getClass().getSimpleName());
                serialNumber++;
            }
            int chooseCard = ZKlavesnice.readInt("Choose card: ");
            player.getHand().get(chooseCard - 1).effect();
            playerIndex++;
        }
    }*/

    public void playerTurn() {
        Player currentPlayer = players[currentPlayerIndex];
        currentPlayer.printCurrentPlayer(currentPlayer);
        int chooseCard = ZKlavesnice.readInt("Choose card: ");
        currentPlayer.getHand().get(chooseCard - 1).effect();
        currentPlayerIndex++;
    }


    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

}



