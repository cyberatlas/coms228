package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;


/**
 * @author Alexander Stevenson
 */

/**
 *
 * This class implements insertion sort.   
 *
 */

public class InsertionSorter extends AbstractSorter {
    // Other private instance variables if you need ...

    /**
     * The two constructors below invoke their corresponding superclass constructors. They
     * also set the instance variables algorithm and outputFileName in the superclass.
     */

    /**
     * Constructor takes an array of points.
     *
     * @param pts
     */
    public InsertionSorter(Point[] pts) {
        super(pts);
        outputFileName = "insert.txt";
        algorithm = "insertion sort";
    }


    /**
     * Constructor reads points from a file.
     *
     * @param inputFileName compare name of the input file
     */
    public InsertionSorter(String inputFileName) throws FileNotFoundException {
        super(inputFileName);
        outputFileName = "insert.txt";
        algorithm = "insertion sort";
    }


    /**
     * Perform insertion sort on the array points[] of the parent class AbstractSorter.
     *
     * @param order  1   by x-coordinate
     * 			     2   by polar angle
     */
    @Override
    public void sort(int order) {
        // TODO
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

    	for (int i = 1; i < points.length; i++) {
            for(int j = i ; j > 0 ; j--){
            	if (pointComparator.compare(points[j], points[j-1]) < 0) {                 
                   swap(j, j-1);
                }
            }
        }
        sortingTime = System.nanoTime() - time;
    }
}



//
//
//
//int partition(int low, int high)
//{
//    int pivot =points[high]; 
//    int i = (low-1); // index of smaller element
//    for (int j=low; j<high; j++)
//    {
//        // If current element is smaller than or
//        // equal to pivot
//        if (arr[j] <= pivot)
//        {
//            i++;
//
//            // swap arr[i] and arr[j]
//            int temp = arr[i];
//            arr[i] = arr[j];
//            arr[j] = temp;
//        }
//    }
//
//    // swap arr[i+1] and arr[high] (or pivot)
//    swap(i+1, high);
//    
//    return i+1;
//}
//
//
///* The main function that implements QuickSort()
//  arr[] --> Array to be sorted,
//  low  --> Starting index,
//  high  --> Ending index */
//void sort(int arr[], int low, int high)
//{
//    if (low < high)
//    {
//        /* pi is partitioning index, arr[pi] is 
//          now at right place */
//        int pi = partition(arr, low, high);
//
//        // Recursively sort elements before
//        // partition and after partition
//        sort(arr, low, pi-1);
//        sort(arr, pi+1, high);
//    }