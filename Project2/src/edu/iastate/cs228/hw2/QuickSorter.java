package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;


/**
 * @author Alexander Stevenson
 */

/**
 *
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 *
 */

public class QuickSorter extends AbstractSorter {

    // Other private instance variables if you need ...

    /**
     * The two constructors below invoke their corresponding superclass constructors. They
     * also set the instance variables algorithm and outputFileName in the superclass.
     */

    /**
     * Constructor accepts an input array of points.
     *
     * @param pts   input array of integers
     */
    public QuickSorter(Point[] pts) {
        super(pts);
        outputFileName = "quick.txt";
        algorithm = "quick sort";

    }


    /**
     * Constructor reads points from a file.
     *
     * @param inputFileName  name of the input file
     */
    public QuickSorter(String inputFileName) throws FileNotFoundException {

        super(inputFileName);
        outputFileName = "quick.txt";
        algorithm = "quick sort";
    }


    /**
     * Carry out quicksort on the array points[] of the AbstractSorter class.
     *
     * @param order  1   by x-coordinate
     * 			     2   by polar angle
     *
     */
    @Override
    public void sort(int order) {
        
    	long time = System.nanoTime();

        if (order == 1) {
            sortByAngle = false;
            setComparator();
        }
        if (order == 2) {
            sortByAngle = true;
            setComparator();
        }
        if (order != 1 && order != 2) {
            throw new IllegalArgumentException("Invalid range for order");
        }
        
    		int last = points.length-1; 
		
		//Does QuickSort
		quickSortRec(0,last);
		
        sortingTime = System.nanoTime() - time;

    }


    /**
     * Operates on the subarray of points[] with indices between first and last.
     *
     * @param first  starting index of the subarray
     * @param last   ending index of the subarray
     */
    private void quickSortRec(int first, int last) {
        // TODO
    	
    	
    	if(first >= last){ 
			return;
		}
		int part = partition(first, last);
		quickSortRec(first,part-1);
		quickSortRec(part+1,last);
    }


    /**
     * Operates on the subarray of points[] with indices between first and last.
     *
     * @param first
     * @param last
     * @return
     */
    private int partition(int first, int last) {
        // TODO
    	//Uses last element as pivot
    			Point pivot = points[last];
    			int i = first-1;
    			for(int j=first;j<last;j++){
    				if(pointComparator.compare(points[j], pivot) <0){ 
    					i=i+1;
    					swap(i,j); 
    				}
    			}
    			swap(i+1,last);
    			return i+1; 
    }


    
}
