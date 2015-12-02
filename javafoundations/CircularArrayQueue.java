//********************************************************************
//  CircularArrayQueue.java       Java Foundations
//
//  Represents an array implementation of a queue in which neither
//  end of the queue is fixed in the array. The variables storing the
//  indexes of the front and rear of the queue continually increment
//  as elements are removed and added, cycling back to 0 when they
//  reach the end of the array.
//********************************************************************

package javafoundations;

import javafoundations.exceptions.*;

public class CircularArrayQueue<T> //implements Queue<T>
{
   private final int DEFAULT_CAPACITY = 5;
   //rear points to the next available position.
   private int front, rear, count;
   private T[] queue;

   //-----------------------------------------------------------------
   //  Creates an empty queue using the default capacity.
   //-----------------------------------------------------------------
   public CircularArrayQueue()
   {
      front = rear = count = 0;
      queue = (T[]) (new Object[DEFAULT_CAPACITY]);
   }

   //-----------------------------------------------------------------
   //  Adds the specified element to the rear of this queue, expanding
   //  the capacity of the queue array if necessary.
   //-----------------------------------------------------------------
   public void enqueue (T element)
   {
      if (count == queue.length)
         expandCapacity();

      queue[rear] = element;//add the newly arrived element to the next available slot
      //find out what the next available slot will be.
      //if the end of the array is reached, then wrap around to the beginning of it.
      rear = (rear+1) % queue.length;
      count++;
   }

   //-----------------------------------------------------------------
   //  Creates a new array to store the contents of this queue with
   //  twice the capacity of the old one.
   //-----------------------------------------------------------------
   public void expandCapacity()
   {
      T[] larger = (T[])(new Object[queue.length*2]);

      for (int index=0; index < count; index++)
         larger[index] = queue[(front+index) % queue.length];

      front = 0;
      rear = count;
      queue = larger;
   }

   //-----------------------------------------------------------------
   //  The following methods are left as Programming Projects.
   //-----------------------------------------------------------------
   public T dequeue () throws EmptyCollectionException { 
     if (count==0) 
       throw new EmptyCollectionException ("nothing to dequeue");
     T result = queue[front];
     front = (front+1)%queue.length;
     count--;
     return result;
   }
    
   public T first () throws EmptyCollectionException {
     if (count==0) 
       throw new EmptyCollectionException ("nothing in the queue");
     return queue[front];
   }
   
   public int size() { 
     return count;
   }
   
   public boolean isEmpty() { 
     return count==0;
   }
   
   public String toString() { 
     String s="";
     for (int i=front; i<front+count; i++) {
       s=s+queue[i%queue.length] + "\t\t";
     }
     return s;
   }
   
   public static void main(String[] args) {
     CircularArrayQueue<String> q = new CircularArrayQueue<String>();
     q.enqueue("AAA");
     q.enqueue("BBB");
     q.enqueue("CCC");
     q.enqueue("DDD");
     q.enqueue("EEE");
     q.enqueue("FFF");
     System.out.println(q.size());
     
     //System.out.println(q.dequeue());
     System.out.println(q);
     //System.out.println(q.dequeue());
     
     //System.out.println("front: " + q.first());
     //System.out.println(q.dequeue());
     //System.out.println(q.dequeue());
   }
   
}