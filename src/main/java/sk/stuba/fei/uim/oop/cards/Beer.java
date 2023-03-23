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
        this.recieveHealth(sourcePlayer);
        sourcePlayer.removeCardToPile(sourcePlayer.getHand().indexOf(this));
    }

    private void recieveHealth(Player sourcePlayer) {
        sourcePlayer.addHealth(1);
        System.out.println(sourcePlayer.getName() + " Drank a beer and now has more health!");
        System.out.println("He has " + sourcePlayer.getHealth() + " HP");
    }
}
