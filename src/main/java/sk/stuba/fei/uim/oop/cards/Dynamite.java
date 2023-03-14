package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Deck;
import sk.stuba.fei.uim.oop.game.Player;

import java.util.ArrayList;
import java.util.Random;

public class Dynamite extends Cards {

    public Dynamite() {

        super(Color.BLUE);
    }

    @Override
    public void effect(Player fromPlayer, ArrayList<Player> allPlayers, Deck deck) {
        Random random = new Random();
        int chance = random.nextInt(8);
        if (chance == 0) {
            fromPlayer.recieveDamage(fromPlayer, 3);
            System.out.println("Dynamite exploded and you lost 3 health");
            if (fromPlayer.isDead()) {
                System.out.println(fromPlayer.getName() + " is dead!");
                for (int i = 0; i < fromPlayer.getHand().size(); i++) {
                    fromPlayer.removeCard(fromPlayer, i , deck);
                }
                for (int i = 0; i < fromPlayer.getCardsOnTable().size(); i++) {
                    fromPlayer.removeCard(fromPlayer, i , deck);
                }
            }
        } else {
            System.out.println("Dynamite didn't explode");
            int currentPlayerIndex = allPlayers.indexOf(fromPlayer);
            int previousPlayerIndex = currentPlayerIndex == 0 ? allPlayers.size() - 1 : currentPlayerIndex - 1;
            Player previousPlayer = allPlayers.get(previousPlayerIndex);
            previousPlayer.getCardsOnTable().add(this);
            fromPlayer.removeCard(fromPlayer, fromPlayer.getCardsOnTable().indexOf(this));
        }
    }
}

