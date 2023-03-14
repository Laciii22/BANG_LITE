package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Deck;
import sk.stuba.fei.uim.oop.game.Player;

import java.util.ArrayList;

public class Indians extends Cards{
    public Indians() {
        super(Color.BROWN);
    }

    @Override
    public void effect(Player fromPlayer, ArrayList<Player> allPlayers, Deck deck) {
        for (Player player : allPlayers) {
            if (player != fromPlayer) {
                player.recieveDamage(player, 1);
            }
        }
        fromPlayer.removeCard(fromPlayer, fromPlayer.getHand().indexOf(this), deck);
    }
}
