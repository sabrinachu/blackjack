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
  private JLabel winner;
  private Card addedCard;
  private BufferedImage backImg; 
  private boolean userFinished; 

  public BlackjackGame() {

    this.user = new Player();
    this.dealer = new Player();
    this.cardDeck = new Deck();
    this.userFinished = false; 

    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(700, 400));

    hit = new JButton("Hit");
    hit.addActionListener(new buttonListener());

    stand = new JButton("Stand");
    stand.addActionListener(new buttonListener());

    playAgain = new JButton("Play Again!");
    playAgain.addActionListener(new buttonListener());

    buttonPanel = new JPanel();

    buttonPanel.setLayout(new GridLayout());
    buttonPanel.setPreferredSize(new Dimension(60, 40));
    buttonPanel.add(hit, 0, 0);
    buttonPanel.add(stand, 0, 1);
    buttonPanel.add(playAgain, 0, 2);

    winner = new JLabel();
    winner.setFont(new Font(null, Font.PLAIN, 15));

    scorePanel = new JPanel();
    scorePanel.setLayout(new GridLayout());
    scorePanel.setPreferredSize(new Dimension(60, 40));

    playerTotalPoints = new JLabel("   The Player's Current Points: " + user.getCurrentTotalPoints());
    dealerTotalPoints = new JLabel("The Dealer's Current Pointsbbbbbb: " + dealer.getCurrentTotalPoints());

    playerTotalPoints.setFont(new Font(null, Font.PLAIN, 15));
    dealerTotalPoints.setFont(new Font(null, Font.PLAIN, 15));

    scorePanel.add(playerTotalPoints, 0, 0);
    scorePanel.add(dealerTotalPoints, 0, 1);

    add(scorePanel, BorderLayout.NORTH);
    add(buttonPanel, BorderLayout.SOUTH);

    startGame();
  }

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

  public void startGame() {

    playAgain.setEnabled(false);

    addedCard = user.addCard(cardDeck.drawTopCard());
    addedCard = user.addCard(cardDeck.drawTopCard());

    playerTotalPoints.setText("   The Player's Current Points: " + Integer.toString(user.getCurrentTotalPoints()));
    System.out.println("original hand" + user.getCardsInHand() + "total: " + user.getCurrentTotalPoints());

    addedCard = dealer.addCard(cardDeck.drawTopCard());
    addedCard = dealer.addCard(cardDeck.drawTopCard());
    dealerTotalPoints.setText("The Dealer's Current Points: " + dealer.getCurrentTotalPoints());
    System.out.println("original dealer hand" + dealer.getCardsInHand() + "total: " + dealer.getCurrentTotalPoints());

  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawImage(g);
  }

  public void drawImage(Graphics g) {

      try{
        backImg = ImageIO.read(new File("./pokerImages/backCard.jpeg"));
      }catch(IOException ex){
      }

      int playerX = playerTotalPoints.getX() + 20;
      for (int i = 0; i < user.getCardsInHand().size(); i++) {
        BufferedImage tempImg = user.getCardsInHand().get(i).getImage();
        g.drawImage(tempImg, i * 35 + playerX, 50, 170, 260, this);
      }

      int dealerX = dealerTotalPoints.getX();
      for(int i = 0; i < dealer.getCardsInHand().size(); i++)
      {
        BufferedImage tempImg = dealer.getCardsInHand().get(i).getImage();
        if (i == 0 && userFinished == false)
        {
          g.drawImage(backImg, i * 35 + dealerX, 50, 170, 260, this);
        }
        else
        {
          g.drawImage(tempImg, i * 45 + dealerX, 50, 170, 260, this);
        }
        
        
      }

    repaint();
  }

  private class buttonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      if (event.getActionCommand() == "Hit") {

        playAgain.setEnabled(false);

        user.addCard(cardDeck.drawTopCard());

        playerTotalPoints.setText("   The Player's Current Points: " + Integer.toString(user.getCurrentTotalPoints()));
        System.out.println("added: " + user.getCardsInHand() + "total: " + user.getCurrentTotalPoints());


        if (user.isBusted() == true) {
          winner.setText("You lose");
          System.out.println("You lose");

          hit.setEnabled(false);
          stand.setEnabled(false);
          playAgain.setEnabled(true);
        }

      } else if (event.getActionCommand() == "Stand") {

        hit.setEnabled(false);
        stand.setEnabled(false);
        playAgain.setEnabled(false);

        userFinished = true; 

        while (dealer.getCurrentTotalPoints() <= 16) {

          dealer.addCard(cardDeck.drawTopCard());
          dealerTotalPoints.setText("The Dealer's Current Points: " + dealer.getCurrentTotalPoints());
        }

        System.out.println("User's Cards: " + user.getCardsInHand() + "total: " + user.getCurrentTotalPoints());
        System.out.println("Dealer's Cards: " + dealer.getCardsInHand() + "total: " + dealer.getCurrentTotalPoints());

        if (dealer.isBusted() == true) {
          System.out.println("Player wins");
          winner.setText("Player wins!");
        } else if (dealer.getCurrentTotalPoints() > user.getCurrentTotalPoints()) {
          System.out.println("Dealer wins");
          winner.setText("Dealer wins!");
        } else if (dealer.getCurrentTotalPoints() < user.getCurrentTotalPoints()) {
          System.out.println("Player wins this");
          winner.setText("Player wins this!");
        } else {
          System.out.println("It's a tie!");
          winner.setText("It's a tie!");
        }
        playAgain.setEnabled(true);
      } else if (event.getActionCommand() == "Play Again!") {
        hit.setEnabled(true);
        stand.setEnabled(true);
        playAgain.setEnabled(false);

        userFinished = false; 

        user.setCurrentTotalPoints(0);
        user.getCardsInHand().clear();

        dealer.setCurrentTotalPoints(0);
        dealer.getCardsInHand().clear();

        cardDeck.createDeck();

        winner.setText("");

        startGame();

      }

    }
  }

}