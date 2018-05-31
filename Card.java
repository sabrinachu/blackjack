import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.*;

public class Card {
  private int rank;
  private int suit; // 0 = spades, 1 = heart, 2 = diamond, 3 = club
  private BufferedImage image; 

  public Card(int suit, int rank) {
    this.suit = suit;
    this.rank = rank;

    try{
      this.image = ImageIO.read(new File("./pokerImages/" + this.rank + "," + this.suit + ".png"));
    }catch(IOException ex){
    }

  }

  public void setRank(int rank) {
    this.rank = rank;
  }

  public int getRank() {
    return rank;
  }

  public void setSuit(int suit) {
    this.suit = suit;
  }

  public int getSuit() {
    return suit;
  }

  public void setImage(BufferedImage image)
  {
    this.image = image;
  }

  public BufferedImage getImage()
  {
    return image; 
  }
  public String toString() {
    String result = "";

    result += "Rank: " + rank + " Suit: " + suit;
    return result;
  }

}