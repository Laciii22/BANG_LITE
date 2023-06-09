package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Deck;
import sk.stuba.fei.uim.oop.game.Player;

import java.util.List;

public class Bang extends Cards {
    public Bang() {
        super(Color.BROWN);
    }

    @Override
    public void effect(Player sourcePlayer, List<Player> allPlayers, Deck deck) {
        Player target = this.selectPlayer(sourcePlayer, allPlayers);
        if (this.hasBarrel(target)) {
            int chance = getRandom().nextInt(4);
            if (chance == 0) {
                System.out.println(target.getName() + " has hidden behind a barrel and missed your bang!");
                sourcePlayer.removeCardToPile(sourcePlayer.getHand().indexOf(this));
                return;
            }
            else{
                System.out.println(target.getName() + " couldn't hide behind a barrel");
            }
        }
        if (this.hasMissed(target)) {
            System.out.println(target.getName() + " has missed card and he missed your bang");
        }
        else{
            System.out.println(target.getName() + " was shot by " + sourcePlayer.getName());
            target.recieveDamage(allPlayers,1);
        }
        sourcePlayer.removeCardToPile(sourcePlayer.getHand().indexOf(this));
    }

    private boolean hasMissed(Player player) {
        for (int i = 0; i < player.getHand().size(); i++) {
            if (player.getHand().get(i) instanceof Missed) {
                player.removeCardToPile(i);
                return true;
            }
        }
        return false;
    }
    private boolean hasBarrel(Player player) {
        for (int i = 0; i < player.getCardsOnTable().size(); i++) {
            if (player.getCardsOnTable().get(i) instanceof Barrel) {
                return true;
            }
        }
        return false;
    }
}