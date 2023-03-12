package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Player;

import java.util.ArrayList;
import java.util.Random;

public class Dynamite extends Cards{

    public Dynamite() {

        super(Color.BLUE);
    }

    @Override
    public void effect(Player fromPlayer, ArrayList<Player> allPlayers) {
        //dynamite has 1/8 chance that it will explode and i want to use random and if it will explode it will remove 3 health from player
        Random random = new Random();
        int chance = random.nextInt(8);
        if (chance == 0) {
            fromPlayer.recieveDamage(fromPlayer, 3);
            System.out.println("Dynamite exploded and you lost 3 health");
        }
        else {
            System.out.println("Dynamite didn't explode");
        }
    }
}
