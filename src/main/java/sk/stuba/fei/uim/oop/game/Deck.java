package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Cards> deck;

    public List<Cards> getDeck() {
        return deck;
    }


    public Deck(){
        this.deck = new ArrayList<>();
        for (int i = 0; i < 2 ; i++ ){
            this.deck.add(new Barrel());
            this.deck.add(new Indians());
        }
        for (int i = 0; i < 3; i++){
            this.deck.add(new Jail());
        }

        for (int i = 0; i < 4; i++){
            this.deck.add(new Stagecoach());
        }
        for (int i = 0; i < 6; i++){
            this.deck.add(new CatBalou());
        }
        for (int i = 0; i < 8; i++){
            this.deck.add(new Beer());
        }
        for (int i = 0; i < 15; i++){
            this.deck.add(new Missed());
        }
        for (int i = 0; i < 30; i++){
            this.deck.add(new Bang());
        }
        for (int i = 0; i < 15; i++){
            this.deck.add(new Dynamite());

        }
        Collections.shuffle(this.deck);
    }

    public int getDeckSize(){
        return deck.size();
    }


}
