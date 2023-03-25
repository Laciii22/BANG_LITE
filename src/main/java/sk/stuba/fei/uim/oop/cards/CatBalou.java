package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Deck;
import sk.stuba.fei.uim.oop.game.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.List;

public class CatBalou extends Cards {
    public CatBalou() {
        super(Color.BROWN);
    }

    @Override
    public void effect(Player sourcePlayer, List<Player> allPlayers, Deck deck) {
        Player target = selectPlayer(sourcePlayer, allPlayers);
        int index;

        if (target.getHand().isEmpty() && target.getCardsOnTable().isEmpty()) {
            System.out.println("Cannot play Cat Balou on " + target.getName() + " as they have no cards on hand or table, you can play it on another player again.");
            return;
        }

        boolean validChoice = false;
        while (!validChoice) {
            String choice = ZKlavesnice.readString("Do you want to discard a card from " + target.getName() + "'s hand or table? (h/t): ");
            if (choice.equalsIgnoreCase("h")) {
                if (target.getHand().isEmpty()) {
                    System.out.println(target.getName() + " has no cards on hand to discard.");
                    continue;
                }
                index = getRandom().nextInt(target.getHand().size());
                System.out.println("TUTU " + index);
                System.out.println("Discarding a card from " + target.getName() + "'s hand: " + target.getHand().get(index).getClass().getSimpleName());
                target.removeCardToPile(index);
                validChoice = true;
            } else if (choice.equalsIgnoreCase("t")) {
                if (target.getCardsOnTable().isEmpty()) {
                    System.out.println(target.getName() + " has no cards on table to discard.");
                    continue;
                }
                target.printCardsOnTable(target);
                do {
                    index = ZKlavesnice.readInt("Enter the index of the card you want to discard: ") - 1;
                } while (index < 0 || index >= target.getCardsOnTable().size());
                System.out.println("Discarding a card from " + target.getName() + "'s table: " + target.getCardsOnTable().get(index).getClass().getSimpleName());
                target.removeCardFromTableToPile(index);
                validChoice = true;
            } else {
                System.out.println("Invalid choice, please enter 'h' or 't'.");
            }
        }
        sourcePlayer.removeCardToPile(sourcePlayer.getHand().indexOf(this));
    }
}

