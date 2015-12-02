//********************************************************************
//  ArrayStack.java       Java Foundations
//
//  Represents an array implementation of a stack. The bottom of
//  the stack is kept at array index 0. So, pushing and popping happens at the higher 
//  end of the underlying array.
//********************************************************************

package javafoundations;
import javafoundations.exceptions.*;

public class ArrayStack<T> implements Stack<T>
{
   private final int DEFAULT_CAPACITY = 10; //keep it small for testing purposes
   private int count; //num of elements in the array
   private T[] stack; //array to hold the elements

   //-----------------------------------------------------------------
   //  Creates an empty stack using the default capacity.
   //-----------------------------------------------------------------
   public ArrayStack()
   {
      count = 0;
      stack = (T[])(new Object[DEFAULT_CAPACITY]);
   }

   //-----------------------------------------------------------------
   //  Adds the specified element to the top of this stack, expanding
   //  the capacity of the stack array if necessary.
   //-----------------------------------------------------------------
   
   private void expandCapacity()
   /*Expands the capacity of the array. */
   {
      T[] stack2 = (T[])(new Object[stack.length * 2]);
      for(int i = 0; i < stack.length; i++)
         stack2[i] = stack[i];
      stack = stack2;
   }
   
   public void push (T element)
   {
      if (count == stack.length)
         expandCapacity();

      stack[count] = element;
      count++;
   }

   //-----------------------------------------------------------------
   //  Returns a string representation of this stack.
   //-----------------------------------------------------------------
   public String toString()
   {
      String result = "<top of stack>\n";

      for (int index=count-1; index >= 0; index--)
         result += stack[index] + "\n";

      return result + "<bottom of stack>";
   }


   //-----------------------------------------------------------------
   //  The following methods are left as Programming Projects.
   //-----------------------------------------------------------------
   // public T pop () throws EmptyCollectionException { }
   // public T peek () throws EmptyCollectionException { }
   // public boolean isEmpty() { }
   // public int size() { }
   
   public T pop()
   /*Returns and removes the element at the top of stack. */
   {
      if (count == 0)
         throw new EmptyCollectionException
         ("Pop operation failed. Stack is empty.");
      
      else {
         T temp = stack[count - 1];
         //stack[count] = null;
         count--;
         return temp;
      }
   }
  
   public T peek()
   /*Returns a reference to the element at the top of 
    * the stack without returning it. */
   {
      if (count == 0)
         throw new EmptyCollectionException
         ("Peek operation failed. Stack is empty.");
         
      else 
         return stack[count - 1];
   }
   
   public boolean isEmpty()
   /*Determines if the stack is empty. */
   {
      return count == 0;
   }
   
   public int size()
   /*Determines the size of the stack. */
   {
      return count;
   }

   public static void main(String[] args)
   {
      ArrayStack<String> journey = new ArrayStack<String>();
      journey.push("Strangers");
      journey.push("waiting");
      journey.push("up");
      journey.push("and");
      journey.push("down");
      journey.push("the");
      journey.push("boulevard");
      journey.push("their");
      journey.push("shadows");
      journey.push("searching");
      System.out.println(journey);
      System.out.println(journey.size());
      System.out.println(journey.peek());
      System.out.println(journey.pop());
      System.out.println(journey.pop());
      System.out.println(journey.pop());
      System.out.println(journey);
      System.out.println(journey.size());
      System.out.println(journey.isEmpty());
   }
}
