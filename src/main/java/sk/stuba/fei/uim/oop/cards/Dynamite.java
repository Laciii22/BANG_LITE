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
        Random random = new Random();
        int chance = random.nextInt(8);
        if (chance == 0) {
            fromPlayer.recieveDamage(fromPlayer, 3);
            System.out.println("Dynamite exploded and you lost 3 health");
            if (fromPlayer.isDead()) {
                System.out.println(fromPlayer.getName() + " is dead!");
                ///remove all his cards
                for (int i = 0; i < fromPlayer.getHand().size(); i++) {
                    fromPlayer.removeCard(fromPlayer, i+1,  fromPlayer.getDeck());
                }
                for (int i = 0; i < fromPlayer.getCardsOnTable().size(); i++) {
                    fromPlayer.removeCard(fromPlayer, i+1,  fromPlayer.getDeck());
                }
            }
        }
        else {
            System.out.println("Dynamite didn't explode");
        }
    }
}
