package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Deck;
import sk.stuba.fei.uim.oop.game.Player;
import java.util.List;

public abstract class Cards {
    private final Color color;

    public Cards(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void effect(Player fromPlayer, List<Player> allPlayers, Deck deck) {
    }
}

