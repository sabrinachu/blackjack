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

public class UserPanel extends JPanel {
  private JLabel playerTotalPoints;
  private Player user;
  private BufferedImage image;
  private BlackjackGame addedCard; 
  private Card card;
  
  
  public UserPanel()
  {
    setPreferredSize(new Dimension(200, 100));
    playerTotalPoints = new JLabel("Player's Current Total: " + user.getCurrentTotalPoints());
    add(playerTotalPoints);

    card = addedCard.getAddedCard();
    System.out.println("here: " + card);
  }
  
  public void ImagePanel()
  {
    try{
      image = ImageIO.read(new File("./pokerImages/" + card.getRank() + "," + card.getRank() + ".png"));
    }catch(IOException ex){
    }
     
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    g.drawImage(image, 10, 10, this); 
    
  }
}