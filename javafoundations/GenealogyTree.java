/*GenealogyTree.java
 * CS230 PSet 8
 * Amal Tidjani & __________
 * 4/22/15 */

package javafoundations;

import java.util.Iterator;
import javafoundations.*;
import javafoundations.exceptions.*;

public class GenealogyTree<T> //IMPLEMENT!!!!
{
   protected BTNode<T> root;
   private T[] treeArray;
   private final int DEFAULT_CAPACITY = 1;
   
   
   
   
   
   
   
   
   //------------------------------------------------------------------------
   // Contructor 1 (Given Root)
   //------------------------------------------------------------------------
   
   public GenealogyTree(T element)
   /*Creates a binary tree with the specified elements
    * as its root. */
   {
      root = new BTNode<T>(element);
      treeArray = (T[])(new Object[DEFAULT_CAPACITY]);
      treeArray[0] = element;
   }
   
  
   //------------------------------------------------------------------------
   // Contructor 2 (Given Root and Subtrees)
   //------------------------------------------------------------------------
   
   public GenealogyTree(T element, GenealogyTree left, GenealogyTree right)
   {
      root = new BTNode<T>(element);
      root.setLeft(left.root);
      root.setRight(right.root);
      
      treeArray = (T[])(new Object[DEFAULT_CAPACITY]);
      treeArray[0] = element;
      
      if (this.size() == treeArray.length) {
         expandCapacity();
      }
      
      int leftIndex = left.getOffspring
   
   }
   


   
   //------------------------------------------------------------------------
   // Helper Method (Expand Capacity)
   //------------------------------------------------------------------------
   
   public void expandCapcity()
   /*Expands the capacity of the treeArray when the array is full. */
   {
      T[] tempArray = (T[])(new Object(treeArray.length * 2));
      
      for (int i = 0; i < treeArray.length; i++) 
         tempArray[i] = treeArray[i];
      
      treeArray = tempArray;
   }
   
   //------------------------------------------------------------------------
   // Helper Method (Expand Capacity)
   //------------------------------------------------------------------------             
     
   public int getIndex(T element)
   {
      for (int i = 0; i < arrayTree.length; i++) {
         if (arrayTree[i] == element)
            return i;
      }
      return -1;
   }
         
                 
   //------------------------------------------------------------------------
   // Get Root Method (CoTU)
   //------------------------------------------------------------------------
   
   public T getCoTU() //Center of The Universe
   /*Returns the element stored in the root (aka CoTU) of the tree. */
   {
      if (root == null)
         throw new EmptyCollectionException("Get CoTU operation failed. "
                                               + "The tree is empty.");
      return root.getElement();
   }

   
   
  
  public T getOffspring(T target); 
  /*Returns the offspring of the target; Returns null as the 
   * offspring of the root. This is the "parent" in tree terminology.*/
  {
     
   
   public int size()
   {
      int result = 0;
      
      if (root != null)
         result = root.count();
      
      return result;
   }
   public String toString() 
   {
      String s = "";
      
      for (int i = 0; i < this.size(); i++)
         s += treeArray[i] + "  ";
      
      return s;
//      String result = "";
//      Iterator<T> iter = iterator();
//      while (iter.hasNext())
//         result += iter.next() + "\n";
//      return result;
  }
  
      
      
   public static void main(String[] args)
   {
      GenealogyTree<String> tree1 = new GenealogyTree<String>("Amal");
      System.out.println(tree1);
   }
}

