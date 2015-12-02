//*******************************************************************************
// LetsPlayMancala.java 
// Authors: Meckila Britt and Amal Tidjani
//
// Create a new playable game of Mancala. 
//********************************************************************************


import java.util.LinkedList;

public class LetsPlayMancala
{
   protected boolean isP1Turn = true;
   private int gameOver = -1;
   private int hand;
   protected MancalaBoard board;
   private Player playerOne, playerTwo;
   private LinkedList<Pit> board2;
   private LinkedList counts;
   protected int PIT_COUNT;
   
      
   public LetsPlayMancala()
   {
      board = new MancalaBoard();
      board.makeBoard();
      playerOne = board.getPlayerOne();
      playerTwo = board.getPlayerTwo();
      PIT_COUNT = 14;
   }  
   
   
   
   //---------------------------------------------------------------------
   // MAKE MOVE (METHOD)
   //---------------------------------------------------------------------
   public void makeMove(int position)
   {
      Pit selectedPit = board.getPitAt(position);
      
      if (isP1Turn) {
         if (selectedPit.getCount() != 0 && selectedPit.getOwner() == board.getPlayerOne()
                && !selectedPit.getType().equals("Pit")) {
            hand = selectedPit.getCount();
            selectedPit.clear();
            while (hand > 0) {
               
               selectedPit = selectedPit.getNextPit();
               
               if (board.locationOf(selectedPit) != 13) {
                  selectedPit.addPebble();
                  hand--; 
               }
               else {
                  selectedPit = selectedPit.getNextPit();
                  selectedPit.addPebble();
                  hand--; 
               }
            }
            
            if (hand == 0 && !selectedPit.getType().equals("Pit")) {
               if (selectedPit.getOwner() == playerOne && selectedPit.getCount() == 1
                      && selectedPit.getOppPit().getCount() > 0) {
                  int captured = selectedPit.getCount() + selectedPit.getOppPit().getCount();
                  selectedPit.clear();
                  selectedPit.getOppPit().clear();
                  playerOne.getCollectPit().add(captured);
               }
               isP1Turn = false;
               selectedPit = null;
               hand = 0;
            }
         } 
      }
      
      else if (!isP1Turn) {  
         if (selectedPit.getCount() != 0 && selectedPit.getOwner() == board.getPlayerTwo()
                && !selectedPit.getType().equals("Pit")) {
            hand = selectedPit.getCount();
            selectedPit.clear();
            while (hand > 0) {
               
               selectedPit = selectedPit.getNextPit();
              
               if (board.locationOf(selectedPit) != 6) {
                  selectedPit.addPebble();
                  hand--; 
               }
               else {
                  selectedPit = selectedPit.getNextPit();
                  selectedPit.addPebble();
                  hand--; 
               }
            }
         
            if (hand == 0 && !selectedPit.getType().equals("Pit")) {
               if (selectedPit.getOwner() == playerTwo && selectedPit.getCount() == 1
                      && selectedPit.getOppPit().getCount() > 0) {
                  int captured = selectedPit.getCount() + selectedPit.getOppPit().getCount();
                  selectedPit.clear();
                  selectedPit.getOppPit().clear();
                  playerTwo.getCollectPit().add(captured);
               }
               isP1Turn = true;
               selectedPit = null;
               hand = 0;
            }
         }
      }
      gameOver = isGameOver(); //Checks if gameOver
   
   }
   
   public LinkedList getAllCounts()
   {
      counts = new LinkedList();
      for (int i = 0; i < PIT_COUNT; i++)
         counts.add(board.getPitAt(i).getCount());
      
      return counts;
   }
   
   
   //---------------------------------------------------------------------
   // GET GAME RESULT (METHOD)
   //---------------------------------------------------------------------
   
   public int getGameResult()
   /*Determines and returns an integer correspodning to the winner of the game. 
    * Returns "0" if game is tied, "1" if playerOne wins, "2" if playerTwo wins. */ 
   {
      
      /*Compares pebble counts in each player's collection pit. */
      
      if (playerOne.getScore() > playerTwo.getScore()) {
         System.out.println("Player One wins!");
         return 1; //playerOne Wins
      }
      else if (playerTwo.getScore() > playerOne.getScore()) {
         System.out.println("Player Two wins!");
         return 2; //playerTwo Wins
      }
      else {
         System.out.println("Game ended in a Draw.");
         return 0; //Tie
      }
   }
   
   //---------------------------------------------------------------------
   // IS GAME OVER (METHOD)
   //---------------------------------------------------------------------
   
