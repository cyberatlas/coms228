package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.lang.IllegalArgumentException; 

/**
 *  
 * @author Alexander Stevenson 
 *
 */

/**
 * 
 * This class implements the merge sort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/** 
	 * Constructor accepts an input array of points. 
	 * in the array. 
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "merge sort";
		outputFileName = "merge.txt";
	}
	
	
	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException 
	 */
	public MergeSorter(String inputFileName) throws InputMismatchException, FileNotFoundException 
	{
		super(inputFileName);
		algorithm = "merge sort";
		outputFileName = "merge.txt";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 *
	 */
	@Override 
	public void sort(int order)
	{
		long time = System.nanoTime();
		
		if(order < 1 || order > 2) throw new IllegalArgumentException();
		
		if(order == 1) {
			sortByAngle = false;
					}
		if(order == 2) 
		{sortByAngle = true;
		}
		
		setComparator();
		
		mergeSortRec(points);
		
		sortingTime = System.nanoTime() - time;
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		if(pts.length <= 1)
		{
			return;
		}
		else
		{
			int middle = pts.length/2;
			Point[] first = new Point[middle];
			Point[] second = new Point[pts.length - middle];
			
			for(int i = 0; i < first.length; ++i)
			{
				first[i] = pts[i];
			}
			for(int i = 0; i < second.length; ++i)
			{
				second[i] = pts[i+middle];
			}
			
			mergeSortRec(first);
			mergeSortRec(second);
			
			Point[] merged = merge(first, second);
			
			for(int i = 0; i < merged.length; i++)
			{
				pts[i] = merged[i];
			}
		}
	}

	
	// Other private methods in case you need ...
	private Point[] merge(Point[]firstHalf, Point[] secondHalf)
	{
		Point[] merged = new Point[firstHalf.length + secondHalf.length];
		
		int i = 0; int j = 0; int k = 0;
		
		while(j < firstHalf.length && k < secondHalf.length)
		{
			if(pointComparator.compare(firstHalf[j], secondHalf[k]) == -1)
			{
				merged[i++] = firstHalf[j++];
			}
			else
			{
				merged[i++] = secondHalf[k++];
			}
		}
		
		if(j >= firstHalf.length)
		{
			while(k < secondHalf.length)
			{
				merged[i++] = secondHalf[k++];
			}
		}
		
		else
		{
			while(j < firstHalf.length)
			{
				merged[i++] = firstHalf[j++];
			}
		}
		return merged;
	}
}
