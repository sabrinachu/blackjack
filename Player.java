/*************************************************************************************
  * Program: Player.java                                                             *
  ************************************************************************************
  * Author: Sabrina Chu                                                              *
  * Due Date: 6/5/2018                                                               *
  * Description: Creates my Player Object for Blackjack, takes care of all the       *
  *              behaviors and properties of the user and dealer                     *
  ***********************************************************************************/
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
    acePointAlreadyReduced = false; // keeps track of the aces that were already taken care of
  }

  // accessor and mutator methods for the variables
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

  // method that resets the player to allow for a new game
  public void resetPlayer() {
    currentTotalPoints = 0;
    cardsInHand.clear();
    numAce = 0;
    acePointAlreadyReduced = false;
  }

  // method to add a card into the player's hand of cards
  public Card addCard(Card card) {
    cardsInHand.add(card); // adds the new card

    // takes care of the special rules for an Ace
    if (card.getRank() == 1) {
      if (numAce == 0) {
        currentTotalPoints = currentTotalPoints + 11; // adds the Ace as 11 points if it's the first ace in the player's
                                                      // hand
      } else {
        currentTotalPoints = currentTotalPoints + 1; // adds the Ace as 1 point if there are already aces in the hand
      }
      numAce++; // the number of aces in the player's hand increases

    } else if (card.getRank() > 1 && card.getRank() <= 10) { // if the card is not an ace or above 10, it gets its rank
                                                             // normally
      currentTotalPoints = currentTotalPoints + card.getRank();
    } else if (card.getRank() > 10) { // if the card is a jack, queen or king, it can only add 10 points
      currentTotalPoints = currentTotalPoints + 10;
    }

    if (currentTotalPoints > 21) { // if the user or dealer is busted, then it will enter
      if (numAce != 0 && acePointAlreadyReduced == false) { // if aces are present in the hand and the ace has not been
                                                            // reduced yet
        currentTotalPoints = currentTotalPoints - 10; // changes the point value of the ace from 11 to 1
        acePointAlreadyReduced = true; // the ace has been reduced now
      }
    }
    return card;
  }

  // checks the player to see if they have over 21 points
  public boolean isBusted() {
    if (currentTotalPoints > 21) {
      return true;
    } else {
      return false;
    }
  }

  // helps to display the dealer's current points without giving away the hidden
  // card's value
  public int getSecondCardPoint() {
    int secondCardRank = cardsInHand.get(1).getRank();

    if (secondCardRank != 1 && secondCardRank <= 10) { // if the hidden card is not an Ace and is not a jack, queen, or
                                                       // king then it will return normally
      return secondCardRank;
    } else if (secondCardRank == 1) // if the hidden card is an Ace, it displays 11 as the current points
    {
      return 11;
    } else // if the hidden card is a jack, queen, or king, it will display 10 points
    {
      return 10;
    }

  }

}