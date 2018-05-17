/*************************************************************************************
  * Program: Blackjack.java                                                      *
  ************************************************************************************
  * Author: Sabrina Chu                                                              *
  * Due Date: 6/5/2018                                                              *
  * Description: The driver file that creates the frame and displays the AboutMe     *
  *              panel                                                               *
  ***********************************************************************************/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Blackjack extends JPanel
{
  private JButton hit; 
  private JButton stand; 
  private JPanel buttons; 
  
  public Blackjack()
  {
    
    hit = new JButton("Hit"); 
    hit.addActionListener(new buttonListener());
    
    stand = new JButton("Stand"); 
    stand.addActionListener(new buttonListener());
   
    buttons = new JPanel();
    buttons.add(hit); 
    buttons.add(stand); 
    
    add(buttons); 
    
    setBackground(Color.lightGray);
    setPreferredSize(new Dimension(1000, 700));  
  }
  
  private class buttonListener implements ActionListener 
  {
    public void actionPerformed(ActionEvent event) 
    {
      if( event.getActionCommand() == "Hit")
      {
        
      }
      else if (event.getActionCommand() == "Stand")
      {
        
      }
      
    }
  }
}