package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Deck;
import sk.stuba.fei.uim.oop.game.Player;

import java.util.List;

public class Prison extends Cards{
    public Prison() {
        super(Color.BLUE);
    }

    @Override
    public void effect(Player sourcePlayer, List<Player> allPlayers, Deck deck) {
        int chance=getRandom().nextInt(4);
        System.out.println("TUTU " + chance);
        if (chance == 0) {
            System.out.println(sourcePlayer.getName() + " is in prison");
            sourcePlayer.setJailed(true);
            sourcePlayer.removeCardFromTableToPile(sourcePlayer.getCardsOnTable().indexOf(this));
        }
        else {
            System.out.println(sourcePlayer.getName() + " is not in prison");
            sourcePlayer.setJailed(false);
            sourcePlayer.removeCardFromTableToPile(sourcePlayer.getCardsOnTable().indexOf(this));

        }
    }
}
