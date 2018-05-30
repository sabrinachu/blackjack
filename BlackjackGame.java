import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class BlackjackGame extends JPanel {
  private Player user;
  private Player dealer;
  private Deck cardDeck;
  private JButton hit;
  private JButton stand;
  private JPanel buttonPanel;
  private JPanel dealerPanel;
  private JPanel userPanel; 
  private JPanel cardPanel; 
  private JLabel playerTotalPoints;
  private JLabel dealerTotalPoints;
  private JLabel winner; 
  private JLabel cardImg; 
  private BufferedImage image;
  private Card addedCard; 
  private Card dealerCard; 
  

  public BlackjackGame() {
    
    this.user = new Player();
    this.dealer = new Player();
    this.cardDeck = new Deck();

    setLayout(new BorderLayout()); 
    setPreferredSize(new Dimension(600, 400));
  
    
    hit = new JButton("Hit");
    hit.addActionListener(new buttonListener());

    stand = new JButton("Stand");
    stand.addActionListener(new buttonListener());

    buttonPanel = new JPanel();
    dealerPanel = new JPanel(); 
    
    userPanel = new JPanel(); 
    userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.PAGE_AXIS));
    
    cardPanel = new JPanel(); 
    
    winner = new JLabel(); 
    userPanel.add(winner, Box.createRigidArea(new Dimension(100, 200))); 
    
    cardImg = new JLabel(); 
    userPanel.add(cardImg); 
    
    buttonPanel.setLayout(new GridLayout());
    buttonPanel.add(hit, 0,0 ) ;
    buttonPanel.add(stand, 0,1);
    
    
    
    playerTotalPoints = new JLabel("The Player's Current Points: " + user.getCurrentTotalPoints()); 
    add(playerTotalPoints);
    
    dealerTotalPoints = new JLabel("The Dealer's Current Points: " + dealer.getCurrentTotalPoints()); 
    add(dealerTotalPoints); 
    
    cardPanel.setLayout(new GridLayout());
    
    cardPanel.add(userPanel, 0,0);
    userPanel.add(playerTotalPoints); 
    
    cardPanel.add(dealerPanel, 0,1); 
    dealerPanel.add(dealerTotalPoints); 
    
    add(cardPanel, BorderLayout.CENTER); 
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

  public void startGame() {
    
    addedCard = user.addCard(cardDeck.drawTopCard());
    addedCard = user.addCard(cardDeck.drawTopCard());
    System.out.println("original hand" + user.getCardsInHand() + "total: " + user.getCurrentTotalPoints());
    
    ImagePanel();
    
    addedCard = dealer.addCard(cardDeck.drawTopCard());
    addedCard = dealer.addCard(cardDeck.drawTopCard());
    System.out.println("original dealer hand" + dealer.getCardsInHand() + "total: " + dealer.getCurrentTotalPoints()); 
     
    ImagePanel();
  }
  
  public void ImagePanel()
  {
    try{
      image = ImageIO.read(new File("./pokerImages/" + addedCard.getRank() + "," + addedCard.getSuit() + ".png"));
    }catch(IOException ex){
    }
     
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    
    g.drawImage(image, 10, 10, userPanel); 
    
  }
  
  private class buttonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      if (event.getActionCommand() == "Hit") {

        user.addCard(cardDeck.drawTopCard());
        playerTotalPoints.setText("The Player's Current Points: " + Integer.toString(user.getCurrentTotalPoints()));
        System.out.println("added: " + user.getCardsInHand() + "total: " + user.getCurrentTotalPoints());
        
        if(user.isBusted() == true)
        {
          System.out.println("You lose");
        }
        

      } else if (event.getActionCommand() == "Stand") {
                
        while (dealer.getCurrentTotalPoints() <= 16)
        {
          dealer.addCard(cardDeck.drawTopCard());
          dealerTotalPoints.setText("The Dealer's Current Points: " + dealer.getCurrentTotalPoints()); 
        } 
        
        System.out.println("User's Cards: " + user.getCardsInHand() + "total: " + user.getCurrentTotalPoints());
        System.out.println("Dealer's Cards: " + dealer.getCardsInHand() + "total: " + dealer.getCurrentTotalPoints());

        if (dealer.isBusted() == true)
        {
          System.out.println("Player wins");
          winner.setText("Player wins!"); 
        }
        else if (user.isBusted() == true)
        {
          System.out.println("Dealer wins this!");
          winner.setText("Dealer wins this!"); 
        }
        else if (dealer.getCurrentTotalPoints() > user.getCurrentTotalPoints())
        {
          System.out.println("Dealer wins");
          winner.setText("Dealer wins!"); 
        }
        else if(dealer.getCurrentTotalPoints() < user.getCurrentTotalPoints())
        {
          System.out.println("Player wins this");
          winner.setText("Player wins this!"); 
        }
        else{
          System.out.println("It's a tie!");
          winner.setText("It's a tie!"); 
        }
      }
        
    }
  }

}