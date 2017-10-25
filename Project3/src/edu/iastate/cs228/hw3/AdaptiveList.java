
package edu.iastate.cs228.hw3;
/*
 *  @author
 *
 *  An implementation of List<E> based on a doubly-linked list with an array for indexed reads/writes
 *
 */

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

  public boolean getlinkedUTD()
  {
    return linkedUTD;
  }

  public boolean getarrayUTD()
  {
    return arrayUTD;
  }

  public AdaptiveList(Collection<? extends E> c)
  {
    clear();
    addAll(c);
  }

  // Removes the node from the linked list.
  // This method should be used to remove a node from the linked list.
  private void unlink(ListNode toRemove)
  {
    if ( toRemove == head || toRemove == tail )
      throw new RuntimeException("An attempt to remove head or tail");
    toRemove.prev.link = toRemove.link;
    toRemove.link.prev = toRemove.prev;
  }

  // Inserts new node toAdd right after old node current.
  // This method should be used to add a node to the linked list.
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

  private void updateArray() // makes theArray up-to-date.
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

  private void updateLinked() // makes the linked list up-to-date.
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


    return count; // may need to be revised.
  }

  @Override
  public boolean isEmpty()
  {

    if (head.link ==  tail && tail.prev == head){return true;}

    return false; // may need to be revised.
  }

  @Override
  public boolean add(E obj)
  {
	if (!linkedUTD) {updateLinked();}
    ListNode n = new ListNode(obj);
    link(tail.prev,n);
    numItems++;
    arrayUTD = false;
    
    
    return true; // may need to be revised.
  }

  @Override
  public boolean addAll(Collection< ? extends E> c) throws NullPointerException
  {
	  if (!linkedUTD) {updateLinked();}
   // Iterator temp = c.iterator();
    if (c ==null) {throw new NullPointerException();}
    if (c.isEmpty()) {return false;}
    for (E e : c) {
    		add(e);
    }
    arrayUTD = false;
    
//	 while (temp.hasNext()){
//      add((E)temp.next());
//  }

    return true; // may need to be revised.
  } // addAll 1

  @Override
  public boolean remove(Object obj)
  {

    //Do I even need this?
    //ListNode object =  new ListNode(obj);
    ListNode n = head;
    //Loops through the list and checks every element if it equals the object.
    while (n.data != obj){
      n= n.link;
      if (n.data == obj){
        n.prev = n.prev.prev;
        n.prev.link = n.link;
        numItems--;
       arrayUTD = false;
        return true;
      }
    }
    return false; // Should only tget to this point if the list does not contain the specified element
  }

  private void checkIndex(int pos) // a helper method
  {
    if ( pos >= numItems || pos < 0 )
     throw new IndexOutOfBoundsException(
       "Index: " + pos + ", Size: " + numItems);
  }

  private void checkIndex2(int pos) // a helper method
  {
    if ( pos > numItems || pos < 0 )
     throw new IndexOutOfBoundsException(
       "Index: " + pos + ", Size: " + numItems);
  }

  private void checkNode(ListNode cur) // a helper method
  {
    if ( cur == null || cur == tail )
     throw new RuntimeException(
      "numItems: " + numItems + " is too large");
  }
