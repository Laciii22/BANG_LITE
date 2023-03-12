package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Logic;
import sk.stuba.fei.uim.oop.game.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.HashMap;

public class Bang extends Cards {
    public Bang() {
        super(Color.BROWN);
    }

    @Override
    public void effect(Player sourcePlayer, ArrayList<Player> allPlayers) {
        Player target = sourcePlayer.selectPlayer(sourcePlayer, allPlayers);
        if (target.hasMissed(target, target.getDeck())) {
            System.out.println(target.getName() + " has a missed card and missed your bang!");
        } else {
            target.recieveDamage(target, 1);
            if (target.isDead()) {
                System.out.println(target.getName() + " is dead!");
                allPlayers.remove(target);
                sourcePlayer.checkForWinner(allPlayers);
            }
        }
    }
}