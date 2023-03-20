package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Deck;
import sk.stuba.fei.uim.oop.game.Player;

import java.util.List;

public class Dynamite extends Cards {

    public Dynamite() {

        super(Color.BLUE);
    }

    @Override
    public void effect(Player sourcePlayer, List<Player> allPlayers, Deck deck) {
        int chance = getRandom().nextInt(8);
        if (chance == 0) {
            System.out.println("Dynamite exploded and you lost 3 health");
            sourcePlayer.removeCardFromTableToPile(sourcePlayer.getCardsOnTable().indexOf(this));
            sourcePlayer.recieveDamage(3);
        } else {
            System.out.println("Dynamite didn't explode");
            int currentPlayerIndex = allPlayers.indexOf(sourcePlayer);
            Player previousPlayer = allPlayers.get((currentPlayerIndex + allPlayers.size() - 1) % allPlayers.size());
            while (!previousPlayer.isActive()) {
                previousPlayer = allPlayers.get((allPlayers.indexOf(previousPlayer) + allPlayers.size() - 1) % allPlayers.size());
            }
            previousPlayer.getCardsOnTable().add(this);
            sourcePlayer.removeCardFromTable(sourcePlayer.getCardsOnTable().indexOf(this));
        }
    }
}

