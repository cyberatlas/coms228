
package edu.iastate.cs228.hw3;
/*
 *  @author Alexander Stevenson
 *
 *  An implementation of List<E> based on a doubly-linked list with an array for indexed reads/writes
 *
 */

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class AdaptiveList<E> implements List<E>
{
 
	public class ListNode{ // private member of outer class

      public E data;        // public members:
      public ListNode link; // used outside the inner class
      public ListNode prev; // used outside the inner class

   /**
    * Creates a Node with the specified data
    * @param item the data you want in the node
    */
      public ListNode(E item)
    {
      data = item;
      link = prev = null;
    }
  }

  public ListNode head;  // dummy node made public for testing.
  public ListNode tail;  // dummy node made public for testing.
  private int numItems;  // number of data items
  private boolean linkedUTD; // true if the linked list is up-to-date.

  public E[] theArray;  // the array for storing elements
  private boolean arrayUTD; // true if the array is up-to-date.
/**
 * Creates the doubly linked list
 */
  public AdaptiveList()
  {
    clear();
  }


  @Override
  public void clear()
  {
    head = new ListNode(null);
    tail = new ListNode(null);
    head.link = tail;
    tail.prev = head;
    numItems = 0;
    linkedUTD = true;
    arrayUTD = true;
    theArray = null;
  }

/**
 * Gets the value from linkedUTD to see if the linked list is up to date 
 * @return linkedUTD 
 */
  public boolean getlinkedUTD()
  {
    return linkedUTD;
  }
/**
 * Gets the value from arrayUTD to see if the array is up to day
 * @return arrayUTD
 */
  public boolean getarrayUTD()
  {
    return arrayUTD;
  }
/**
 * Creates a double linked list
 * @param c the collection that we are adding to the list
 */
  public AdaptiveList(Collection<? extends E> c)
  {
    clear();
    addAll(c);
  }

   
  /**
   *Removes the node from the linked list.
   *This method should be used to remove a node from the linked list. 
   * @param toRemove the node to remove
   */
  private void unlink(ListNode toRemove)
  {
    if ( toRemove == head || toRemove == tail )
      throw new RuntimeException("An attempt to remove head or tail");
    toRemove.prev.link = toRemove.link;
    toRemove.link.prev = toRemove.prev;
  }

  /**
   * Inserts new node toAdd right after old node current.
   *  This method should be used to add a node to the linked list.
   * @param current the current node to add after
   * @param toAdd Node to add
   */
  private void link(ListNode current, ListNode toAdd)
  {
    if ( current == tail )
      throw new RuntimeException("An attempt to link after tail");
    if ( toAdd == head || toAdd == tail )
      throw new RuntimeException("An attempt to add head/tail as a new node");
    toAdd.link = current.link;
    toAdd.link.prev = toAdd;
    toAdd.prev = current;
    current.link = toAdd;
  }
/**
 * Loops through the linked list and updates the array based on it
 */
  private void updateArray() 
  {
    if ( numItems < 0 )
      throw new RuntimeException("numItems is negative: " + numItems);
    if ( ! linkedUTD )
      throw new RuntimeException("linkedUTD is false");

    E[] newarray = (E[]) new Object [numItems];
    ListNode cur;
    int j = 0;
    for(cur = head.link; cur != tail; cur = cur.link, j++) {
    	newarray[j] = cur.data;
    }
   theArray = newarray;
    arrayUTD = true;

  }
/**
 * Loops through the array and makes the linked list up to date
 */
  private void updateLinked() 
  {
    if ( numItems < 0 )
      throw new RuntimeException("numItems is negative: " + numItems);
    if ( ! arrayUTD )
      throw new RuntimeException("arrayUTD is false");

    if ( theArray == null || theArray.length < numItems )
      throw new RuntimeException("theArray is null or shorter");


    head.link = tail;
    tail.prev = head;
    ListNode current = head;
    for(int i = 0; i < theArray.length;i++) {
    	ListNode  p = new ListNode(theArray[i]);
    	link(current, p);
    	current = current.link;
    }
linkedUTD = true;
  }

  @Override
  public int size()
  {
    int count = 0;
    ListNode n = head;
    while (n.link != tail){
      count++;
      n = n.link;
    }


    return count; 
  }

  @Override
  public boolean isEmpty()
  {

    if (head.link ==  tail && tail.prev == head){return true;}

    return false; 
  }

  @Override
  public boolean add(E obj)
  {
	if (!linkedUTD) {updateLinked();}
    ListNode n = new ListNode(obj);
    link(tail.prev,n);
    numItems++;
    arrayUTD = false;
    
    
    return true; 
  }

  @Override
  public boolean addAll(Collection< ? extends E> c) throws NullPointerException
  {
	  int i = 0;
	  if (!linkedUTD) {updateLinked();}
    Iterator temp = c.iterator();
    if (c ==null) {throw new NullPointerException();}
    if (c.isEmpty()) {return false;}

    
    E[] temp2 = (E[]) new Object[c.size()];
    for(E e: c)
    {
    	temp2[i] = e;
    	i++;
    }
    for(E e: temp2)
    {
    	add(e);
    }
    arrayUTD = false;
    


    return true;   } 

  @Override
  public boolean remove(Object obj)
  {

    
    ListNode n = head;
    //Loops through the list and checks every element if it equals the object.
    while (n.data != obj){
      n= n.link;
      if (n.data == obj){

    	  unlink (n);
        numItems--;
       arrayUTD = false;
        return true;
      }
    }
    return false; 
  }
/**
 * Checks to make sure the specified index is valid, if not it throws an error
 * @param pos the index to check
 * @throws IndexOutOfBoundsException 
 */
  private void checkIndex(int pos) throws IndexOutOfBoundsException// a helper method
  {
    if ( pos >= numItems || pos < 0 )
     throw new IndexOutOfBoundsException(
       "Index: " + pos + ", Size: " + numItems);
  }

  /**
  * Checks to make sure the specified index is valid, if not it throws an error
  * @param pos the index to check
  * @throws IndexOutOfBoundsException 
  */
  private void checkIndex2(int pos)throws IndexOutOfBoundsException // a helper method
  {
    if ( pos > numItems || pos < 0 )
     throw new IndexOutOfBoundsException(
       "Index: " + pos + ", Size: " + numItems);
  }
/**
 * Makes sure the node is not null and is not the tail
 * @param cur takes in the current node that we are looking at
 * @throws RuntimeException
 */
  private void checkNode(ListNode cur)throws RuntimeException // a helper method
  {
    if ( cur == null || cur == tail )
     throw new RuntimeException(
      "numItems: " + numItems + " is too large");
  }
  /**
   * Give an index and this returns the node before the node you want. gives you 1 node before the one you want
   * @param pos the index of the node you want 1 before
   * @return
   */
  private ListNode findNode(int pos)   // a helper method
  {
    ListNode cur = head;
    for ( int i = 0; i < pos; i++ )
    {
      checkNode(cur);
      cur = cur.link;
    }
    checkNode(cur);
    return cur;
  }

  @Override
  public void add(int pos, E obj)
  {
	  
	  if (pos ==0) {addFirst(obj);}
	  else if (pos>=numItems) {addLast(obj);}
	  else {
		 ListNode current = head;
		 for(int i=1; i< pos; i++) {
			 current = current.link;
			 
		 }
		 ListNode temp = current.link;
		 current.link = new ListNode (obj);
		 current.link.link = temp;
		 numItems++;
		 
		 
	  }
	  
	 }
  /**
   * The code called to add a node to the first part of a list
   * @param e data to add
   */
public void addFirst(E e ) {
	ListNode newNode = new ListNode(e);
	newNode.link = head;
	head = newNode;
	numItems++;
	if (tail == null) {tail =head;}
}
/**
 * Used to add a node to the end of a list 
 * @param e data to add
 */
public void addLast(E e) {
	ListNode newNode = new ListNode(e);
	if (tail == null) {head = tail= newNode;}
	else {
		tail.link = newNode;
		tail = tail.link;
	}
	numItems++;
}
  @Override
  public boolean addAll(int pos, Collection< ? extends E> c) throws IndexOutOfBoundsException
  {
	if (pos < 0 || pos > c.size()) {throw new IndexOutOfBoundsException();}  
	if (linkedUTD == false) {updateLinked();}
	boolean isAdded = false;

	int i = 0;
	E[] arr = (E[]) new  Object[(c.size())];

	if (c == null) {throw new NullPointerException();}
	if (c.isEmpty()) {return false;}
	

	
	for (E e: c ) {
		arr[i] = e;
		i++;
	}
	for (E e : arr) {
		add(pos++, e);
		arrayUTD =false;
	}
	
    return isAdded; }

  @Override
  public E remove(int pos)
  {
	  if (!linkedUTD) {updateLinked();}
	  checkIndex2(pos);
    ListNode n = findNode(pos).link;
    
    E data = n.data;
    unlink(n);
    arrayUTD = false;
    return data; 
  }

  @Override
  public E get(int pos)
  {
    
	if (!arrayUTD) {updateArray();}
	checkIndex(pos);
	


    return theArray[pos]; 
  }

  @Override
  public E set(int pos, E obj)
  {
    if(!arrayUTD) {
    	updateArray();
    }
    checkIndex2(pos);
    E data = theArray[pos];
    theArray[pos] = obj;
    linkedUTD = false;
   
    return data; 
  }

 /**
  *   If the number of elements is at most 1, the method returns false.
  *   Otherwise, it reverses the order of the elements in the array without using any additional array, and returns true.
  *   Note that if the array is modified, then linkedUTD needs to be set to false.
  * @return reversed array
  */
  public boolean reverse()
  {
	  if(!arrayUTD) {updateArray();}
    if (numItems<=1){return false;}
    
    for (int i = 0; i < theArray.length / 2;i++) {
    	E temp = theArray[i];
    	theArray[i] = theArray [ theArray.length - i - 1];
    	theArray [ theArray.length -i - 1 ] = temp;
    	
    }
    linkedUTD =false;
    return true;
  }

  @Override
  public boolean contains(Object obj)
  {
	  if (linkedUTD == false) {updateLinked();}
    
	  AdaptiveListIterator l = new AdaptiveListIterator();
    while (l.hasNext()){
      if (l.next() == obj){
        return true;
      }
    }
	  
   return false;
  }

  /**
   * True if this list contains all the specified elements
   * @param c collection to be checked
   * @return true if if list contains all specified elemetns
   */
  @Override
  public boolean containsAll(Collection< ? > c)
  {

    Iterator temp =  c.iterator();
    while (temp.hasNext()) {
      if (!contains(temp.next())){
        return false;
      }

    }

  return true; }

  @Override
  public int indexOf(Object obj) 
  {
	  if(!linkedUTD) {updateLinked();}
	if (!contains(obj)) {return -1;} 
	int index =0; 
	ListNode current;
	for (current = head.link; current != tail; current = current.link, index++) {
		if (current.data == obj || current.data.equals(obj)) {return index;}
	}


    return -1; 
  }

  @Override
  public int lastIndexOf(Object obj)
  {

	  int index = 0; 
		int lastIndex = -1;;
		ListNode current;
		for (current = head.link; current != tail; current = current.link, index++) {
			if (current.data == obj || current.data.equals(obj)) {lastIndex = index;}
		
    }
    return lastIndex;
  }

  @Override
  public boolean removeAll(Collection<?> c)
  {
    
	  boolean isChanged = false;
	  ListIterator <E> l = listIterator();

		while (l.hasNext()) {
			if (c.contains(l.next())) {
				l.remove();
				isChanged = true; 
			}
		}
	  
    return isChanged; 
  }

  @Override
  public boolean retainAll(Collection<?> c)
  {
 
    Iterator temp = c.iterator();
    AdaptiveListIterator l = new AdaptiveListIterator();

    
    for (int i=0; i<numItems; i++){
      if (!c.contains(l.next())){
        l.remove();
      }
    }

    return true; }

  
  @Override
  public Object[] toArray()
  {
	  
	  Object[] arr = new Object[numItems];
	    ListIterator<E> iter = listIterator();
	    for(int i = 0; i < numItems; i++)
	       arr[i] = iter.next();
	    return arr;
  }

  @Override
  public <T> T[] toArray(T[] arr)
  {
  
	  if(arr.length < numItems)
	       arr = Arrays.copyOf(arr, numItems);
	    System.arraycopy(toArray(), 0, arr, 0, numItems);
	    if(arr.length > numItems)
	       arr[numItems] = null;
	    return arr;
	  
  }

  @Override
  public List<E> subList(int fromPos, int toPos)
  {
    throw new UnsupportedOperationException();
  }

  private class AdaptiveListIterator implements ListIterator<E>
  {
    private int    index;  // index of next node;
    private ListNode cur;  // node at index - 1
    private ListNode last; // node last visited by next() or previous()

    public AdaptiveListIterator()
    {
      
      index = 0;
      cur = new ListNode(null);
      cur.prev =head;
      cur.link = head.link;
      
      if ( ! linkedUTD ) updateLinked();
    }
    public AdaptiveListIterator(int pos)
    {
      index = 0;
      cur = new ListNode(null);
      cur.prev =head;
      cur.link = head.link;
      while(index < pos){
        next();
        
      }
      if ( ! linkedUTD ) updateLinked();

      
    }

    @Override
    public boolean hasNext()
    {
      
    if (cur.link == null) {return false;}  
    	return true; 
    }

    @Override
    public E next() throws NoSuchElementException
    {
      
    if (!hasNext()) {throw new NoSuchElementException(); }  
   
    
    ListNode temporary = cur;
    cur  = cur.link;
    cur.prev = temporary;
      
      index++;

      last = cur.prev;
      return cur.data;     
    }

    @Override
    public boolean hasPrevious()
    {
   
    	//might also be cur.prev.data
      if (cur.prev !=  head){return true;}
      return false; // may need to be revised.
    }

    @Override
    public E previous() throws IndexOutOfBoundsException
    {
    	
    	
    	if(linkedUTD == false) {updateLinked();}
   
    if (hasPrevious() ==false){ throw new NoSuchElementException();}
    		
    last = cur;
    cur = cur.prev;     
    index--;
   	
      return last.data;
    }

    @Override
    public int nextIndex()
    {
    if (index >= numItems) {return numItems;}
    	
      return index++; // may need to be revised
    }

    @Override
    public int previousIndex()
    {
      
      return index--; // may need to be revised.
    }

    @Override
	public void remove()
    {
    	
     if (last != null) {unlink(last);}
      
    }

    @Override
	public void add(E obj) 
    {
      if (!linkedUTD) {updateLinked();}
      ListNode n = new  ListNode(obj);

      
      link(cur, n);
      index++;
      numItems++;
      
      cur = cur.link;
      arrayUTD =false;
    } 

    @Override
    public void set(E obj)
    {
      // sets the last node iterated over to obj

    	last.data = obj;
    	arrayUTD =false;
    } 
  }

  @Override
  public boolean equals(Object obj)
  {
    if ( ! linkedUTD ) updateLinked();
    if ( (obj == null) || ! ( obj instanceof List<?> ) )
      return false;
    List<?> list = (List<?>) obj;
    if ( list.size() != numItems ) return false;
    Iterator<?> iter = list.iterator();
    for ( ListNode tmp = head.link; tmp != tail; tmp = tmp.link )
    {
      if ( ! iter.hasNext() ) return false;
      Object t = iter.next();
      if ( ! (t == tmp.data || t != null && t.equals(tmp.data) ) )
         return false;
    }
    if ( iter.hasNext() ) return false;
    return true;
  }

  @Override
  public Iterator<E> iterator()
  {
    return new AdaptiveListIterator();
  }

  @Override
  public ListIterator<E> listIterator()
  {
    return new AdaptiveListIterator();
  }

  @Override
  public ListIterator<E> listIterator(int pos)
  {
    checkIndex2(pos);
    return new AdaptiveListIterator(pos);
  }

  // Adopted from the List<E> interface.
  @Override
  public int hashCode()
  {
    if ( ! linkedUTD ) updateLinked();
    int hashCode = 1;
    for ( E e : this )
       hashCode = 31 * hashCode + ( e == null ? 0 : e.hashCode() );
    return hashCode;
  }

  // You should use the toString*() methods to see if your code works as expected.
  @Override
  public String toString()
  {
   String eol = System.getProperty("line.separator");
   return toStringArray() + eol + toStringLinked();
  }
/**
 *  returns array of elements as a string 
 * @return array as string
 */
  public String toStringArray()
  {
    String eol = System.getProperty("line.separator");
    StringBuilder strb = new StringBuilder();
    strb.append("A sequence of items from the most recent array:" + eol );
    strb.append('[');
    if ( theArray != null )
      for ( int j = 0; j < theArray.length; )
      {
        if ( theArray[j] != null )
           strb.append( theArray[j].toString() );
        else
           strb.append("-");
        j++;
        if ( j < theArray.length )
           strb.append(", ");
      }
    strb.append(']');
    return strb.toString();
  }

  public String toStringLinked()
  {
    return toStringLinked(null);
  }

  /**
   * Returns linked list as a string 
   * @param iter iterator to loop through the list with
   * @return the string made from the linked list
   */
  public String toStringLinked(ListIterator<E> iter)
  {
    int cnt = 0;
    int loc = iter == null? -1 : iter.nextIndex();

    String eol = System.getProperty("line.separator");
    StringBuilder strb = new StringBuilder();
    strb.append("A sequence of items from the most recent linked list:" + eol );
    strb.append('(');
    for ( ListNode cur = head.link; cur != tail; )
    {
      if ( cur.data != null )
      {
        if ( loc == cnt )
        {
          strb.append("| ");
          loc = -1;
        }
        strb.append(cur.data.toString());
        cnt++;

        if ( loc == numItems && cnt == numItems )
        {
          strb.append(" |");
          loc = -1;
        }
      }
      else
         strb.append("-");

      cur = cur.link;
      if ( cur != tail )
         strb.append(", ");
    }
    strb.append(')');
    return strb.toString();
  }
}
