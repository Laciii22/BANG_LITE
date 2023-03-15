package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Deck;
import sk.stuba.fei.uim.oop.game.Player;

import java.util.ArrayList;
import java.util.Random;

public class Bang extends Cards {
    public Bang() {
        super(Color.BROWN);
    }

    @Override
    public void effect(Player sourcePlayer, ArrayList<Player> allPlayers, Deck deck) {
        Player target = sourcePlayer.selectPlayer(sourcePlayer, allPlayers);
        if (target.hasBarrel(target)) {
            Random random = new Random();
            int chance = random.nextInt(4);
            if (chance == 0) {
                System.out.println(target.getName() + " has hidden behind a barrel and missed your bang!");
                return;
            }
        }
        if (target.hasMissed(target , target.getDeck())){
            System.out.println(target.getName() + " has missed card and he missed your bang");
        }
        else{
            System.out.println(target.getName() + " was shot by " + sourcePlayer.getName());
            if (target.isDead(allPlayers)) {
                for (int i = 0; i < target.getHand().size(); i++) {
                    target.removeCard(i, deck);
                }
                for (int i = 0; i < target.getCardsOnTable().size(); i++) {
                    target.removeCard(i, deck);
                }
            }
            target.recieveDamage(allPlayers, 1);
        }
        sourcePlayer.removeCard(sourcePlayer.getHand().indexOf(this), deck);
    }
}