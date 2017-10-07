package edu.iastate.cs228.hw2;

/**
 *@author Alexander Stevenson
 */
public class Tester {

    public static void main(String[] args) {

        Point p1 = new Point(-1, 6);
        Point p2 = new Point(5, -2);
        Point ref = new Point(-7,-10);
       PolarAngleComparator compare = new PolarAngleComparator(ref);
//        //Sets the points equal, should return 0
//        System.out.println(compare.compareDistance(p2, p2));
//        //p1 in this is bigger so it should return -1
//        System.out.println(compare.compareDistance(p2, p1));
//        //p1 in this case is smaller so should return 1
//        System.out.println(compare.compareDistance(p1, p2));
//
//        Point p3 = new Point(10, 4);
//        //checking the angle between the same point should return 0
//        System.out.println(compare.comparePolarAngle(p1, p1));
//        //p1 angle should be greater so it should return 1
//        System.out.println(compare.comparePolarAngle(p3, p1));
//        //p1 angle should be less so it should return -1
//        System.out.println(compare.comparePolarAngle(p1, p3));
     	System.out.println(compare.compare(p2, p1));

    }

}
