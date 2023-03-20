package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Deck;
import sk.stuba.fei.uim.oop.game.Player;
import java.util.List;
import java.util.Random;

public abstract class Cards {
    private final Color color;
    private final Random random;

    public Cards(Color color) {
        this.color = color;
        this.random = new Random();
    }

    public Color getColor() {
        return color;
    }

    public void effect(Player sourcePlayer, List<Player> allPlayers, Deck deck) {
    }

    public Random getRandom() {
        return random;
    }
}
