package com.g13.controller.arena.commands;

import com.g13.model.arena.Card;

import java.util.Collections;
import java.util.List;

public class DeckShuffler implements Command{

    List<Card> deck;

    public DeckShuffler(List<Card> deck){
        this.deck = deck;
    }

    public void execute(){
        Collections.shuffle(deck);
    }
}
