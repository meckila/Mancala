//*******************************************************************************
// MancalaPanel.java 
// Authors: Amal Tidjani
//
// Creates a panel with a playable version of the mancala game.
//********************************************************************************

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javafoundations.LinkedStack;
import javax.swing.ImageIcon;

public class MancalaPanel extends JPanel{
   
   //------------------------------------------------------------
   //Instance variables
   //------------------------------------------------------------
   
   private JFrame resultFrame;
   private JPanel resultNorth, resultCenter, resultSouth;
   private BkImagePanel boardResultPanel;
   private LinkedStack<String> winners; //ADT (Stores Previous Winners)
   private JButton[] buttons; //Array of smallpit buttons
   private JButton b13, b6; //Collection Pit Buttons
   private final JButton instructBtn = new JButton(new ImageIcon("Images/Help.png"));
   private final JButton restartBtn = new JButton(new ImageIcon("Images/Restart.png"));
   private final JButton playAgainBtn = new JButton(new ImageIcon("Images/PlayAgain.png"));
   private final JButton scoresBtn = new JButton(new ImageIcon("Images/PreviousResults.png"));
   private final JButton quitBtn = new JButton(new ImageIcon("Images/Quit.png"));
   private String imageFile;
   private JLabel statusLabel1, statusLabel2, p1Label, p2Label, winnersLabel, result, scores;
   private final int SMALL_PIT_COUNT;
   protected LetsPlayMancala game;
     
