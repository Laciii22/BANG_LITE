package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Deck;
import sk.stuba.fei.uim.oop.game.Player;

import java.util.ArrayList;

public class Stagecoach extends Cards{
    public Stagecoach() {
        super(Color.BROWN);
    }

    @Override
    public void effect(Player fromPlayer, ArrayList<Player> allPlayers, Deck deck) {
        fromPlayer.getCardFromDeck(fromPlayer, fromPlayer.getDeck(), 2);
        fromPlayer.removeCard(fromPlayer, fromPlayer.getHand().indexOf(this), deck);
    }
}
