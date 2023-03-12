package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Player;

import java.util.ArrayList;

public class Indians extends Cards{
    public Indians() {
        super(Color.BROWN);
    }

    @Override
    public void effect(Player fromPlayer, ArrayList<Player> allPlayers) {
        for (Player player : allPlayers) {
            if (player != fromPlayer) {
                player.recieveDamage(player);
            }
        }
    }
}
