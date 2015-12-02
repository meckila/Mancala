//*******************************************************************************
// SmallPit.java 
// Authors: Lily Westort
//
// The "SmallPit" class, which extends the parent "Pit" class, represents 
// each of the six small pits belonging to a player. 
//********************************************************************************


public class SmallPit extends Pit
{
   
   //---------------------------------------------------------------------
   // INSTANCE VARIABLES
   //---------------------------------------------------------------------
   
   protected final int PEB_COUNT; //Constant to store initial pebble count
   protected Pit oppPit;
   
   //---------------------------------------------------------------------
   // CONSTRUCTOR
   //---------------------------------------------------------------------
   public SmallPit()
   {
      PEB_COUNT = 4; /*At the start of a new game, each small pit must
                      *contain 4 pebbles. */
   }
   
   
   //---------------------------------------------------------------------
   // SET INITIAL COUNT METHOD
   //---------------------------------------------------------------------
   
   public void setInitialCount()
   /*Initializes a small pit to contain 4 pebbles.*/
   {
      count = PEB_COUNT;
   }  
   
   
   //---------------------------------------------------------------------
   // MAIN METHOD (TESTING)
   //---------------------------------------------------------------------
   public static void main(String[] args)
   {
      SmallPit pit1 = new SmallPit();
      System.out.println("Initial Count (0): " + pit1.getCount());
      pit1.add(3);
      System.out.println("Updated Count (3): " + pit1.getCount());
      pit1.clear();
      System.out.println("Cleared (0): " + pit1.getCount());
      pit1.addPebble();
      System.out.println("AddedPebble (1): " + pit1.getCount());
      pit1.setInitialCount();
      System.out.println("Re-Initialize (4): " + pit1.getCount());
      pit1.addPebble();
      System.out.println("Added Pebble (5): " + pit1.getCount());
      
      System.out.print("\nPrinting pit info:\n" + pit1);
      System.out.println("Pit Type: " + pit1.getType());
   }
      
}