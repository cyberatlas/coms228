package edu.iastate.cs228.hw2;

/**
 * @author Alex Stevenson
 */

import java.io.*;
import java.util.Comparator;
import java.lang.IllegalArgumentException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort, and QuickSort.
 * It stores the input (later on the sorted) sequence and records the employed sorting algorithm, 
 * the comparison method, and the time spent on sorting. 
 *
 */


public abstract class AbstractSorter {

    protected Point[] points;    // Array of points operated on by a sorting algorithm.
    // The number of points is given by points.length.

    protected String algorithm = null; // "selection sort", "insertion sort",
    // "merge sort", or "quick sort". Initialized by a subclass
    // constructor.
    protected boolean sortByAngle;     // true if last sort was done by polar angle and false
    // if by x-coordinate

    protected String outputFileName;   // "select.txt", "insert.txt", "merge.txt", or "quick.txt"

    protected long sortingTime;       // execution time in nanoseconds.

    protected Comparator<Point> pointComparator;  // comparator which compares polar angle if
    // sortByAngle == true and x-coordinate if
    // sortByAngle == false

    private Point lowestPoint;        // lowest point in the array, or in case of a tie, the
    // leftmost of the lowest points. This point is used
    // as the reference point for polar angle based comparison.


    // Add other protected or private instance variables you may need.

    protected AbstractSorter() {
        // No implementation needed. Provides a default super constructor to subclasses.
        // Removable after implementing SelectionSorter, InsertionSorter, MergeSorter, and QuickSorter.
    }


    /**
     * This constructor accepts an array of points as input. Copy the points into the array points[].
     * Sets the instance variable lowestPoint.
     *
     * @param  pts  input array of points
     * @throws IllegalArgumentException if pts == null or pts.length == 0.
     */
    protected AbstractSorter(Point[] pts) throws IllegalArgumentException {

        if (pts.length == 0 || pts == null) {
            throw new IllegalArgumentException("Not enough points in the array");
        }
        
        lowestPoint = pts[0];
        
        for (int i = 0; i < pts.length; i++) {
//            if (pts[i] == null) {
//                throw new IllegalArgumentException("Null point");
//            }
//        
//
//
//            //Checks if the new point is lower than the current lowest point. If so, sets it to that
//            //If there is a tie, sets the one with lowest x to the lowest poin
//            if (pts[i].compareTo(lowestPoint) == -1) {
//                lowestPoint = pts[i];
//            } else if (pts[i].compareTo(lowestPoint) == 0) {
//                //If points[i] x is lower (more left) than the current lowest point x, then set lowestPoint as that point, otherwise lowest point is equal to itself
//
//                lowestPoint = (Math.min(pts[i].getX(), lowestPoint.getX()) == pts[i].getX()) ? pts[i] : lowestPoint;
                if(lowestPoint.getY() == pts[i].getY() && lowestPoint.getX() > pts[i].getX() || lowestPoint.getY() > pts[i].getY())
                {
                		lowestPoint = pts[i];
                }
            
        }
        points = pts.clone();

    }


    /**
     * This constructor reads points from a file. Sets the instance variables lowestPoint and
     * outputFileName.
     *
     * @param  inputFileName
     * @throws FileNotFoundException
     * @throws InputMismatchException   when the input file contains an odd number of integers
     */
    protected AbstractSorter(String inputFileName) throws FileNotFoundException, InputMismatchException {
        // TODO

        int numInts = 0, place = 0, xvar = 0, yvar = 0;
        File fileInput = new File(inputFileName);

        //Is this even right?
        if (!fileInput.exists()) {
            throw new FileNotFoundException("File does not exist");
        }

        Scanner scan = new Scanner(fileInput);
        while (scan.hasNextInt()) {
            numInts += 1;

            //If numInts is not an even number then that number is an x variable,
            if (numInts % 2 != 0) {
                xvar = scan.nextInt();
            }
            //If num ints is even then it takes the next int in as the yvar,
            // then adds a new point to the array using the x and y variables
            //Then increments the place int
            //I do not reset the xvar or yvar variables becaause both will be redefined by the time they hit the point they're used again
            if (numInts % 2 == 0) {
                yvar = scan.nextInt();
                Point p = new Point(xvar, yvar);
                points[place] = p;
                lowestPoint = (Math.min(points[place].getX(), lowestPoint.getX()) == points[place].getX()) ? points[place] : lowestPoint;
                place++;
            }
        }
        //If not an even number, that means there was not an even number of ints
        if (numInts % 2 != 0) {
            throw new InputMismatchException("Not valid number of arguments");
        }


    }


