import java.util.*;
import javax.swing.*;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
        for (int suit = 0; suit < 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                Card gameCard = new Card(suit, rank);
                cards.add(gameCard);  
            }
        }
        Collections.shuffle(cards);
    }

    public Card drawTopCard() {
      
      Card topCard = cards.remove(0);
      return topCard;
    }

}