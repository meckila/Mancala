//*******************************************************************************
// Player.java 
// Author: Lily Westort
//
// Creates an instance of the type "Player," representing one of two 
// humans that will play a round of Mancala. Each player is assigned pits is 
// assigned a set of small pits and one collection pit. 
//********************************************************************************


public class Player
{
   
   //------------------------------------------------------------------
   // INSTANCE VARIABLES
   //------------------------------------------------------------------
   
   private Pit collectionPit;
   private SmallPit[] smallPits; //Stores each player's 6 small pits
  

   //------------------------------------------------------------------
   // ASSIGN COLLECTION PIT (METHOD)
   //------------------------------------------------------------------  

   public void assignCollectPit(Pit collectionPit)
   /*Assigns/sets a player as the owner of a collection pit.*/ 
   {
      this.collectionPit = collectionPit;
   }
   
   
   
   //------------------------------------------------------------------
   // GET COLLECTION PIT (METHOD)
   //------------------------------------------------------------------
   
   public Pit getCollectPit()
   /*Returns and retrieves the collection pit object assigned
    * to the player. */
   {
      return collectionPit;
   }
   
   
   //------------------------------------------------------------------
   // ASSIGN SMALL PITS (METHOD)
   //------------------------------------------------------------------
   
   public void assignSmallPits(SmallPit[] smallPits)
   /*Assigns/sets a player as the owner of an array of siz
    * small pits. */
   {
      this.smallPits = smallPits;
   }
   
   
   
   //------------------------------------------------------------------
   // GET SMALL PITS
   //------------------------------------------------------------------
   
   public SmallPit[] getSmallPits()
   /*Retrieves and returns the array of six small pits
    * assigned to the player. */
   {
      return smallPits;
   }
   
 
   //------------------------------------------------------------------
   // GET SCORE (METHOD)
   //------------------------------------------------------------------
   
   public int getScore()
   /*Returns the number of pebbles a player has captured in
    * their assigned collection pit. */
   {
      return collectionPit.getCount();
   }
   
   
   
   //------------------------------------------------------------------
   // HAS PEBBLES (METHOD)
   //------------------------------------------------------------------
   
   public boolean hasPebbles()
   /*Returns a boolean value indicating whether or not a player
    * has any pebbles remaining on their side of the mancala board. */
   {
      for (int i = 0; i < 6; i++) 
         /*Loops through a player's array of small pits and
          * retrieves the number of pebbles in each pit. */
         
         if (smallPits[i].getCount() != 0)
            return true; //If at least one small pit contains pebbles 
      
      return false; //If all pits are empty
   }  
}

