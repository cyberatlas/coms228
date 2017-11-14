package edu.iastate.cs228.hw4;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * @author XXXXX
 * <p>
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
            return parent;
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
            return value;
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
    public V search(K[] keyarr) throws NullPointerException {
        ErrorHandler(keyarr);
        Node n = root;
        return searchHelper(keyarr, 0, n);


//        if (keyarr == null || keyarr.length == 0) {
//            return null;
//        }
//
//        Node n = root;
//
//        for (int i = 0; i < keyarr.length; i++) {
//            if (n.child != null) {
//                if (n.child.key == keyarr[i]) {
//                    n.child();
//                } else {
//                    while (n.child).next
//                }
//            }
//        }
//
//
//        int i = 0;
//
//        if (n.child() != null) {
//            n.child();
//        }
//
//        while (n.child.next != null) {
//            if (keyarr[i]
//        }
//
//        if ()
//            // TODO
//            return null;

    //return null;

    }

    //TODO Create new private method to call itself recursively. Then do the following 3 things in that new method
    //What parameters will you need to pass recursively?
    //keyarr[], index, current node



    private V searchHelper(K[] keyarr, int i,  Node n)  {

        //TODO 1 make sure we're not on the last node, if we are, return the node value
        //if n's key is the last key in the key array, it exists, return it's value
        if (n.key().equals(keyarr[keyarr.length-1])){
            return n.value();
        }

        //TODO 2 if the child is null, return null or throw an error
        //if we have to go to a child, and the value is wrong then check the siblings. If the the next sibling is null that means the key wansn't found. Throw error.
        //The only time this is true when the child key does not equal the array key
        else if (!(n.child().key().equals(keyarr[i]))){
            //check siblings using a while loop

            Node temp = n;
            //Keep checking the siblings until we run out
            while(temp != null)
            {
                //If we find the desired key, search for the next one again.
                if (temp.next().key().equals(keyarr[i])){

                    return searchHelper(keyarr, i++, temp.next);

                }
               //TODO is there something in here to check to amke sure we are not dealing with a childless and siblingless node
                //Do I need that?
                //increments
                temp = temp.next;
            }
        }

        //happens only when the child key equals the array key, runs again
        return searchHelper(keyarr, i++, n.child);

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
        // TODO check
        Node n = root.child;
        int x = 0;
        loop1:
        for (int i = 0; i < keyarr.length; i++) {
            if (n != null) {
                //if1: is labeling the line so it's easier to continue the loop

                if (n.key == keyarr[i]) {
                    //If it is at length-1 then it is at the end of the key array and it is time to save the value
                    if (i == keyarr.length - 1) {
                        n.value = aValue;
                        return true;
                    }
                    try {
                        n.value =aValue;
                        n = n.child;
                        //continue;
                    } catch (NullPointerException e) {
                        System.out.println(e);
                    }
                } else {
                    while (n.next() != null) {
                        if (n == keyarr[i]) {
                            n.next();
                            //Goes back to the first loop
                            continue loop1;
                        } else {
                            continue;
                        } //Runs the while loop again checking for siblings
                    }
                    //created a new Node, assuming null is it's new value
                    Node n2 = null;
                    n.next = n2;
                    n2.key = keyarr[i];
                    n2.parent = n.parent;

                    while (i < keyarr.length) {
                        Node temp = null;
                        n2.child = n;
                        temp.key = keyarr[i];
                        n2 = temp;
                        i++;
                    }
                    return true;

                }
            }
        }

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
        System.out.println("null -> null");
        showHelper(root, 0, false);
    }
    private void showHelper(Node n, int spaces, boolean printedChild){
        //if the child is not equal to null and we haven't printed that child yet
        baseCase: if (!(n.child.equals(null))&& printedChild == false){
            //Print the spaces, increment the spacing by 1 then print the node and it's value
            spacing(spaces);
            spaces++;
            System.out.print(n.child.key + " -> " + n.child.value);
            showHelper(n.child, spaces, false);
        }
        else if(!(n.next.equals(null))){
                //Don't need to increment the space because you are keeping track of the spacing as you are traversing through
                spacing(spaces);
                System.out.print(n.child.key + " -> " + n.child.value);
                //Printed is false because you're in a different dimentsion of the array
                showHelper(n.next, spaces, false);
        }
        //When you call this case, you will be near the end of the last next, this is where we say the child has been printed and work back up
        //Make sure the the parent is not the root.
         else if (!(n.parent.equals(null))){
            //Going back up, decrement the spaces
            spaces--;
            //We are printing the parent, with less spaces, and we already printed the child
            showHelper(n.parent, spaces, true);
        }

    }
    private void spacing(int numPrevSpaces){
        String s = ("\t");
        for(int i= 0; i < numPrevSpaces; i++){
            //In the example it increments by 1 tab
            s.concat("\t");
        }
        //when the string is made with the appropriate number of tabs print the string
        System.out.print(s);
    }


    public void ErrorHandler(K[] keyarr) {

        for (K E : keyarr) {
            if (E == null) {
                throw new NullPointerException();
            }
        }
    }

//    //Did I write this? What does it even do?
//    private boolean check (K[] keyarr[], int i, Node n ){
//
//        if (n.value == keyarr[i]){
//            i++;
//        }
//        if(n.child.value == keyarr[i] ) {
//            i++;
//        }
//        if(n.next){}
//        return true
//    }

}

