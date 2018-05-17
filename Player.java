import java.util.*;

public class Player
{
  private List<Card> cardsInHand; 
  private int currentTotalPoint; 

  public Player ()
  {
    cardsInHand = new ArrayList<Card>(); 
    currentTotalPoint = 0; 
  }
  
  public void addCard(Card card)
  {
     cardsInHand.add(card);
     currentTotalPoint = currentTotalPoint + card.getRank();
  }
  
  public boolean isBustedOrNot(Card card)
  { 
    if (currentTotalPoint > 21)
    {
      return false; 
    }
    else {
      return true; 
    }
  }
}