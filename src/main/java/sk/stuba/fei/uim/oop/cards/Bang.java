package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Deck;
import sk.stuba.fei.uim.oop.game.Player;

import java.util.List;
import java.util.Random;

public class Bang extends Cards {
    public Bang() {
        super(Color.BROWN);
    }

    @Override
    public void effect(Player sourcePlayer, List<Player> allPlayers, Deck deck) {
        Player target = sourcePlayer.selectPlayer(sourcePlayer, allPlayers);
        if (target.hasBarrel(target)) {
            int chance = getRandom().nextInt(4);
            if (chance == 0) {
                System.out.println(target.getName() + " has hidden behind a barrel and missed your bang!");
                sourcePlayer.removeCard(sourcePlayer.getHand().indexOf(this), deck);
                return;
            }
            else{
                System.out.println(target.getName() + " couldn't hide behind a barrel");
            }
        }
        if (target.hasMissed(target , target.getDeck())){
            System.out.println(target.getName() + " has missed card and he missed your bang");
        }
        else{
            System.out.println(target.getName() + " was shot by " + sourcePlayer.getName());
            target.recieveDamage(allPlayers, 1);
        }
        sourcePlayer.removeCard(sourcePlayer.getHand().indexOf(this), deck);
    }
}