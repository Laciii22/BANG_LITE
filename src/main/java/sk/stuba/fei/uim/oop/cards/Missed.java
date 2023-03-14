package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Deck;
import sk.stuba.fei.uim.oop.game.Player;

import java.util.ArrayList;

public class Missed extends Cards{
    public Missed() {
        super(Color.BROWN);
    }
    public void effect(Player fromPlayer, ArrayList<Player> allPlayers, Deck deck) {
        System.out.println("You cannot use missed card");
    }
}
