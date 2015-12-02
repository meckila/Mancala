//*******************************************************************************
// StartFrame.java 
// Authors: Amal Tidjani
//
// Creates the frame that displays the startup menu.
//********************************************************************************


import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;

public class StartFrame extends JFrame{
   private JButton btn1 = new JButton(new ImageIcon("Images/Start.png"));
   private JButton btn2 = new JButton(new ImageIcon("Images/HowToPlay.png"));
   private final int NUM = 2;
   private JPanel bkPanel = new BkImagePanel();
   private JPanel test = new JPanel();
   
   public StartFrame(){
      super();
      //Import font "Belta Regular"
      try{
        InputStream in = StartFrame.class.getResourceAsStream("belta-regular.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, in);
      }
      catch (Exception ex){
        System.out.println("Font couldn't be loaded.");
      }
    
   /*Create a panel displaying the background image. */
  // JPanel bkPanel = new BkImagePanel();
   bkPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 350, 150));
    
   /*Create a panel that holds the buttons. */
   JPanel panelBtn = new JPanel(new GridLayout(2, 0, 0, 20));
   panelBtn.setOpaque(false);
     
   /*Customize the buttons. */
   JButton[] btnArray = {btn1, btn2};
   ButtonListener listener = new ButtonListener();
   
   for (int i = 0; i < NUM; i++) {
      //btnArray[i].setBackground(new java.awt.Color(84, 179, 159));
      //btnArray[i].setForeground(new java.awt.Color(0, 0, 0));
      btnArray[i].setBorder(BorderFactory.createLineBorder(Color.black, 6));
      //btnArray[i].setFont(new java.awt.Font("Belta Regular", 1, 50));
      btnArray[i].setPreferredSize(new Dimension(300, 75));
      btnArray[i].addActionListener(listener);
      panelBtn.add(btnArray[i]);     
   }
   
   JPanel container = new JPanel();
   container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
   container.setSize(300, 300); 
   container.setOpaque(false);
   container.add(Box.createRigidArea(new Dimension(300, 400)));
   container.add(panelBtn);
   bkPanel.add(container);
   add(bkPanel, BorderLayout.CENTER);
   bkPanel.setVisible(true);
   }
   
   private class ButtonListener implements ActionListener{
   /*Determines which button was pressed and redirects
    * to the appropriate panel. */

      public void actionPerformed(ActionEvent event){
         if (event.getSource() == btn2) {
            JLabel picLabel = new JLabel(new ImageIcon("Images/Instructions.png"));
            JOptionPane.showMessageDialog(null, picLabel, "Instructions", JOptionPane.PLAIN_MESSAGE, null);
         }
         
         else if (event.getSource() == btn1) {
            LetsPlayMancala game = new LetsPlayMancala(); 
            MancalaPanel panel = new MancalaPanel(game);
            add(panel, BorderLayout.CENTER);
            bkPanel.setVisible(false);
         }
      }
   }
   
}
      