   public MancalaPanel(LetsPlayMancala game){
     
     //------------------------------------------------------------------------------
     // Import font "Belta Regular"
     //-----------------------------------------------------------------------------
      try{
         InputStream in = MancalaPanel.class.getResourceAsStream("belta-regular.ttf");
         Font font = Font.createFont(Font.TRUETYPE_FONT, in);     
      }
      catch (Exception ex){
         System.out.println("Font couldn't be loaded.");
         
      }
      
      //----------------------------------------------------------------------------
      // Set background image
      //----------------------------------------------------------------------------
      imageFile = "/Images/GameBackground.png";
      this.game = game;
      SMALL_PIT_COUNT = 12;
      
      setLayout(new BorderLayout());
      
      
      add(northWindowPanel(), BorderLayout.NORTH);
      add(westWindowPanel(), BorderLayout.WEST);
      add(makeBoardPanel(), BorderLayout.CENTER);
      add(eastWindowPanel(), BorderLayout.EAST);
      add(southWindowPanel(), BorderLayout.SOUTH);
      
      winners = new LinkedStack<String>();
      winnersLabel = new JLabel();
      
      resultFrame = new JFrame();
      boardResultPanel = new BkImagePanel("/Images/board2.png");
      resultNorth = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 50));
      resultNorth.setOpaque(false);
      resultCenter = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
      resultCenter.setOpaque(false);
      resultSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
      resultSouth.setOpaque(false);
      
      playAgainBtn.setPreferredSize(new Dimension(240, 80));
      playAgainBtn.setBackground(new Color(135, 17, 76));
      playAgainBtn.setForeground(Color.white);
      playAgainBtn.setBorder(BorderFactory.createLineBorder(Color.white, 6));
      playAgainBtn.setFont(new Font("Belta Regular", Font.BOLD, 40));
      playAgainBtn.addActionListener(new ButtonListener());
      
      scoresBtn.setPreferredSize(new Dimension(340, 80));
      scoresBtn.setBorder(BorderFactory.createLineBorder(Color.white, 6));
      scoresBtn.addActionListener(new ButtonListener());
     
      
      quitBtn.setPreferredSize(new Dimension(150, 80));
      quitBtn.setBorder(BorderFactory.createLineBorder(Color.white, 6));     
      quitBtn.addActionListener(new ButtonListener());
      
      result = new JLabel();
      result.setFont(new Font("Belta Regular", Font.BOLD, 40));
      result.setForeground(Color.white);
      result.setBackground(new Color(0, 158, 121));
      result.setBorder(BorderFactory.createLineBorder(Color.white, 6));
      result.setOpaque(true);
      result.setPreferredSize(new Dimension(380, 80));
      
      winnersLabel = new JLabel();
      winnersLabel.setFont(new Font("Belta Regular", Font.BOLD, 40));
      winnersLabel.setForeground(Color.white);
      winnersLabel.setPreferredSize(new Dimension(420, 200));
      
      boardResultPanel.setLayout(new BorderLayout(50, 50));
      boardResultPanel.setPreferredSize(new Dimension(800, 700));
      boardResultPanel.setBackground(Color.yellow);
      boardResultPanel.add(resultNorth, BorderLayout.NORTH);
      boardResultPanel.add(resultCenter, BorderLayout.CENTER);
      boardResultPanel.add(resultSouth, BorderLayout.SOUTH);
      
      resultCenter.add(winnersLabel);
      resultSouth.add(scoresBtn);
      resultSouth.add(playAgainBtn);
      resultSouth.add(quitBtn);
      
      resultFrame.add(boardResultPanel);    
   }
   
   //-------------------------------------------------------------------------------
   // Create Background Image
   //-------------------------------------------------------------------------------
   public void paintComponent(Graphics g){
      /*Create image icon to get image*/
      ImageIcon icon = new ImageIcon(getClass().getResource(imageFile));
      Image image = icon.getImage();
      
      /*Draw image on the panel*/
      super.paintComponent(g);      
      if (image != null)
         g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
   }
   
   
   //-------------------------------------------------------------------------------
   // Creates the Panel Displaying Game Board
   //-------------------------------------------------------------------------------
   private BkImagePanel makeBoardPanel(){
      BkImagePanel boardPanel = new BkImagePanel("/Images/board2.png");
      boardPanel.setLayout(new BorderLayout());
      boardPanel.setPreferredSize(new Dimension(300, 150));
      boardPanel.setBorder(BorderFactory.createLineBorder(Color.black, 6));
      
      boardPanel.add(northBoardPanel(), BorderLayout.NORTH);
      boardPanel.add(centerBoardPanel(), BorderLayout.CENTER); 
      boardPanel.add(southBoardPanel(), BorderLayout.SOUTH);
      boardPanel.add(westBoardPanel(), BorderLayout.WEST);
      boardPanel.add(eastBoardPanel(), BorderLayout.EAST);
      
      return boardPanel;     
   }
   
   /*Creates the North section of the mancala board. */
   private JPanel northBoardPanel(){
      JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 300, 30));
      northPanel.setOpaque(false);
      northPanel.setPreferredSize(new Dimension(600, 100));
      p2Label = new JLabel("Player 2");
      p2Label.setFont(new Font("Belta Regular", Font.BOLD, 50));
      p2Label.setForeground(new Color(203, 159, 0));
      northPanel.add(p2Label);
      
      return northPanel;
   }
   
   /*Creates the South section of the mancala board. */
   private JPanel southBoardPanel(){
      JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 300, 50));
      p1Label = new JLabel("Player 1");
      p1Label.setFont(new Font("Belta Regular", Font.BOLD, 50));
      p1Label.setForeground(new Color(203, 159, 0));
      southPanel.add(p1Label);
      southPanel.setOpaque(false);
      southPanel.setPreferredSize(new Dimension(600, 100));
      return southPanel;
   }
   /*Creates the West section of the mancala board (Contains Collection Pit. */
   private JPanel westBoardPanel(){
      JPanel westPanel = new JPanel();
      westPanel.setOpaque(false);
      westPanel.setPreferredSize(new Dimension(150, 600));
      
      b13 = new JButton(String.valueOf(game.board.getPitAt(13).getCount()));
      b13.setPreferredSize(new Dimension(100, 330));
      b13.setFont(new Font("Belta Regular", Font.BOLD,32));
      b13.setBackground(new Color(214, 162, 173));
      westPanel.add(b13);
      return westPanel;
   }
  
   /*Creates the East section of the mancala board. Contains Collection Pit*/
   private JPanel eastBoardPanel(){
      JPanel eastPanel = new JPanel();
      eastPanel.setOpaque(false);
      eastPanel.setPreferredSize(new Dimension(150, 600));
      
      b6 = new JButton(String.valueOf(game.board.getPitAt(6).getCount()));
      b6.setPreferredSize(new Dimension(100, 330));
      b6.setFont(new Font("Belta Regular", Font.BOLD,32));
      b6.setBackground(new Color(214, 162, 173));
      eastPanel.add(b6);
      return eastPanel;
   }
   //Create small Pits in board
   private JPanel centerBoardPanel(){
      JPanel centerPanel = new JPanel(new GridLayout(2, 6, 20, 100));
      centerPanel.setOpaque(false);
      
      /*Initialize/Create buttons Array */
      buttons = new JButton[SMALL_PIT_COUNT];
      
      for (int i = 0; i < SMALL_PIT_COUNT; i++) {
         if (i < SMALL_PIT_COUNT/2) {
            JButton b = new JButton(String.valueOf(game.board.getPitAt(i).getCount()));
            b.setFont(new Font("Belta Regular", Font.BOLD,32));         
            b.addActionListener(new ButtonListener());
            buttons[i] = b;
         }
         else {
            JButton b = new JButton(String.valueOf(game.board.getPitAt(i + 1).getCount()));
            b.setFont(new Font("Belta Regular", Font.BOLD,32));
            b.addActionListener(new ButtonListener());
            buttons[i] = b;
         }
      }
      
      for (int i = SMALL_PIT_COUNT - 1; i >= 6 ; i--) {
         buttons[i].setBackground(new Color(214, 162, 173));
         centerPanel.add(buttons[i]);
      }
      
      for (int j = 0; j < SMALL_PIT_COUNT/2; j++) {
         buttons[j].setBackground(new Color(214, 162, 173));
         centerPanel.add(buttons[j]);
      }
      
      return centerPanel;
   }
   
   //Panel below board (Contains Label Indicating Player 1's turn
   private JPanel southWindowPanel(){
      JPanel lab1Panel = new JPanel();
      lab1Panel.setLayout(new BorderLayout());
      lab1Panel.setPreferredSize(new Dimension(300, 150));
      lab1Panel.setOpaque(false);
      statusLabel1 = new JLabel("Player 1's Turn", JLabel.CENTER);
      statusLabel1.setForeground(new Color(203, 159, 0));
      statusLabel1.setFont(new Font("Belta Regular", Font.ITALIC, 45));
      
      lab1Panel.add(statusLabel1, BorderLayout.CENTER);
      
      JPanel eastContainer = new JPanel();
      eastContainer.setLayout(new BorderLayout());
      eastContainer.setOpaque(false);
      eastContainer.setPreferredSize(new Dimension(200, 300));
      
      JPanel btnPanel = new JPanel();
      btnPanel.setOpaque(false);
      btnPanel.setPreferredSize(new Dimension(300, 60));
      eastContainer.add(btnPanel, BorderLayout.SOUTH);
      
      //Add help button to panel
      instructBtn.setPreferredSize(new Dimension(50, 40));
      instructBtn.setBorder(BorderFactory.createLineBorder(Color.black, 5));
      instructBtn.addActionListener(new ButtonListener());
      btnPanel.add(instructBtn);
      
      //Add restart button to panel
      restartBtn.setPreferredSize(new Dimension(100, 40));
      restartBtn.setBorder(BorderFactory.createLineBorder(Color.black, 5));
      restartBtn.addActionListener(new ButtonListener());       //Add Action Listener (Reset Game)
      btnPanel.add(restartBtn);
      
      JPanel westContainer = new JPanel();
      westContainer.setPreferredSize(new Dimension(200, 300));
      westContainer.setOpaque(false);
      lab1Panel.add(westContainer, BorderLayout.WEST);
      lab1Panel.add(eastContainer, BorderLayout.EAST);
      return lab1Panel;
   }
   
   //Panel above board
   private JPanel northWindowPanel(){
      JPanel lab2Panel = new JPanel();
      lab2Panel.setLayout(new BorderLayout());
      lab2Panel.setPreferredSize(new Dimension(300, 150));
      lab2Panel.setOpaque(false);
      statusLabel2 = new JLabel("", JLabel.CENTER);
      statusLabel2.setForeground(new Color(203, 159, 0));
      statusLabel2.setFont(new Font("Belta Regular", Font.ITALIC, 45));
      
      lab2Panel.add(statusLabel2, BorderLayout.CENTER);
      return lab2Panel;
   }
   //Panel left of board
   private JPanel westWindowPanel(){
      JPanel westPanel = new JPanel();
      westPanel.setOpaque(false);
      westPanel.setPreferredSize(new Dimension(80, 200));
      return westPanel;
   }
   //Panel right of board
   private JPanel eastWindowPanel(){
      JPanel eastPanel = new JPanel();
      eastPanel.setOpaque(false);
      eastPanel.setPreferredSize(new Dimension(80, 200));
      return eastPanel;
   }
   
   //Button Listener Events 
   private class ButtonListener implements ActionListener{
      public void actionPerformed(ActionEvent event) {
         if (event.getSource() == instructBtn) {  //display instruction panel
            JLabel picLabel = new JLabel(new ImageIcon("Images/Instructions.png"));
            JOptionPane.showMessageDialog(null, picLabel, "Instructions", JOptionPane.PLAIN_MESSAGE, null);
            System.out.println("Need some help?");
         }     
         if (event.getSource() == restartBtn) { //reset board when restart button is clicked
            winnersLabel.setText("");
            resultFrame.setVisible(false);
            System.out.println("Restarting!");
            resetAll(); 
         }
         
         //If Player 1's turn
         if (game.isP1Turn) {
            for (int i = 0; i < SMALL_PIT_COUNT/2; i++) {
               if ((event.getSource() == buttons[i]) && (Integer.parseInt(buttons[i].getText()) != 0) && (game.getGameOver() == -1)) {
                  game.makeMove(i); //Make move
                  updateButtons(); //Update buttons
                  if (game.getGameOver() == -1) {
                     if (game.isP1Turn)
                        statusLabel1.setText("Player 1's Turn Again");             
                     else {
                        statusLabel1.setText("");
                        statusLabel2.setText("Player 2's Turn");
                        System.out.println("Updating");
                     }
                  }
                  else if (game.getGameOver() != -1) {
                     endGame();
                  }
               }
            }
         }
         //If Player 2's turn
         else {
            for (int i = SMALL_PIT_COUNT; i > SMALL_PIT_COUNT/2; i--) {
               if ((event.getSource() == buttons[i - 1]) && (Integer.parseInt(buttons[i - 1].getText()) != 0) && (game.getGameOver() == -1)) {
                  game.makeMove(i); //Make move
                  updateButtons(); //Update buttons
                  if (game.getGameOver() == -1) {
                     if (!game.isP1Turn)
                        statusLabel2.setText("Player 2's Turn Again");             
                     else {
                        statusLabel2.setText("");
                        statusLabel1.setText("Player 1's Turn");
                     }
                  }            
                  else if (game.getGameOver() != -1) {
                     endGame();
                  }
               }
            }
         }
         //Buttons in panel that pops up at end of game
         if (event.getSource() == scoresBtn) //Display previous results
            printWinners();
         
         if (event.getSource() == playAgainBtn) { //Play again
            resetAll();
            resultFrame.setVisible(false);
            winnersLabel.setText("");
         }     
         if (event.getSource() == quitBtn) //Quit and exit out of window
            System.exit(0);         
      }
      
      //Display winner results
      private void endGame(){     
         statusLabel1.setText("");
         statusLabel2.setText("");
         
         if (game.getGameOver() == 0) { //If tie
            System.out.println("TIE");
            winners.push("TIE");
            result.setText("          IT'S A TIE!");
         }     
         else if (game.getGameOver() == 1) { //If player 1 wins
            System.out.println("PLAYER 1 WINS!");
            winners.push("PLAYER 1");
            result.setText("  PLAYER 1 WINS!");
         }      
         else if (game.getGameOver() == 2) { //if player 2 wins
            System.out.println("PLAYER 2 WINS");
            winners.push("PLAYER 2");
            result.setText("  PLAYER 2 WINS!");
            //resultFrame.setVisible(true);
         }
         
         if (winners.size() < 5)
            scoresBtn.setVisible(true);
         if (winners.size() > 4)
            scoresBtn.setVisible(false);
         
         resultNorth.add(result);
         resultFrame.pack();
         resultFrame.setLocation(200, 50);
         resultFrame.setVisible(true);
      }
      
      /*Produces a JLabel that displays the winners of the last five rounds. */        
      private void printWinners(){
         String text = "<html>&nbsp;&nbsp;&nbsp;&nbsp;Previous Winners:<br><br>";
         String [] tempArray = new String[5];
         while (!winners.isEmpty()) {
            for (int i = winners.size(); i > 0; i--) {
               String temp = winners.pop();
               text += "&nbsp;&nbsp;Round " + i + ": " + temp + "<br>";
               System.out.println("<b>Text: " + text);
               tempArray[i] = temp;
            }
         }
         for (int i = 0; i < 5; i++) {
            if (tempArray[i] != null)
               winners.push(tempArray[i]);
         }
         
         winnersLabel.setOpaque(true);
         winnersLabel.setBorder(BorderFactory.createLineBorder(Color.white, 6));
         winnersLabel.setBackground(new Color(0, 158, 121));
         winnersLabel.setText(text);   
      }
      
      //Updated buttons on board to reflect LinkedList    
      private void updateButtons(){
         System.out.println(String.valueOf(game.board.getPitAt(13).getCount()));
         b13.setText(String.valueOf(game.board.getPitAt(13).getCount()));
         b6.setText(String.valueOf(game.board.getPitAt(6).getCount()));
         
         for (int i = 0; i < SMALL_PIT_COUNT; i++) {
            if (i < SMALL_PIT_COUNT/2) 
               buttons[i].setText(String.valueOf(game.board.getPitAt(i).getCount()));
            else 
               buttons[i].setText(String.valueOf(game.board.getPitAt(i + 1).getCount()));
         }
      }             
      //Reset board for new game              
      private void resetAll(){
         game = new LetsPlayMancala();
         statusLabel1.setText("Player 1's Turn");
         statusLabel2.setText("");
         
         for (int i = 0; i < SMALL_PIT_COUNT; i++) { 
            if (i < SMALL_PIT_COUNT/2)
               buttons[i].setText(String.valueOf(game.board.getPitAt(i).getCount()));
            else
               buttons[i].setText(String.valueOf(game.board.getPitAt(i + 1).getCount()));
         }
         b13.setText(String.valueOf(game.board.getPitAt(13).getCount()));
         b6.setText(String.valueOf(game.board.getPitAt(6).getCount()));
      }
      
   }//End of Button Listener Class
   
}//End of MancalaPanel class