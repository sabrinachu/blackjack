/*************************************************************************************
  * Program: BlackjackDriver.java                                                      *
  ************************************************************************************
  * Author: Sabrina Chu                                                              *
  * Due Date: 6/5/2018                                                               *
  * Description:           *
  ***********************************************************************************/
import javax.swing.JFrame;
import javax.swing.UIManager;

public class BlackjackDriver {
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    JFrame frame = new JFrame("Blackjack");

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new BlackjackGame());
    frame.pack();
    frame.setVisible(true);

  }

}