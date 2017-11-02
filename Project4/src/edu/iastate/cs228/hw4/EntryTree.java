package edu.iastate.cs228.hw4;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * @author XXXXX
 *
 * An entry tree class
 * Add Javadoc comments to each method
 */
public class EntryTree<K, V> {
	/**
	 * dummy root node made public for grading
	 */
	protected Node root;
	
	/**
	 * prefixlen is the largest index such that the keys in the subarray keyarr
	 * from index 0 to index prefixlen - 1 are, respectively, with the nodes on
	 * the path from the root to a node. prefixlen is computed by a private
	 * method shared by both search() and prefix() to avoid duplication of code.
	 */
	protected int prefixlen;

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
			return null;
		}

		@Override
		public EntryNode<K, V> child() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public EntryNode<K, V> next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public EntryNode<K, V> prev() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public K key() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public V value() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public EntryTree() {
		root = new Node(null, null);
	}

	/**
	 * Returns the value of the entry with a specified key sequence, or null if
	 * this tree contains no entry with the key sequence.
	 * 
	 * @param keyarr
	 * @return
	 */
	public V search(K[] keyarr) {
		// TODO
		return null;
	}

	/**
	 * The method returns an array of type K[] with the longest prefix of the
	 * key sequence specified in keyarr such that the keys in the prefix label
	 * the nodes on the path from the root to a node. The length of the returned
	 * array is the length of the longest prefix.
	 * 
	 * @param keyarr
	 * @return
	 */
	public K[] prefix(K[] keyarr) {
		// TODO
		return null; // TODO
		// Hint: An array of the same type as keyarr can be created with
		// Arrays.copyOf().

	}

	/**
	 * The method locates the node P corresponding to the longest prefix of the
	 * key sequence specified in keyarr such that the keys in the prefix label
	 * the nodes on the path from the root to the node. If the length of the
	 * prefix is equal to the length of keyarr, then the method places aValue at
	 * the node P and returns true. Otherwise, the method creates a new path of
	 * nodes (starting at a node S) labelled by the corresponding suffix for the
	 * prefix, connects the prefix path and suffix path together by making the
	 * node S a child of the node P, and returns true.
	 * 
	 * @param keyarr
	 * @param aValue
	 * @return
	 */
	public boolean add(K[] keyarr, V aValue) {
		// TODO
		return false;
	}

	/**
	 * Removes the entry for a key sequence from this tree and returns its value
	 * if it is present. Otherwise, it makes no change to the tree and returns
	 * null.
	 * 
	 * @param keyarr
	 * @return
	 */
	public V remove(K[] keyarr) {
		// TODO
		return null;
	}

	/**
	 * The method prints the tree on the console in the output format shown in
	 * an example output file.
	 */
	public void showTree() {
		// TODO
	}

}
