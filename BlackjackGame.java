import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BlackjackGame extends JPanel {
  private Player user;
  private Player dealer;
  private Deck cardDeck;
  private JButton hit;
  private JButton stand;
  private JPanel buttonPanel;  

  public BlackjackGame() {
    
    this.user = new Player();
    this.dealer = new Player();
    this.cardDeck = new Deck();

    setLayout(new BorderLayout()); 
    setPreferredSize(new Dimension(500, 300));
    setBackground(Color.pink);

    hit = new JButton("Hit");
    hit.addActionListener(new buttonListener());

    stand = new JButton("Stand");
    stand.addActionListener(new buttonListener());

    buttonPanel = new JPanel();
    
    buttonPanel.setLayout(new GridLayout());
    buttonPanel.add(hit, 0, 0) ;
    buttonPanel.add(stand, 0, 1);
    
    buttonPanel.setPreferredSize(new Dimension(100, 50));
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
    user.addCard(cardDeck.drawTopCard());
    user.addCard(cardDeck.drawTopCard());
    System.out.println("original hand" + user.getCardsInHand() + "total: " + user.getCurrentTotalPoints());

    dealer.addCard(cardDeck.drawTopCard());
    dealer.addCard(cardDeck.drawTopCard());
    System.out.println("original dealer hand" + dealer.getCardsInHand() + "total: " + dealer.getCurrentTotalPoints()); 
  }

  private class buttonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      if (event.getActionCommand() == "Hit") {

        user.addCard(cardDeck.drawTopCard());
        System.out.println("added: " + user.getCardsInHand() + "total: " + user.getCurrentTotalPoints());
        if(user.isBusted() == true)
        {
          System.out.println("You lose");
        }
        

      } else if (event.getActionCommand() == "Stand") {

        while (dealer.getCurrentTotalPoints() <= 16)
        {
          dealer.addCard(cardDeck.drawTopCard());  
        } 
        
        System.out.println("User's Cards: " + user.getCardsInHand() + "total: " + user.getCurrentTotalPoints());
        System.out.println("Dealer's Cards: " + dealer.getCardsInHand() + "total: " + dealer.getCurrentTotalPoints());

        if (dealer.isBusted() == true)
        {
            System.out.println("Player wins");
        }
        else if (dealer.getCurrentTotalPoints() > user.getCurrentTotalPoints())
        {
          System.out.println("Dealer wins");
        }
        else if(dealer.getCurrentTotalPoints() < user.getCurrentTotalPoints())
        {
          System.out.println("Player wins this");
        }
        else{
          System.out.println("It's a tie!");
        }
      }
    }
  }

}