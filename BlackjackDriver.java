/*************************************************************************************
  * Program: BlackjackDriver.java                                                      *
  ************************************************************************************
  * Author: Sabrina Chu                                                              *
  * Due Date: 6/5/2018                                                              *
  * Description: The driver file that creates the frame and displays the AboutMe     *
  *              panel                                                               *
  ***********************************************************************************/
import javax.swing.JFrame;
import javax.swing.UIManager;

public class BlackjackDriver 
{
  public static void main (String [] args)
  {
     try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
     } catch (Exception e) {
      e.printStackTrace();
     }
     
     Deck deck = new Deck();
     Card myCard = deck.drawTopCard();
     Player dealer = new Player();
     Player user = new Player();

     System.out.println(myCard.toString()); 
     dealer.addCard(deck.drawTopCard());
     user.addCard(deck.drawTopCard()); 

     JFrame frame = new JFrame("Blackjack"); 
     
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.getContentPane().add(new Blackjack());
     frame.pack();
     frame.setVisible(true);
    
  }
  

}