/*************************************************************************************
  * Program: Deck.java                                                               *
  ************************************************************************************
  * Author: Sabrina Chu                                                              *
  * Due Date: 6/5/2018                                                               *
  * Description: Creates and resets my Deck for Blackjack. Allows people to draw a   *
  *              card here                                                           *
  ***********************************************************************************/

import java.util.*;
import javax.swing.*;

public class Deck {
    private ArrayList<Card> cards; // creates an ArrayList filled with cards to store the player's cards

    public Deck() {
        cards = new ArrayList<Card>();
        createDeck(); // calls createDeck
    }

    public Card drawTopCard() {
        Card topCard = cards.remove(0); // if a card is drawn, the first card is removed from the pile
        return topCard;
    }

    public void createDeck() {
        cards.clear(); // clears the old cards
        // goes through a loop to create all 52 cards
        for (int suit = 0; suit < 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                Card gameCard = new Card(suit, rank);
                cards.add(gameCard);
            }
        }
        Collections.shuffle(cards); // shuffles the cards to make the deck random
    }

}