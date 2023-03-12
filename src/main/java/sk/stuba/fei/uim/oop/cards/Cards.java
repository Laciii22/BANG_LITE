package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Player;

import java.util.ArrayList;

public abstract class Cards {
    private Boolean available = true;
    private Color color;

    public Cards(Color color) {
        this.color = color;
    }

    public void effect(Player fromPlayer, ArrayList<Player> allPlayers) {
    }
}

