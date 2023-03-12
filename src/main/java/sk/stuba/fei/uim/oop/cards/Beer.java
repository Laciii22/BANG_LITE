package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Player;

import java.util.ArrayList;

public class Beer extends Cards{
    public Beer() {
        super(Color.BROWN);

    }

    @Override
    public void effect(Player fromPlayer, ArrayList<Player> allPlayers) {
        fromPlayer.recieveHealth(fromPlayer);
    }
}
