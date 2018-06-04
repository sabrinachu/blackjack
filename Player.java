import java.util.*;

public class Player {
  private ArrayList<Card> cardsInHand;
  private int currentTotalPoints;
  private int numAce;
  private boolean acePointAlreadyReduced;

  public Player() {
    cardsInHand = new ArrayList<Card>();
    currentTotalPoints = 0;
    numAce = 0;
    acePointAlreadyReduced = false;
  }

  public void resetPlayer() {
    currentTotalPoints = 0;
    cardsInHand.clear();
    numAce = 0;
    acePointAlreadyReduced = false;
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

    if (card.getRank() == 1) {
      if (numAce == 0) {
        currentTotalPoints = currentTotalPoints + 11;
      } else {
        currentTotalPoints = currentTotalPoints + 1;
      }
      numAce++;

    } else if (card.getRank() > 1 && card.getRank() <= 10) {
      currentTotalPoints = currentTotalPoints + card.getRank();
    } else if (card.getRank() > 10) {
      currentTotalPoints = currentTotalPoints + 10;
    }

    if (currentTotalPoints > 21) {
      if (numAce != 0 && acePointAlreadyReduced == false) {
        currentTotalPoints = currentTotalPoints - 10;
        acePointAlreadyReduced = true;
      }
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

  public int getSecondCardPoint()
  {
    int secondCardRank = cardsInHand.get(1).getRank();

    if (secondCardRank != 1 && secondCardRank <= 10) {
      return secondCardRank; 
    } 
    else if (secondCardRank == 1)
    {
      return 11; 
    }
    else 
    {
      return 10; 
    }


  }

}