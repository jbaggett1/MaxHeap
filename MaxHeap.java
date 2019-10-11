import java.util.*;
import java.util.ArrayList;
/**
 * Jillian Baggett
 * MaxHeap Class 
 */
public class MaxHeap <E extends Comparable<? super E>> 
{
    protected int size;
    protected int largestSize; 
    protected ArrayList<Node> heap;
    public static void main(String[] args)
    {
        MaxHeap<Integer> example = new MaxHeap<Integer>(10, Integer.MIN_VALUE);
        example.insert(10);
        example.insert(5);
        example.insert(2);
        example.insert(6);
        example.heapsort();
        for (int i = 0; i < example.size; i++)
        {
         System.out.println("\n" + example.heap.get(i).element.toString());
        }
        System.out.println("Second is " + example.heap.get(1).element.toString());
    }
    /**
     * Constructor for objects of class MaxHeap
     */
    //For the purpose of keeping the heap as an array, largest size must be specificed
    //by the programmer 
    public MaxHeap(int largestSize, E minValue)
    {
        //this.root = null;
        this.largestSize = largestSize;
        this.size = 0;
       
        this.heap = new ArrayList<Node>();
        //Create temporary node that has absolute minimum element, so anything greater
        //will take its place 
        //Node absMin = new Node(minValue);
        //heap.add(absMin);
        
    }
    
    //Takes in a node's index and uses relations from homework to find the parent index
    public int getParent(int currIndex)
    {
        return (int)currIndex/2; 
    }
    //Takes in the two indeces and switched the nodes,  
    public void switchPosition(int currIndex, int newPosIndex)
    {
        //Save current nodes information into a temporary node
        Node tempNode = new Node(heap.get(currIndex).element);
        //Switch the element at current index with that of the parent
        heap.get(currIndex).element = heap.get(newPosIndex).element;
        //insert the element into the new position from the temp element
        heap.get(newPosIndex).element = tempNode.element;
    }
    
    public void insert(E element)
    { //Increase the size of the heap
      this.size++;
      //Create new node with the given element 
      Node toInsert = new Node(element);
      //Insert that element to the tail end of the filled elements
      heap.add(toInsert);
      int currentIndex = size-1;
      //Check to make sure the heap is still a valid max heap 
      if (this.size > 1)
      {
             while (heap.get(currentIndex).element.compareTo(heap.get(getParent(currentIndex)).element) > 0)
      {   switchPosition(currentIndex, getParent(currentIndex));
          currentIndex = getParent(currentIndex);
        }
        }
 
      
    }
    
    
    public void heapify(ArrayList<Node> array, int size, int rootInd)
    { Node currRoot = heap.get(rootInd);
      int leftInd = rootInd * 2 + 1;
      int rightInd = rootInd * 2 + 2;
      int largestInd = rootInd;
      
      if (leftInd < size && (array.get(leftInd).element.compareTo(array.get(rootInd).element) > 0))
      {     largestInd = leftInd;
        }
      
              if (rightInd < size && (array.get(rightInd).element.compareTo(array.get(rootInd).element) > 0))
      {     largestInd = rightInd;
        }
        
       if (largestInd != rootInd)
       { switchPosition(largestInd,rootInd);
          heapify(array, size, largestInd);
        }
    }
    
    
    public void heapsort()
    {
        int size = heap.size();
        
        //Creating the heap - must call heapify on all nodes not leaves, aka less than n/2 - 1
        for (int i = size/2 - 1; i >= 0; i--)
        { heapify(heap, size, i);
        }
        
        //Extracting the largest element and placing it at end of array
        for (int i = size-1; i>=0; i--)
        {
            Node temp = new Node(heap.get(0).element);
            heap.get(0).element = heap.get(i).element;
            heap.get(i).element = temp.element;
            
            heapify(heap, i, 0);
        }
    }
    
        protected class Node {
        // since this is a private inner class, and the outer AVLTree class
        // will need to freely modify the connections and update the height
        // of its nodes, the following three variables are not private.
        Node left;
        Node right;
        int height;
        E element;

        /**
         * Construct an AVLTreeNode. At instantiation, each node has no
         * children and therefore a height of 0.
         * @param element the element that this node contains
         */
        public Node(E element) {
            this.left = null;
            this.right = null;
            this.height = 0;
            this.element = element;
        }
    }
}
