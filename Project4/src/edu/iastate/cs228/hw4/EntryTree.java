package edu.iastate.cs228.hw4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * @author Alexander Stevenson
 *         <p>
 *         An entry tree class 
 */
public class EntryTree<K, V> {
	/**
	 * dummy root node made public for grading
	 */
	protected Node root;

	/**
	 * prefixlen is the largest index such that the keys in the subarray keyarr from
	 * index 0 to index prefixlen - 1 are, respectively, with the nodes on the path
	 * from the root to a node. prefixlen is computed by a private method shared by
	 * both search() and prefix() to avoid duplication of code.
	 */
	protected int prefixlen;
	// Instantiated as root just in case prefix does not find anything
	private Node current;

	protected class Node implements EntryNode<K, V> {
		protected Node child; // link to the first child node
		protected Node parent; // link to the parent node
		protected Node prev; // link to the previous sibling
		protected Node next; // link to the next sibling
		protected K key; // the key for this node
		protected V value; // the value at this node

		public Node(K aKey, V aValue) {
			key = aKey;
			value = aValue;
			child = null;
			parent = null;
			prev = null;
			next = null;
		}

		@Override
		public EntryNode<K, V> parent() {
			// TODO Auto-generated method stub
			return parent;
		}

		@Override
		public EntryNode<K, V> child() {
			// TODO Auto-generated method stub
			return child;
		}

		@Override
		public EntryNode<K, V> next() {
			// TODO Auto-generated method stub
			return next;
		}

		@Override
		public EntryNode<K, V> prev() {
			// TODO Auto-generated method stub
			return prev;
		}

		@Override
		public K key() {
			// TODO Auto-generated method stub
			return key;
		}

		@Override
		public V value() {
			// TODO Auto-generated method stub
			return value;
		}
	}

	public EntryTree() {
		root = new Node(null, null);
		current = root;
	}

	/**
	 * Returns the value of the entry with a specified key sequence, or null if this
	 * tree contains no entry with the key sequence.
	 *
	 * @param keyarr
	 * @return value of entry with the key sequence
	 */
	public V search(K[] keyarr) throws NullPointerException {
		if (keyarr == null || keyarr.length==0) {return null;}
		ErrorHandler(keyarr);
		Node n = root;
		return searchHelper(keyarr, 0, n);
	}

	
/**
 * 	 Private method that calls itself recursively. Then do the
 * @param keyarr array of keys that we are searching through
 * @param i our location in the key array
 * @param n the node we are currently at
 * @return
 */
	private V searchHelper(K[] keyarr, int i, Node n) {

		// Make sure we're not on the last node, if we are, return the node value
		// if n's key is the last key in the key array, it exists, return it's value
		if (n.key().equals(keyarr[keyarr.length - 1])) {
			return n.value();
		}

		// If the child is null, return null or throw an error
		// if we have to go to a child, and the value is wrong then check the siblings.
		// If the the next sibling is null that means the key wansn't found throw error.
		// The only time this is true when the child key does not equal the array key
		else if (!(n.child().key().equals(keyarr[i]))) {
			// check siblings using a while loop

			Node temp = n;
			// Keep checking the siblings until we run out
			while (temp != null) {
				// If we find the desired key, search for the next one again.
				if (temp.next().key().equals(keyarr[i])) {

					return searchHelper(keyarr, i++, temp.next);

				}
				
			
				// increments the location that we are considering
				temp = temp.next;
			}
			if (!(n.child.key.equals(keyarr[i])) && !(n.next.key.equals(keyarr[i]))) {return null;}
			
		}

		// happens only when the child key equals the array key, runs again
		return searchHelper(keyarr, i++, n.child);

	}

