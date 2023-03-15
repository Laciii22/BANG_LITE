package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Deck;
import sk.stuba.fei.uim.oop.game.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Random;

public class CatBalou extends Cards {
    public CatBalou() {
        super(Color.BROWN);
    }

    @Override
    public void effect(Player sourcePlayer, ArrayList<Player> allPlayers, Deck deck) {
        Player target = sourcePlayer.selectPlayer(sourcePlayer, allPlayers);
        if (target.getHand().isEmpty() && target.getCardsOnTable().isEmpty()) {
            System.out.println("Cannot play Cat Balou on " + target.getName() + " as they have no cards on hand or table.");
            effect(sourcePlayer, allPlayers, deck);
            return;
        }

        String choice = ZKlavesnice.readString("Do you want to discard a card from " + target.getName() + "'s hand or table? (h/t): ");

        if (choice.equals("h")) {
            if (target.getHand().isEmpty()) {
                System.out.println(target.getName() + " has no cards on hand to discard.");
                effect(sourcePlayer, allPlayers, deck);
                return;
            }
            Random random = new Random();
            int index = random.nextInt(target.getHand().size());
            System.out.println("Discarding a card from " + target.getName() + "'s hand: " + target.getHand().get(index).getClass().getSimpleName());
            target.removeCard(index, deck);
        } else if (choice.equals("t")) {
            if (target.getCardsOnTable().isEmpty()) {
                System.out.println(target.getName() + " has no cards on table to discard.");
                return;
            }
            target.printCardsOnTable(target);
            int index = ZKlavesnice.readInt("Enter the index of the card you want to discard: ")-1;
            System.out.println("Discarding a card from " + target.getName() + "'s table: " + target.getCardsOnTable().get(index).getClass().getSimpleName());
            target.removeCardFromTable(index, deck);
        } else {
            System.out.println("Invalid choice, please enter 'h' or 't'.");
        }
        sourcePlayer.removeCard(sourcePlayer.getHand().indexOf(this), deck);
    }
}
