package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Player;

import java.util.ArrayList;
import java.util.Random;

public class Jail extends Cards{
    public Jail() {
        super(Color.BLUE);
    }

    @Override
    public void effect(Player fromPlayer, ArrayList<Player> allPlayers) {
        Random random = new Random();
        int chance = random.nextInt(4);
        if (chance == 0) {
            System.out.println("You got out of jail!");
        } else {
            fromPlayer.setInJail(true);
        }
    }
}
