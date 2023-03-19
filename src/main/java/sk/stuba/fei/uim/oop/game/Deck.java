package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Cards> deck;
    static final int BARREL = 2;
    static final int INDIANS = 2;
    static final int JAIL = 3;
    static final int STAGECOACH = 4;
    static final int CATBALOU = 6;
    static final int BEER = 8;
    static final int MISSED = 15;
    static final int DYNAMITE = 3;
    static final int BANG = 30;

    public List<Cards> getDeck() {
        return deck;
    }


    public Deck(){
        this.deck = new ArrayList<>();
        for (int i = 0; i < BARREL ; i++ ){
            this.deck.add(new Barrel());
        }
        for (int i = 0; i < INDIANS; i++){
            this.deck.add(new Indians());
        }
        for (int i = 0; i < JAIL; i++){
            this.deck.add(new Jail());
        }

        for (int i = 0; i < STAGECOACH; i++){
            this.deck.add(new Stagecoach());
        }
        for (int i = 0; i < CATBALOU; i++){
            this.deck.add(new CatBalou());
        }
        for (int i = 0; i < BEER; i++){
            this.deck.add(new Beer());
        }
        for (int i = 0; i < MISSED; i++){
            this.deck.add(new Missed());
        }
        for (int i = 0; i < BANG; i++){
            this.deck.add(new Bang());
        }
        for (int i = 0; i < DYNAMITE; i++){
            this.deck.add(new Dynamite());

        }
        Collections.shuffle(this.deck);
    }
}
