//*******************************************************************************
// Pit.java 
// Authors: Lily Westort
//
// The "Pit" class represents a generic pit object. There are two types of pits
// on the mancala board: small pits (12) and collection pits(2). The "Pit" class,
// which is used to represent a collection pit, also serves as the parent class
// of the subclass "SmallPit." Each pit has a count(number of pebbles it contains), 
// nextPit(a reference to the next pit on the mancala board), and an owner(
// (the player a pit belongs to).
//******************************************************************************* 

public class Pit
{
   
   //---------------------------------------------------------------------
   // INSTANCE VARIABLES
   //---------------------------------------------------------------------
   
   protected int count;       //Number of pebbles in pit
   protected Pit nextPit;     //Reference to the next pit on the board
   protected Player owner;    //The player a pit belongs to
   protected Pit oppPit;      /*The pit directly across from current pit
    on opponents side of the board. */
   
   
   //---------------------------------------------------------------------
   // GET COUNT (METHOD)
   //---------------------------------------------------------------------
   
   public int getCount()
   /*Retrieves and returns the number of pebbles in a pit. */
   {
      return count;
   }
   
   
   //---------------------------------------------------------------------
   // ADD PEBBLE (METHOD)
   //--------------------------------------------------------------------- 
   
   public void addPebble()
   /*Increments the pit's count by 1. */
   {
      count += 1;
   }
   
   
   //---------------------------------------------------------------------
   // ADD (METHOD)
   //---------------------------------------------------------------------
   
   public void add(int num)
   /*Adds the specified number of pebbles to a pit. */
   {
      count += num;
   }
   
   
   //---------------------------------------------------------------------
   // CLEAR (METHOD)
   //---------------------------------------------------------------------
   
   public void clear()
   /*Removes all of a pit's pebbles (sets the pit's count to 0). */
   {
      count = 0;
   }
   
   
   //---------------------------------------------------------------------
   // SET NEXT PIT (METHOD)
   //---------------------------------------------------------------------
   
   public void setNextPit(Pit nextPit)
   /*links the current pit to the next pit on the board. */
   {
      this.nextPit = nextPit;
   }
   
   
   //---------------------------------------------------------------------
   // GET NEXT PIT (METHOD)
   //---------------------------------------------------------------------
   
   public Pit getNextPit()
   /*Retrieves and returns the next pit on the board. */
   {
      return nextPit;
   }
   
   
   //---------------------------------------------------------------------
   // GET COUNT (METHOD)
   //---------------------------------------------------------------------
   
   public void setOwner(Player player)
   /*Assigns a pit to belong to a specified player.*/
   {
      this.owner = player;
   }
   
   
   //---------------------------------------------------------------------
   // GET OWNER (METHOD)
   //---------------------------------------------------------------------
   
   public Player getOwner()
   /*Retrieves and returns a pit's owner. */
   {
      return owner;
   }
   
   
   //---------------------------------------------------------------------
   // GET TYPE (METHOD)
   //---------------------------------------------------------------------
   
   public String getType()
   /*Returns a string representation of a pit's type (whether it is a
    * "collection pit" or "small pit"). */
   {
      String s = String.valueOf(getClass()); //(i.e. s = "class Pit")
      return s.substring(6); //Excludes "class" from returned value
   }
   
   
   //---------------------------------------------------------------------
   // SET OPP PIT METHOD
   //---------------------------------------------------------------------
   
   public void setOppPit(Pit oppPit)
   /*Sets a current pit's opposing pit (the pit directly across from it on
    * the opponents side of the board). */
   {
      this.oppPit = oppPit;
   }
   
   
   //---------------------------------------------------------------------
   // GET OPP PIT METHOD
   //---------------------------------------------------------------------
   
   public Pit getOppPit()
   /*Returns a current pit's opposing pit. */
   {
      return oppPit;
   }
   
   
   //---------------------------------------------------------------------
   // TO STRING METHOD
   //---------------------------------------------------------------------
   
   public String toString()
   {
      String s ="";
      
      try {
         s += "Pit Type: " + getType() + "\nPit Count: " + getCount() + 
            "\nPit Owner: " + getOwner() + "\n";
         return s;
      
      } catch (NullPointerException ex) {
         /*If the pit hasn't been assigned an owner yet. */
         return "Pit Count: " + getCount() + "\nPit Owner: None\n";
      }
   }
   
   //-----------------------------------------------------------------------------
   // MAIN METHOD (TESTING)
   //-----------------------------------------------------------------------------
  
   public static void main(String[] args)
   {   
      Pit p1 = new Pit();
      Pit p2 = new Pit();
      
      Player playerOne = new Player();
      p1.setOwner(playerOne);
      System.out.println("Pit 1 Owner: " + p1.getOwner());
      System.out.println("Pit 1 Initial Count (0): " + p1.getCount());
      p1.addPebble();
      p1.addPebble();
      p1.addPebble();
      System.out.println("Pit 1 Updated Count (3): " + p1.getCount());
      p1.setNextPit(p2);
      p2.addPebble();
      System.out.println("Pit 2 Count (1): " + p1.getNextPit().getCount());
      p1.clear();
      System.out.println("Pit 1 Count (0): " + p1.getCount());
      p1.getNextPit().addPebble();
      System.out.println("Pit 2 Updated Count (2): " + p2.getCount());
      System.out.println();
      System.out.println(p1);
   }
}


      