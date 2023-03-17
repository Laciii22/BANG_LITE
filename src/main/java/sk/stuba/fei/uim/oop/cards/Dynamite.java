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
    public void effect(Player sourcePlayer, ArrayList<Player> allPlayers, Deck deck) {
        Random random = new Random();
        int chance = random.nextInt(8);
        if (chance == 0) {
            System.out.println("Dynamite exploded and you lost 3 health");
            sourcePlayer.removeCardFromTable(sourcePlayer.getCardsOnTable().indexOf(this), deck);
            sourcePlayer.recieveDamage(allPlayers,3);
/*            if (sourcePlayer.isDead(allPlayers)) {
                sourcePlayer.removeCardsFromDeadPlayer(sourcePlayer, deck);
            }*/
        } else {
            System.out.println("Dynamite didn't explode");
            int currentPlayerIndex = allPlayers.indexOf(sourcePlayer);
            int previousPlayerIndex = currentPlayerIndex == 0 ? allPlayers.size() - 1 : currentPlayerIndex - 1;
            Player previousPlayer = allPlayers.get(previousPlayerIndex);
            previousPlayer.getCardsOnTable().add(this);
            sourcePlayer.removeCardFromTable(sourcePlayer.getCardsOnTable().indexOf(this));
        }
    }
}

