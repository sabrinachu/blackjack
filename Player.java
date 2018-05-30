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
    currentTotalPoints = currentTotalPoints + card.getRank();
    return card; 
  }

  public boolean isBusted() {
    if (currentTotalPoints > 21) {
      return true;
    } else {
      return false;
    }
  }

  

}