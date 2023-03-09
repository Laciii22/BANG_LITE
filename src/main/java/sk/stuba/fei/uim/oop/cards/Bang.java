package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Logic;
import sk.stuba.fei.uim.oop.game.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class Bang extends Cards {
    public Bang(){
        super(Color.BROWN);
    }

    @Override
    public void effect() {
        System.out.println("Select a player to target:");
        int index = ZKlavesnice.readInt("Select a player to target:")-1;
        Player target = Player.getAllPlayers().get(index);
        boolean missedCardUsed = false;
        if (target.hasMissed(target)) {
            ///todo - remove missed card from hand
        }
        else{
            target.recieveDamage(target );
            System.out.println(target.getName() + " has been hit!");
        }
            }
/*        for (Cards card : target.getHand()) {
            if (card instanceof Missed) {
                System.out.println(target.getName() + " has a Missed card in their hand. Do you want to use your Bang card again?");
                String response = ZKlavesnice.readString("[y/n]");
                if (response.equals("y")) {
                    missedCardUsed = true;
                    System.out.println(target.getName() + " used a Missed card to cancel your Bang!");
                    break;
                }
            }*/

}