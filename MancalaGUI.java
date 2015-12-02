//--------------------------------------------------------------
// MancalaGUI.java 
// Authors: Lily Westort
//
// Displays Mancala GUI
//--------------------------------------------------------------

import java.awt.*;
import javax.swing.*;

public class MancalaGUI {
   
   public static void main(String[] args){
      StartFrame frame = new StartFrame();
      frame.setPreferredSize(new Dimension(950, 780));
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      System.out.println(frame.getLocation());
           
      frame.pack();
      frame.setResizable(true);
      System.out.println(frame.getWidth());
      System.out.println(frame.getHeight());
      
      frame.setVisible(true);
   }
     
}
    