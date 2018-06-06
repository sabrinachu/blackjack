/*************************************************************************************
  * Program: BlackjackGame.java                                                      *
  ************************************************************************************
  * Author: Sabrina Chu                                                              *
  * Due Date: 6/5/2018                                                               *
  * Description: Takes care of the actions that happen during the game and creates   *
  *              the whole game and the Panels. Draws the cards in the game onto     * 
  *              my main panel, displays points and buttons as well                  *
  ***********************************************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;

public class BlackjackGame extends JPanel {
  private Player user;
  private Player dealer;
  private Deck cardDeck;
  private JButton hit;
  private JButton stand;
  private JButton playAgain;
  private JPanel buttonPanel;
  private JPanel scorePanel;
  private JLabel playerTotalPoints;
  private JLabel dealerTotalPoints;
  private String winner;
  private Card addedCard;
  private BufferedImage backImg;
  private boolean userFinished;

  public BlackjackGame() {

    this.user = new Player();
    this.dealer = new Player();
    this.cardDeck = new Deck();
    this.userFinished = false;
    winner = "";

    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(1000, 600));
    setBackground(new Color(8, 117, 19)); //created my own shade of green
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //creates a green border around the panel

    // creates the hit card button
    hit = new JButton("Hit");
    hit.addActionListener(new ButtonListener());
    hit.setFont(new Font("Verdana", Font.PLAIN, 20)); // changes the size and font of the words on the button

    // creates the stand button
    stand = new JButton("Stand");
    stand.addActionListener(new ButtonListener());
    stand.setFont(new Font("Verdana", Font.PLAIN, 20)); // changes the size and font of the words on the button

    // creates a button to allow the user to play again
    playAgain = new JButton("Play Again!");
    playAgain.addActionListener(new ButtonListener());
    playAgain.setFont(new Font("Verdana", Font.PLAIN, 20)); // changes the size and font of the words on the button

    // creates a panel to store all buttons
    buttonPanel = new JPanel();

    buttonPanel.setLayout(new GridLayout());
    buttonPanel.setPreferredSize(new Dimension(70, 50));
    buttonPanel.add(hit, 0, 0);
    buttonPanel.add(stand, 0, 1);
    buttonPanel.add(playAgain, 0, 2);

    // creates a panel to keep track of the labels
    scorePanel = new JPanel();
    scorePanel.setLayout(new GridLayout());
    scorePanel.setPreferredSize(new Dimension(70, 50));

    // keeps track of the player's and dealer's current points
    playerTotalPoints = new JLabel("   The Player's Current Points: " + user.getCurrentTotalPoints());
    dealerTotalPoints = new JLabel("The Dealer's Current Points: " + dealer.getCurrentTotalPoints());

    // changes the font of the JLabel
    playerTotalPoints.setFont(new Font(null, Font.BOLD, 20));
    dealerTotalPoints.setFont(new Font(null, Font.BOLD, 20));

    scorePanel.add(playerTotalPoints, 0, 0);
    scorePanel.add(dealerTotalPoints, 0, 1);

    add(scorePanel, BorderLayout.NORTH);
    add(buttonPanel, BorderLayout.SOUTH);

    // calls the method to start dealing cards
    startGame();
  }

  // accessor and mutator methods for the private variables
  public void setUser(Player user) {
    this.user = user;
  }

  public Player getUser() {
    return user;
  }

  public void setDealer(Player dealer) {
    this.dealer = dealer;
  }

  public Player getDealer() {
    return dealer;
  }

  public void setCardDeck(Deck cardDeck) {
    this.cardDeck = cardDeck;
  }

  public Deck getCardDeck() {
    return cardDeck;
  }

  public void setAddedCard(Card addedCard) {
    this.addedCard = addedCard;
  }

  public Card getAddedCard() {
    return addedCard;
  }

  // starts the game by adding the first two cards to the player's hand of cards
  // and the dealer's hand
  public void startGame() {

    // doesn't allow the user to press the play again button
    playAgain.setEnabled(false);

    addedCard = user.addCard(cardDeck.drawTopCard());
    addedCard = user.addCard(cardDeck.drawTopCard());

    // changes the JLabel to the new amount of points
    playerTotalPoints.setText("   The Player's Current Points: " + Integer.toString(user.getCurrentTotalPoints()));

    addedCard = dealer.addCard(cardDeck.drawTopCard());
    addedCard = dealer.addCard(cardDeck.drawTopCard());

    // sets the JLabel for the dealer's points
    dealerTotalPoints.setText("The Dealer's Current Points: " + dealer.getSecondCardPoint());

  }

  // calls the drawImage method to draw the cards onto the panel
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawImage(g);
  }

  // draws the cards onto the screen
  public void drawImage(Graphics g) {

    // gets the card image
    try {
      backImg = ImageIO.read(new File("./pokerImages/backCard.jpeg"));
    } catch (IOException ex) {
    }

    int playerX = playerTotalPoints.getX() + 20;
    // draws all of the cards in the player's hand
    for (int i = 0; i < user.getCardsInHand().size(); i++) {
      BufferedImage tempImg = user.getCardsInHand().get(i).getImage();
      g.drawImage(tempImg, i * 35 + playerX, 150, 170, 260, this); // sets the location of where the card is drawn and the drawing size of the image
    }

    int dealerX = dealerTotalPoints.getX();
    // draws all of the cards in the dealer's hand
    for (int i = 0; i < dealer.getCardsInHand().size(); i++) {
      BufferedImage tempImg = dealer.getCardsInHand().get(i).getImage();
      if (i == 0 && userFinished == false) // instead of calling the original photo, calls the image to display the back of card for the hidden card
      {
        g.drawImage(backImg, i * 35 + dealerX, 150, 170, 260, this); // sets the location of where the card is drawn and the drawing size of the image
      } else // draws all the cards normally
      {
        g.drawImage(tempImg, i * 45 + dealerX, 150, 170, 260, this); // sets the location of where the card is drawn and the drawing size of the image
      }

      g.setFont(new Font("verdana", Font.PLAIN, 30)); // changes the font of the winner
      g.drawString(winner, 55, 500); // draws the string to display the winner
      g.setColor(Color.white); // changes the color of the winner string

    }

    repaint(); // calls the method repaint to repaint the panel for each card to be displayed
  }

  // listens for which button the user presses
  private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      if (event.getActionCommand() == "Hit") {

        playAgain.setEnabled(false); // doesn't allow the user to press the button "play again"

        user.addCard(cardDeck.drawTopCard()); // adds the new card into the player's hand

        playerTotalPoints.setText("   The Player's Current Points: " + Integer.toString(user.getCurrentTotalPoints())); // displays the new current points

        // calls the isBusted method to check if the user's points is above 21
        if (user.isBusted() == true) {
          winner = "Busted!";

          hit.setEnabled(false); // doesn't allow the user to press the hit button
          stand.setEnabled(false); // doesn't allow the user to press the stand button
          playAgain.setEnabled(true); // opens the Play Again button for the user to press
        }

      } else if (event.getActionCommand() == "Stand") {

        hit.setEnabled(false); // doesn't allow the user to hit anymore cards
        stand.setEnabled(false); // doesn't allow the user to press stand again
        playAgain.setEnabled(false); // doesn't allow the user to press Play Again

        userFinished = true; // the user is now finished playing
        dealerTotalPoints.setText("The Dealer's Current Points: " + dealer.getCurrentTotalPoints());

        // adds cards to the dealer's hand if their points are less than or equal to 16
        while (dealer.getCurrentTotalPoints() <= 16) {

          dealer.addCard(cardDeck.drawTopCard()); // adds the card to the dealer's hand
          dealerTotalPoints.setText("The Dealer's Current Points: " + dealer.getCurrentTotalPoints()); // displays the dealer's new points
        }

        // checks who the winner is
        if (dealer.isBusted() == true) {
          winner = "Player wins!!";
        } else if (dealer.getCurrentTotalPoints() > user.getCurrentTotalPoints()) {
          winner = "Dealer wins!";
        } else if (dealer.getCurrentTotalPoints() < user.getCurrentTotalPoints()) {
          winner = "Player wins!!";
        } else {
          winner = "It's a tie!";
        }
        playAgain.setEnabled(true); // allows the user to play again
      } else if (event.getActionCommand() == "Play Again!") {
        hit.setEnabled(true); // if the user plays again, the hit button is reopened
        stand.setEnabled(true); // if the user plays again, the stand button is reopened
        playAgain.setEnabled(false); // doesn't allow the user to press the Play Again button

        userFinished = false; // resets the userFinished back to false

        user.resetPlayer(); // calls the resetPlayer method
        dealer.resetPlayer(); // calls the resetPlayer method
        cardDeck.createDeck(); // creates a new deck
        winner = "";

        startGame(); // calls the game to start again

      }

    }
  }

}