package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Deck;
import sk.stuba.fei.uim.oop.game.Player;

import java.util.List;
import java.util.Random;

public class Jail extends Cards{
    public Jail() {
        super(Color.BLUE);
    }

    @Override
    public void effect(Player sourcePlayer, List<Player> allPlayers, Deck deck) {
        Random random = new Random();
        int chance = random.nextInt(8);
        if (chance == 0) {
            System.out.println(sourcePlayer.getName() + " is jailed");
            sourcePlayer.setJailed(true);
            sourcePlayer.removeCardFromTable(sourcePlayer.getCardsOnTable().indexOf(this), deck);
        }
        else {
            System.out.println(sourcePlayer.getName() + " is not jailed");
            sourcePlayer.setJailed(false);
            sourcePlayer.removeCardFromTable(sourcePlayer.getCardsOnTable().indexOf(this), deck);

        }
    }
}