//Give an index and this returns the node before the node you want. gives you 1 node before the one you want
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
   //int position = 0;
	 if (!arrayUTD) {updateArray();}
   AdaptiveListIterator l = new AdaptiveListIterator(pos);
   l.add(obj);
   arrayUTD = false;
  }

  @Override
  public boolean addAll(int pos, Collection< ? extends E> c) throws IndexOutOfBoundsException
  {
	  //TODO this is fucked rn
	if (pos < 0 || pos > size()) {throw new IndexOutOfBoundsException();}  
	
    AdaptiveListIterator q = new AdaptiveListIterator(pos);
    Iterator I  =  c.iterator();

    if (!I.hasNext() ){return false; }
    while (I.hasNext() && I.next().equals(head) && I.next().equals(tail)){
    E temp=	(E)I.next(); 
    	q.add(temp);
    }

    return true; // may need to be revised.
  } // addAll 2

  @Override
  public E remove(int pos)
  {
	  if (!linkedUTD) {updateLinked();}
	  checkIndex2(pos);
    ListNode n = findNode(pos).link;
    //n.data = I.next();
    E data = n.data;
    unlink(n);
    arrayUTD = false;
    return data; // may need to be revised.
  }

  @Override
  public E get(int pos)
  {
    
	if (!arrayUTD) {updateArray();}
	checkIndex(pos);
	
//    AdaptiveListIterator I = new AdaptiveListIterator(pos);
//    ListNode n = new ListNode(I.next());
//    n.data =  I.next();

    return theArray[pos]; // may need to be revised.
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
   
    return data; // may need to be revised.
  }

  // If the number of elements is at most 1, the method returns false.
  // Otherwise, it reverses the order of the elements in the array
  // without using any additional array, and returns true.
  // Note that if the array is modified, then linkedUTD needs to be set to false.
  public boolean reverse()
  {
	  if(!arrayUTD) {updateArray();}
    if (numItems<=1){return false;}
    
    for (int i = 0; i < theArray.length / 2;i++) {
    	E temp = theArray[i];
    	theArray[i] = theArray [ theArray.length - i - 1];
    	theArray [ theArray.length -i - 1 ] = temp;
    	
    }
//    AdaptiveListIterator l = new AdaptiveListIterator(numItems);
//    for (int i=0; i < numItems; i++){
//      theArray[i] = l.previous();//gets the one before and moves the cursor
//    }
    linkedUTD =false;
    return true;
  }

  @Override
  public boolean contains(Object obj)
  {
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

  return true; // may need to be revised.
  } // containsAll


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
//    AdaptiveListIterator I = new AdaptiveListIterator();
//    int index = 0;
//    
//    
//    while(I.hasNext()){
//      if (I.next().equals( obj)){return index;}
//      index++;
//      
//    }

    return -1; // Returns -1 if it does not contain the object
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
    // TODO
    return true; // may need to be revised.
  }

  @Override
  public boolean retainAll(Collection<?> c)
  {
    // TODO- Accomplish by looping throgh the list array and checking if it has what is in the collection look at other looped stuff for reference
	//TODO USES COLLECTIONS FIXXXXXXXXCXXXXXXXXXXXXXXX
	  //ListNode l;
	  
	//for () {}
    Iterator temp = c.iterator();
    AdaptiveListIterator l = new AdaptiveListIterator();
//    for (int i =0; i < numItems; i++) {
//    		if ()
//    	
//    }
    
    for (int i=0; i<numItems; i++){
      if (!c.contains(l.next())){
        l.remove();
      }
    }

    return true; // may need to be revised.
  }

  /**
   *
   * @return instance of array
   */
  @Override
  public Object[] toArray()
  {
    if(theArray == null){return null;}

    E[] temp = (E[]) new Object[numItems];
    for (int i=0; i < numItems; i++){
      temp[i] = theArray[i];
    }

    return temp; // may need to be revised.
  }

  @Override
  public <T> T[] toArray(T[] arr)
  {
    // TODO
    return null; // may need to be revised.
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
      //if (cur.link != null || cur.link != tail || cur.link != head){return true;}
    if (cur.link == null) {return false;}  
    	return true; // may need to be revised.
    }

    @Override
    public E next() throws NoSuchElementException
    {
      //Sets the previous node to the next, sets the next node to the one after.
      //Returns the next's data
    if (!hasNext()) {throw new NoSuchElementException(); }  
   
    
    ListNode temporary = cur;
    cur  = cur.link;
    cur.prev = temporary;
      //cur.link = cur.link.link;
      index++;
//      cur = cur.link.link;
      last = cur.prev;
      return cur.data; // may need to be revised.
    
    }

    @Override
    public boolean hasPrevious()
    {
   
    	//might also be cur.prev.data
      if (cur.prev != null || cur.prev !=  head){return true;}
      return false; // may need to be revised.
    }

    @Override
    public E previous()
    {
    	
    ListNode temporary = cur;
    
    cur = cur.prev;
    	cur.link = temporary;
//    cur.prev = cur.prev.prev;
  
    	//cur = cur.prev;
      last = cur.link;
      index--;

      return cur.data;
    }

    @Override
    public int nextIndex()
    {
     
    	
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
//      n.prev = tail.prev;
//      n.prev.link =n;
//      n.link.prev = n;
      
      link(cur, n);
      index++;
      numItems++;
      
      cur = cur.link;
      arrayUTD =false;
    } // add

    @Override
    public void set(E obj)
    {
      // sets the last node iterated over to obj

    	last.data = obj;
    	arrayUTD =false;
    } // set
  } // AdaptiveListIterator

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
  } // equals

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

  // iter can be null.
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
