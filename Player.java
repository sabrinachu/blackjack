import java.util.*;

public class Player {
  private ArrayList<Card> cardsInHand;
  private int currentTotalPoints;

  public Player() {
    cardsInHand = new ArrayList<Card>();
    currentTotalPoints = 0;
  }

  public void setCurrentTotalPoints(int currentTotalPoints) {
    this.currentTotalPoints = currentTotalPoints;
  }

  public int getCurrentTotalPoints() {
    return currentTotalPoints;
  }

  public void setCardsInHand(ArrayList<Card> cardsInHand) {
    this.cardsInHand = cardsInHand;
  }

  public ArrayList<Card> getCardsInHand() {
    return cardsInHand;
  }

  public Card addCard(Card card) {
    cardsInHand.add(card);

    if(cardsInHand. == 1)
    {
      if(currentTotalPoints > 10)
      {
        currentTotalPoints = currentTotalPoints + 1; 
      }
      else if (currentTotalPoints < 10)
      {
        currentTotalPoints = currentTotalPoints + 11;
      }
    }
    else if (card.getRank() <= 10)
    {
      currentTotalPoints = currentTotalPoints + card.getRank();
    }
    else if (card.getRank() > 10)
    {
      currentTotalPoints = currentTotalPoints + 10; 
    }
    
    return card; 
  }

  public boolean isBusted() {
    if (currentTotalPoints > 21) {
      return true;
    } else {
      return false;
    }
  }

  public int checksAce()
  {
    int numOfAces = 0; 
    for (int i = 0; i < cardsInHand.size(); i++)
    {
      if (cardsInHand.get(i)
    }
  }

  

}