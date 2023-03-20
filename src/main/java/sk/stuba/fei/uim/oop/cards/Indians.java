package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.game.Deck;
import sk.stuba.fei.uim.oop.game.Player;

import java.util.ArrayList;
import java.util.List;

public class Indians extends Cards {
    public Indians() {
        super(Color.BROWN);
    }

    @Override
    public void effect(Player sourcePlayer, List<Player> allPlayers, Deck deck) {
        List<Player> playersToAttack = new ArrayList<>(allPlayers);
        playersToAttack.remove(sourcePlayer);
        for (Player player : playersToAttack) {
            if(player.isActive()) {
                if (player.hasBang(player, deck)) {
                    System.out.println("Player " + player.getName() + " has bang card and he shot an indian");
                } else {
                    System.out.println("Player " + player.getName() + " was attacked by an indian");
                    player.recieveDamage(allPlayers, 1);
                }
            }
        }
        sourcePlayer.removeCard(sourcePlayer.getHand().indexOf(this), deck);
    }
}

