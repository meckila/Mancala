//*******************************************************************************
// MancalaBoard.java 
// Author: Meckila Britt
//
// Creates a LinkedList representation of a mancala board. The LinkedList
// contains all of the game board's pits. 
//*******************************************************************************

import java.util.LinkedList; //ADT


public class MancalaBoard
{
   //---------------------------------------------------------------------
   // INSTANCE VARIABLES
   //---------------------------------------------------------------------
   
   private LinkedList<Pit> mancalaBoard;             //Represents game board
   protected Player playerOne, playerTwo;
   protected SmallPit[] playerOnePits, playerTwoPits;  //Stores players' SmallPits
  
   protected Pit collectionPit1, collectionPit2;       //Represents each player's collectionPit
   protected final int SMALL_PIT_COUNT;                //Number of SmallPits per player (Constant)
   
   
   //---------------------------------------------------------------------
   // CONSTRUCTOR
   //---------------------------------------------------------------------
   
   public MancalaBoard()
   {
      mancalaBoard = new LinkedList<Pit>();
      playerOne = new Player();
      playerTwo = new Player();
      
      
      SMALL_PIT_COUNT = 6;
      playerOnePits = new SmallPit[SMALL_PIT_COUNT];
      playerTwoPits = new SmallPit[SMALL_PIT_COUNT];
      
      collectionPit1 = new Pit();
      collectionPit2 = new Pit();
      
      playerOne.assignSmallPits(playerOnePits);
      playerOne.assignCollectPit(collectionPit1);
      
      playerTwo.assignSmallPits(playerTwoPits);
      playerTwo.assignCollectPit(collectionPit2);
   }
   
   
   //---------------------------------------------------------------------
   // HELPER METHOD (INITIALIZE PITS)
   //---------------------------------------------------------------------
   
   public void initializePits()
      /*Initializes all 14 of the game board's pits. This involves assigning 
       * pits a specific pebble count, linking the pits together, assigning
       * a pit an owner, etc. */
   {
      
      /* -------------------------------------------
          Create two arrays representing 
          each player's respective small pits.
          Initialize each new pit.
         ------------------------------------------- */
      
      for (int i = 0; i < SMALL_PIT_COUNT; i++) {
         playerOnePits[i] = new SmallPit();    //Create and add each SmallPit to playerOne's array
         playerOnePits[i].setInitialCount();   //Initialize pit's count to 4 pebbles
         playerOnePits[i].setOwner(playerOne); //Initialize pit's owner to playerOne
         
         
         playerTwoPits[i] = new SmallPit();
         playerTwoPits[i].setInitialCount();
         playerTwoPits[i].setOwner(playerTwo);
      }
      
      
      /* ------------------------------------------------
          Initialize each player's endPit & collectionPit
         ------------------------------------------------ */
      
      collectionPit1.setOwner(playerOne);           //Set Owner of collectionPit1
      collectionPit2.setOwner(playerTwo);
      
      
      /* -------------------------------------------
          Links all the pits together
         ------------------------------------------- */
      
      for (int i = 0; i < SMALL_PIT_COUNT; i++) {
         /*Assign each SmallPit an opposite pit */
         playerOnePits[i].setOppPit(playerTwoPits[SMALL_PIT_COUNT - (i + 1)]);
         playerTwoPits[i].setOppPit(playerOnePits[SMALL_PIT_COUNT - (i + 1)]);
         
         if (i < SMALL_PIT_COUNT - 1) {
            playerOnePits[i].setNextPit(playerOnePits[i + 1]);
            playerTwoPits[i].setNextPit(playerTwoPits[i + 1]);
         }
         
         else {
            playerOnePits[i].setNextPit(collectionPit1);
            playerTwoPits[i].setNextPit(collectionPit2);
            
         }
      }      

      /*Link each collectionPit to the next pit */
      collectionPit1.setNextPit(playerTwoPits[0]); //Link to playerTwo's first pit 
      collectionPit2.setNextPit(playerOnePits[0]);    
   }
   
   
   
   //---------------------------------------------------------------------
   // MAKE BOARD METHOD
   //---------------------------------------------------------------------
   
   public LinkedList<Pit> makeBoard()
   /*Create a LinkedList representation of a mancala board game. */
   {
      this.initializePits(); //Setup the Pits
      
      
      
      /* --------------------------------------------------------
          Add each pit to the LinkedList representation of board
         ------------------------------------------------------- */
     
      for (int i = 0; i < SMALL_PIT_COUNT; i++)
         mancalaBoard.add(playerOnePits[i]); 
      
      mancalaBoard.add(collectionPit1);
      
      for (int i = 0; i < SMALL_PIT_COUNT; i++)
         mancalaBoard.add(playerTwoPits[i]);
      
      mancalaBoard.add(collectionPit2);
      
      return mancalaBoard;
   }
   
   
   //---------------------------------------------------------------------
   // LOCATION OF PIT METHOD
   //---------------------------------------------------------------------
   
   public int locationOf(Pit pit)
   /*Returns the indexed position of the specified pit on the mancala board. */
   {
      return mancalaBoard.indexOf(pit);
   }
   
   
   public Pit getPitAt(int position)
   {
      return mancalaBoard.get(position);
   }
   
   
   public Player getPlayerOne()
   {
      return playerOne;
   }
   
   public Player getPlayerTwo()
   {
      return playerTwo;
   }
   
   
   //---------------------------------------------------------------------
   // TO STRING METHOD
   //---------------------------------------------------------------------
   
   public String toString()
   /*String representation of the mancala game board. */
   {
      String s = "          MANCALA BOARD: \n          P1 = Player One\n" 
                      + "          P2 = Player Two";
      
      s += "\n\n        P2 Collection Pit: " + collectionPit2.getCount();
      
      s += "\n          (Position = " + locationOf(collectionPit2)
                        + ")\n";
      
      for (int i = 0; i < SMALL_PIT_COUNT; i++) {
            s += "\nP1 Pit " + locationOf(playerOnePits[i])
                       + ": " + playerOnePits[i].getCount() + "          P2 Pit: " 
                       + locationOf(playerTwoPits[SMALL_PIT_COUNT - (i + 1)])
                       + ": " + playerTwoPits[SMALL_PIT_COUNT - (i + 1)].getCount();
      }
            
      s += "\n\n        P1 Collection Pit: " + collectionPit1.getCount();
      
      s += "\n          (Position = " + locationOf(collectionPit1)
                        + ")";
      return s;
   }
      
   
   public static void main(String[] args)
   {
      MancalaBoard board = new MancalaBoard();
      board.makeBoard();
      System.out.println(board.playerOnePits[0].getOwner());
      System.out.println(board.collectionPit1.getOppPit());
      System.out.println(board.locationOf(board.playerOnePits[0].getOppPit()));
      
      for (int i = 0; i < 6; i++)
         System.out.println(board.playerOne.getSmallPits()[i]);
      
      System.out.println(board);
   }
   
}      