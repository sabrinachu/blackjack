import java.util.*;

public class Deck 
{
    private List<Card> cards; 
    public Deck()
    {
        cards = new ArrayList<Card>(); 
        for (int suit = 0; suit < 3; suit++)
        {
            for (int rank = 0; rank < 13; rank++)
            {
                Card gameCard = new Card(suit, rank); 
                cards.add(gameCard); 
            }
        }
        Collections.shuffle(cards); 

    }

    public Card drawTopCard()
    {
        Card topCard = cards.remove(0);

        return topCard; 
    }

}