   public int isGameOver()
   /*Returns an integer value indicating whether or not the game is over.
    * Returns -1 if game is NOT over, 
    *          0 if game ends in TIE, 
               1 if playerOne wins, 
               2 if playerTwo wins. */
   {
      
      if (!playerOne.hasPebbles() || !playerTwo.hasPebbles()) {
         /*Game is over if one or both players run out of 
          * pebbles in their small pits. */        
         return getGameResult(); //Return Game Result
      }
      
      else if (playerOne.getScore() > 24 || playerTwo.getScore() > 24)
         /*Game is also over if one player collects 24 or more pebbles
          * in their collection pit. */
         return getGameResult(); //Return Game Result     
      else
         return -1; //Game NOT over
   }

   public int getGameOver(){
      return gameOver;
   }
   
   
   //--------------------------------------------------------------------------
   // TESTING (MAIN METHOD)
   //--------------------------------------------------------------------------
   public static void main(String[] args){
      LetsPlayMancala game = new LetsPlayMancala();
           
      game.makeMove(2);
      System.out.println("Move 1: (Pit 2 Selected) ");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      LinkedList test = new LinkedList();
      test = game.getAllCounts();
      for (int i = 0; i < 14; i++)
         System.out.println(test.get(i));
             
      game.makeMove(5);
      System.out.println("Move 2: (Pit 5 Selected) ");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(3);
      System.out.println("Move 3: (Pit 3 Selected) ");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
           
      game.makeMove(8);
      System.out.println("Move 4: (Pit 8 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(6);
      System.out.println("Move 5: (Pit 6 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(13);
      System.out.println("Move 6: (Pit 13 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(10);
      System.out.println("Move 7: (Pit 10 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(0);
      System.out.println("Move 8: (Pit 0 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(11);
      System.out.println("Move 9: (Pit 11 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(8);
      System.out.println("Move 10: (Pit 8 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(3);
      System.out.println("Move 11: (Pit 3 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(7);
      System.out.println("Move 12: (Pit 7 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(5);
      System.out.println("Move 13: (Pit 5 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(2);
      System.out.println("Move 14: (Pit 2 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(7);
      System.out.println("Move 15: (Pit 7 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(12);
      System.out.println("Move 16: (Pit 12 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(4);
      System.out.println("Move 17: (Pit 4 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(12);
      System.out.println("Move 18: (Pit 12 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(10);
      System.out.println("Move 19: (Pit 10 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(5);
      System.out.println("Move 20: (Pit 5 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(11);
      System.out.println("Move 21: (Pit 11 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(9);
      System.out.println("Move 22: (Pit 9 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(2);
      System.out.println("Move 23: (Pit 2 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(7);
      System.out.println("Move 24: (Pit 7 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(0);
      System.out.println("Move 25: (Pit 0 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(8);
      System.out.println("Move 26: (Pit 8 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(10);
      System.out.println("Move 27: (Pit 10 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(1);
      System.out.println("Move 28: (Pit 1 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(9);
      System.out.println("Move 29: (Pit 9 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(5);
      System.out.println("Move 30: (Pit 5 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(3);
      System.out.println("Move 31: (Pit 3 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(11);
      System.out.println("Move 32: (Pit 11 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(4);
      System.out.println("Move 33: (Pit 4 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(5);
      System.out.println("Move 34: (Pit 5 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(0);
      System.out.println("Move 35: (Pit 0 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(0);
      System.out.println("Move 36: (Pit 0 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(10);
      System.out.println("Move 37: (Pit 10 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(11);
      System.out.println("Move 38: (Pit 11 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(2);
      System.out.println("Move 39: (Pit 2 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(7);
      System.out.println("Move 40: (Pit 7 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(1);
      System.out.println("Move 41: (Pit 1 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(8);
      System.out.println("Move 42: (Pit 8 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(3);
      System.out.println("Move 43: (Pit 3 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(9);
      System.out.println("Move 44: (Pit 9 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(4);
      System.out.println("Move 45: (Pit 4 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(10);
      System.out.println("Move 46: (Pit 10 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      game.makeMove(5);
      System.out.println("Move 47: (Pit 5 Selected)");
      System.out.println(game.board);
      System.out.println("isP1Turn: " + game.isP1Turn);
      System.out.println("isGameOver: " + game.gameOver);
      System.out.println();
      
      System.out.println("P1 Score: " + game.playerOne.getScore());
      System.out.println("P2 Score: " + game.playerTwo.getScore());
      
      
      
      
      
   }
}
     