package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Logic;
import sk.stuba.fei.uim.oop.game.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class Bang extends Cards {
    public Bang() {
        super(Color.BROWN);
    }

    @Override
    public void effect(Player sourcePlayer, ArrayList<Player> allPlayers) {
        int index = ZKlavesnice.readInt("Select a player to target:") ;
        Player target = allPlayers.get(index);
        if (target.hasMissed(sourcePlayer, target.getDeck())){
            System.out.println(target.getName() + " has missed!");

        } else {
            target.recieveDamage(target);
            System.out.println(target.getName() + " has been hit!");
        }
    }
}
