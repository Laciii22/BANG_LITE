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
                if (this.hasBang(player)) {
                    System.out.println("Player " + player.getName() + " has bang card and he shot an indian");
                } else {
                    System.out.println("Player " + player.getName() + " was attacked by an indian");
                    player.recieveDamage(allPlayers, 1);
                }
            }
        }
        sourcePlayer.removeCardToPile(sourcePlayer.getHand().indexOf(this));
    }

    private boolean hasBang(Player player) {
        for (int i = 0; i < player.getHand().size(); i++) {
            if (player.getHand().get(i) instanceof Bang) {
                player.removeCardToPile(i);
                return true;
            }
        }
        return false;
    }
}

