package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Deck;
import sk.stuba.fei.uim.oop.game.Player;
import java.util.List;

public class Beer extends Cards{
    public Beer() {
        super(Color.BROWN);
    }

    @Override
    public void effect(Player sourcePlayer, List<Player> allPlayers, Deck deck) {
        sourcePlayer.recieveHealth();
        sourcePlayer.removeCard(sourcePlayer.getHand().indexOf(this), deck);
    }
}