    /**
     * Sorts the elements in points[].
     *
     *     a) in the non-decreasing order of x-coordinate if order == 1
     *     b) in the non-decreasing order of polar angle w.r.t. lowestPoint if order == 2
     *        (lowestPoint will be at index 0 after sorting)
     *
     * Sets the instance variable sortByAngle based on the value of order. Calls the method
     * setComparator() to set the variable pointComparator and use it in sorting.
     * Records the sorting time (in nanoseconds) using the System.nanoTime() method.
     * (Assign the time to the variable sortingTime.)
     *
     * @param order  1   by x-coordinate
     * 			     2   by polar angle w.r.t lowestPoint
     *
     * @throws IllegalArgumentException if order is less than 1 or greater than 2
     */
    public abstract void sort(int order) throws IllegalArgumentException;


    /**
     * Outputs performance statistics in the format:
     *
     * <sorting algorithm> <size>  <time>
     *
     * For instance,
     *
     * selection sort   1000	  9200867utp
     *
     * Use the spacing in the sample run in Section 2 of the assignment description.
     */
    public String stats() {
        //In the doc there is 20 spaces for the algorithm
        String stats = String.format("%" + -20 + "s", algorithm);
        //In the doc there is 12 spaces for the size
        stats += String.format("% " + -12 + "s", points.length);
        //size is just added to the end
        stats += " " + sortingTime;
        return stats;

    }


    /**
     * Write points[] to a string.  When printed, the points will appear in order of increasing
     * index with every point occupying a separate line.  The x and y coordinates of the point are
     * displayed on the same line with exactly one blank space in between.
     */
    @Override
    public String toString() {
        String pointString = new String("");
        for (int i = 0; i < points.length; i++) {
            pointString += points[i].toString() + "\n";
        }
        return pointString;

    }


    /**
     *
     * This method, called after sorting, writes point data into a file by outputFileName. It will
     * be used for Mathematica plotting to verify the sorting result.  The data format depends on
     * sortByAngle.  It is detailed in Section 4.1 of the assignment description assn2.pdf.
     *
     * @throws FileNotFoundException
     */
    public void writePointsToFile() throws IOException {

        FileWriter fileWriter = new FileWriter(outputFileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(toString());
        printWriter.close();
    }

    /**
     * Generates a comparator on the fly that compares by polar angle if sortByAngle == true
     * and by x-coordinate if sortByAngle == false. Set the protected variable pointComparator
     * to it. Need to create an object of the PolarAngleComparator class and call the compareTo()
     * method in the Point class, respectively for the two possible values of sortByAngle.
     *
     */
    protected void setComparator() {


        //sort by angle true ==> s
        if (sortByAngle) {
            pointComparator = new PolarAngleComparator(lowestPoint);
        }
        if (!sortByAngle) {

            pointComparator = new Comparator<Point>() {
                @Override
                public int compare(Point point1, Point point2) {
                    return point1.compareTo(point2);
                }
            };
        }


    }


    /**
     * Swap the two elements indexed at i and j respectively in the array points[].
     *
     * @param i
     * @param j
     */
    protected void swap(int i, int j) {
        Point temp = points[j];
        points[j] = points[i];
        points[i] = temp;
    }
}