	/**
	 * The method returns an array of type K[] with the longest prefix of the key
	 * sequence specified in keyarr such that the keys in the prefix label the nodes
	 * on the path from the root to a node. The length of the returned array is the
	 * length of the longest prefix.
	 *
	 * @param keyarr array of keys to look through
	 * @return the keys from the tree in the key array
	 */
	public K[] prefix(K[] keyarr) {
		ArrayList<K> list = new ArrayList();
		Node n = root;
		if (keyarr == null || keyarr.length == 0) {return null;}
		int index = 0;
		while (index < keyarr.length) {
			// for (int i =0; i< keyarr.length; i++){
			// Checks to make sure there are no children
			if (n.child == null || !(n.child.key.equals(keyarr[index]))) {
				if (n.next == null) {

					return (K[]) list.toArray();
				}
				// The following code only executes if the next is not null
				if (!(n.next.key.equals(keyarr[index])))

				{
					// If the one after the next is not null, return the prefix
					if (n.next.next == null) {
						return (K[]) list.toArray();
					} else {
						n = n.next;
						continue;
					}
				}
				if (n.next.key.equals(keyarr[index])) {
					n = n.next;
					current = n;
					list.add(n.key);
					index++;
					continue;
				}
			}
			if (n.child.key == keyarr[index]) {
				n = n.child;
				current = n;
				list.add(n.key);
				index++;
				continue;
			}

		}

		
		return (K[]) list.toArray();

	}

	/**
	 * The method locates the node P corresponding to the longest prefix of the key
	 * sequence specified in keyarr such that the keys in the prefix label the nodes
	 * on the path from the root to the node. If the length of the prefix is equal
	 * to the length of keyarr, then the method places aValue at the node P and
	 * returns true. Otherwise, the method creates a new path of nodes (starting at
	 * a node S) labelled by the corresponding suffix for the prefix, connects the
	 * prefix path and suffix path together by making the node S a child of the node
	 * P, and returns true.
	 *
	 * @param keyarr array of keys to make sure ar ein the tree
	 * @param aValue the value of the last key in the tree
	 * @return true if we added the key array and the value
	 */
	public boolean add(K[] keyarr, V aValue) {
		// If prefix is 0 that means none of the words match, just add them all as children
		if (keyarr ==null || keyarr.length == 0) {return false;}
		ErrorHandler(keyarr);
		if (prefix(keyarr).length == 0) {
			for (int i = 0; i < keyarr.length; i++) {



				Node temp = new Node(keyarr[i], null);
				current.child = temp;
				temp.parent = current;
				current = temp;
			}
			current.value = aValue;
			return true;
		}

		if (prefix(keyarr).length == keyarr.length) {
			current.value = aValue;
			return true;

		} else {
			for (int i = ( prefix(keyarr).length); i < keyarr.length; i++) {

				Node temp = new Node(keyarr[i], null);
				
				if (current.child != null) {
					Node child = current.child;
					while (child.next != null) {
						child = child.next;
					}
					
					child.next = temp;
					temp.prev = child;
					temp.parent = current;
					current = temp;
					

				} 
				if(i >= keyarr.length)
				{
					current.child = temp;
					temp.parent = current;
					current = temp;
				}
			}
			current.value = aValue;
			return true;
		}

		
	}
	

	/**
	 * Removes the entry for a key sequence from this tree and returns its value if
	 * it is present. Otherwise, it makes no change to the tree and returns null.
	 *
	 * @param keyarr the array of keys to look through
	 * @return the value of the key removed
	 */
	public V remove(K[] keyarr) {
		// TODO
		if (keyarr == null || keyarr.length ==0) {
		return null;}
		V val = current.value;
		current.value = null;
		return val;
	}

	/**
	 * The method prints the tree on the console in the output format shown in an
	 * example output file.
	 */
	public void showTree() {
		// System.out.println("null -> null");
		showHelper(root, 0);
	}
/**
 * Helper method to show tree, recursively goes through the tree and adds spaces where needed and prints key and value
 * @param n
 * @param spaces
 */
	private void showHelper(Node n, int spaces) {
		System.out.println(spacing(spaces) + n.key + "=>" + n.value);
		if (n.child != null) {
			int nextSpaces = spaces + 1;
			showHelper(n.child, nextSpaces);
		}
		if (n.next != null) {
			showHelper(n.next, spaces);
		}

	}
/**
 * Private helper method that increments the spaces for showTree helper. 
 * @param numPrevSpaces
 * @return string of spaces
 */
	private String spacing(int numPrevSpaces) {
		String s = "";
		for (int i = 0; i < numPrevSpaces; i++) {
			// In the example it increments by 1 tab, this is slightly less so it is easier to read
			s = s + "  ";
		}
		// when the string is made with the appropriate number of tabs print the string
		return s;
	}
/**
 * Private helper method that makes sure nothing in the key array is null
 * @param keyarr
 */
	private void ErrorHandler(K[] keyarr) {

		if(keyarr != null) {
			for (K E : keyarr) {
				if (E == null) {
					throw new NullPointerException();
				}
			}
		}
	}
}