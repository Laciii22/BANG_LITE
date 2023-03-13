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
        ///there is 1/8 chance that the player will be jailed
        Random random = new Random();
        int chance = random.nextInt(8);
        if (chance == 0) {
            System.out.println("You are jailed");
            fromPlayer.setJailed(true);
        }
        else {
            System.out.println("You are not jailed");
            fromPlayer.setJailed(false);
        }
    }
}
