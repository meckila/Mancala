/* LinkedStack.java       Java Foundations
 * CS230 PSet 5
 * Amal Tidjani
 * 3/10/15 */
    

package javafoundations;
import javafoundations.exceptions.*;


public class LinkedStack<T> implements Stack<T>
/*Represents a linked implementation of a stack. */
{
   private int count;
   private LinearNode<T> top;

  
   public LinkedStack()
   /*Creates an empty stack using the default capacity. */
   {
      count = 0;
      top = null;
   }

  
   public T pop() throws EmptyCollectionException
   /*Removes the element at the top of this stack and returns a 
    * reference to it. Throws an EmptyCollectionException if the
    * stack contains no elements. */
   {
      if (count == 0)
         throw new EmptyCollectionException ("Pop operation failed."
            + " The stack is empty.");

      T result = top.getElement();
      top = top.getNext();
      count--;

      return result;
   }

   
   public String toString()
   //Returns a string representation of the stack.    
   {
      String result = "<top of stack>\n";
      LinearNode current = top;

      while (current != null)
      {
         result += current.getElement() + "\n";
         current = current.getNext();
      }

      return result + "<bottom of stack>";
   }

   //-----------------------------------------------------------------
   //  The following methods are left as Programming Projects.
   //-----------------------------------------------------------------
   
   
   public void push(T element)
   /*Adds an element to the top of the stack.*/
   {
      LinearNode temp = new LinearNode(element); //Create new node w/ ref to obj to be added
      temp.setNext(top);  //Set next ref to point to top of stack
      top = temp;  //Set top ref to new node
      count++;  //Increment count of elements in stack
   }
   
   
   public T peek() throws EmptyCollectionException
   /*Returns a reference to the element at the top of the stack
    * without removing it from the stack. */
   {
      if (count == 0) //When stack is empty
         throw new EmptyCollectionException("Peek operation failed."
                                    + " The stack is empty.");
      return top.getElement(); //Return ref to element at top
   }
   
   
   public boolean isEmpty()
   /*Returns a boolean value indicating whether the stack
    * is empty or not. */  
   {
      return count == 0;
   }
   
   
   public int size()
   /*Returns the size of the stack. */
   {
      return count;
   }
}


