package edu.iastate.cs228.hw2;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuickSorterTest {

	@Test	
	public void sortXTest() {
		System.out.println("Sort by X below");
		Point[]	cheese = new Point[17];
		Point[] correctX = new Point[17];
		
		cheese[0] = new Point(0,0);
		cheese[1] = new Point(-3,-9);
		cheese[2] = new Point(0,-10);
		cheese[3] = new Point(8,4);
		cheese[4] = new Point(3,3);
		cheese[5] = new Point(-6,3);
		cheese[6] = new Point(-2,1);
		cheese[7] = new Point(10,5);
		cheese[8] = new Point(-7,-10);
		cheese[9] = new Point(5,-2);
		cheese[10] = new Point(7,3);
		cheese[11] = new Point(10,5);
		cheese[12] = new Point(-7,-10);
		cheese[13] = new Point(0,8);
		cheese[14] = new Point(-1,-6);
		cheese[15] = new Point(-10,0);
		cheese[16] = new Point(5,5);
		
		QuickSorter dip = new QuickSorter(cheese);
		
		correctX[0] = new Point(-10,0);
		correctX[1] = new Point(-7,-10);
		correctX[2] = new Point(-7,-10);
		correctX[3] = new Point(-6,3);
		correctX[4] = new Point(-3,-9);
		correctX[5] = new Point(-2,1);
		correctX[6] = new Point(-1,-6);
		correctX[7] = new Point(0,-10);
		correctX[8] = new Point(0,0);
		correctX[9] = new Point(0,8);
		correctX[10] = new Point(3,3);
		correctX[11] = new Point(5,-2);
		correctX[12] = new Point(5,5);
		correctX[13] = new Point(7,3);
		correctX[14] = new Point(8,4);
		correctX[15] = new Point(10,5);
		correctX[16] = new Point(10,5);
		
		
		dip.sort(1);
		boolean pass = true;
		for(int i = 0; i < cheese.length; ++i)
		{
			if(dip.points[i].compareTo(correctX[i]) != 0)
			{
				pass = false;
			}
		}
		
		System.out.println(dip.toString());
		assertTrue(pass);
	}	
	
	@Test	
	public void sortPolarTest()
	{
		System.out.println("Sort by Polar below");
		Point[] correctPolar = new Point[17];
		Point[] cheese = new Point[17];
		cheese[0] = new Point(0,0);
		cheese[1] = new Point(-3,-9);
		cheese[2] = new Point(0,-10);
		cheese[3] = new Point(8,4);
		cheese[4] = new Point(3,3);
		cheese[5] = new Point(-6,3);
		cheese[6] = new Point(-2,1);
		cheese[7] = new Point(10,5);
		cheese[8] = new Point(-7,-10);
		cheese[9] = new Point(5,-2);
		cheese[10] = new Point(7,3);
		cheese[11] = new Point(10,5);
		cheese[12] = new Point(-7,-10);
		cheese[13] = new Point(0,8);
		cheese[14] = new Point(-1,-6);
		cheese[15] = new Point(-10,0);
		cheese[16] = new Point(5,5);
		
		QuickSorter dip = new QuickSorter(cheese);
		
		correctPolar[0] = new Point(-7,-10);
		correctPolar[1] = new Point(-7,-10);
		correctPolar[2] = new Point(0,-10);
		correctPolar[3] = new Point(-3,-9);
		correctPolar[4] = new Point(-1,-6);
		correctPolar[5] = new Point(5,-2);
		correctPolar[6] = new Point(10,5);
		correctPolar[7] = new Point(10,5);
		correctPolar[8] = new Point(7,3);
		correctPolar[9] = new Point(8,4);
		correctPolar[10] = new Point(5,5);
		correctPolar[11] = new Point(3,3);
		correctPolar[12] = new Point(0,0);
		correctPolar[13] = new Point(-2,1);
		correctPolar[14] = new Point(0,8);
		correctPolar[15] = new Point(-6,3);
		correctPolar[16] = new Point(-10,0);
		
		dip.sort(2);
		boolean pass = true;
		for(int i = 0; i < cheese.length; ++i)
		{
			if(dip.points[i].compareTo(correctPolar[i]) != 0)
			{
				pass = false;
			}
		}
		System.out.println(dip.toString());
		assertTrue(pass);
		
		
	}
}