package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Deck;
import sk.stuba.fei.uim.oop.game.Player;
import java.util.List;

public class Beer extends Cards{
    public Beer() {
        super(Color.BROWN);
    }

    @Override
    public void effect(Player fromPlayer, List<Player> allPlayers, Deck deck) {
        fromPlayer.recieveHealth();
        fromPlayer.removeCard(fromPlayer.getHand().indexOf(this), deck);
    }
}
