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

    if (card.getRank() == 1) {
      currentTotalPoints = currentTotalPoints + 11;
    } else if (card.getRank() > 1 && card.getRank() <= 10) {
      currentTotalPoints = currentTotalPoints + card.getRank();
    } else if (card.getRank() > 10) {
      currentTotalPoints = currentTotalPoints + 10;
    }
    
    if (currentTotalPoints > 21) {
      int numOfAces = 0;
      for (int i = 0; i < cardsInHand.size(); i++) {
        if (cardsInHand.get(i).getRank() == 1) {
          numOfAces++;
        }
      }
      if (numOfAces != 0)
      {
        while (numOfAces >= 1 || currentTotalPoints > 21) {
          numOfAces--;
          currentTotalPoints = currentTotalPoints - 10;
        }
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

  public void checkAce() {
    int numOfAces = 0;
    if (currentTotalPoints > 21) {
      for (int i = 0; i <= cardsInHand.size(); i++) {
        if (cardsInHand.get(i).getRank() == 1) {
          numOfAces++;
        }
      }

      while (numOfAces >= 1 || currentTotalPoints > 21) {
        numOfAces--;
        currentTotalPoints = currentTotalPoints - 10;
      }

    }

  }

}