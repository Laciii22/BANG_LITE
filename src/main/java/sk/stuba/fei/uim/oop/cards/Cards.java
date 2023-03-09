package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Player;

public abstract class Cards {
    private Boolean available = true;
    private Color color;

    public Cards(Color color) {
        this.color = color;
    }

    protected void effect(Player fromPlayer){

    }

    protected void effect(Player fromPlayer, Player toPlayer) {

    }
}
