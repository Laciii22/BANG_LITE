package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Deck;
import sk.stuba.fei.uim.oop.game.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Cards {
    private final Color color;
    private final Random random;

    public Cards(Color color) {
        this.color = color;
        if (this instanceof Bang || this instanceof Dynamite || this instanceof Prison || this instanceof CatBalou){
            this.random = new Random();
        } else {
            this.random = null;
        }
    }

    public Color getColor() {
        return color;
    }

    protected Random getRandom() {
        return random;
    }

    public void effect(Player sourcePlayer, List<Player> allPlayers, Deck deck) {
    }

    public Player selectPlayer(Player sourcePlayer, List<Player> allPlayers) {
        ArrayList<Player> playersToShoot = new ArrayList<>();
        ArrayList<Integer> playerIndices = new ArrayList<>();
        for (int i = 0; i < allPlayers.size(); i++) {
            Player player = allPlayers.get(i);
            if (!player.equals(sourcePlayer) && player.isActive()) {
                playersToShoot.add(player);
                playerIndices.add(i);
            }
        }
        for (int i = 0; i < playersToShoot.size(); i++) {
            System.out.print((i + 1) + ") " + playersToShoot.get(i).getName() + " " + playersToShoot.get(i).getHealth() + " HP ");
            playersToShoot.get(i).printCardsOnTable(playersToShoot.get(i));

        }
        int index = ZKlavesnice.readInt("Select a player to target:") - 1;
        if (index >= 0 && index < playersToShoot.size()) {
            return allPlayers.get(playerIndices.get(index));
        } else {
            System.out.println("Invalid selection!");
            return selectPlayer(sourcePlayer, allPlayers);
        }
    }
}
