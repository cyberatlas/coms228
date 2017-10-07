package edu.iastate.cs228.hw2;

/**
 * @author Alex Stevenson
 */

import java.util.Comparator;

/**
 *
 * This class compares two points p1 and p2 by polar angle with respect to a reference point.  
 * It is known that the reference point is not above either p1 or p2, and in the case that
 * either or both of p1 and p2 have the same y-coordinate, not to their right.
 *
 */
public class PolarAngleComparator implements Comparator<Point> {
    private Point referencePoint;

    /**
     *
     * @param p reference point
     */
    public PolarAngleComparator(Point p) {
        referencePoint = p;
    }

 

    /**
     * Use cross product and dot product to implement this method.  Do not take square roots
     * or use trigonometric functions. See the PowerPoint notes on how to carry out cross and
     * dot products. Calls private methods crossProduct() and dotProduct().
     *
     * Call comparePolarAngle() and compareDistance().
     *
     * @param p1
     * @param p2
     * @return 0 if p1 and p2 are the same point
     *         -1 otherwise, if one of the following three conditions holds:
     *                a) p1 and referencePoint are the same point (hence p2 is a different point);
     *                b) neither p1 nor p2 equals referencePoint, and the polar angle of
     *                   p1 with respect to referencePoint is less than that of p2;
     *                c) neither p1 nor p2 equals referencePoint, p1 and p2 have the same polar
     *                   angle w.r.t. referencePoint, and p1 is closer to referencePoint than p2.
     *
     *          1  otherwise.
     *
     */
    public int compare(Point p1, Point p2) {

        //If both return 0 then they are the same point
        if (areSamePoint(p1, p2)) {
            return 0;
        }
        //If p1 and reference point are not the same point and the p2 is not the same as p1 and reference OR
        //If p1 and p2 don't equal reference point AND the polar angle of p1 is less than p2 OR
        //Neither p1 nor p2 equal reference point AND  p1 and p2 have the same polar angle AND p1 is closer to the reference point than p2
//        else if (areSamePoint(p1, referencePoint) ||
//
//                (!areSamePoint(p1, referencePoint) && !areSamePoint(p2, referencePoint) && comparePolarAngle(p1, p2) == -1) ||
//
//                (!(areSamePoint(p1, referencePoint)) && !areSamePoint(p2, referencePoint) && comparePolarAngle(p1, p2) == 0 && (compareDistance(p1, p2) == -1))
//                ) {
//            return -1;
//        }
        
        else if(p1.equals(this.referencePoint) || 
				(!p1.equals(referencePoint) && !p2.equals(referencePoint)) && this.comparePolarAngle(p1, p2) == -1 ||
				(!p1.equals(referencePoint) && !p2.equals(referencePoint)) && this.comparePolarAngle(p1, p2) == 0 && this.compareDistance(p1, p2) == -1
				) {
			return -1;
		}
        //If neither of the above conditions are true, returns 1
        return 1;
    }


    /**
     * Compare the polar angles of two points p1 and p2 with respect to referencePoint.  Use
     * cross products.  Do not use trigonometric functions.
     *
     * Ought to be private but made public for testing purpose.
     *
     * @param p1
     * @param p2
     * @return 0  if p1 and p2 have the same polar angle.
     * 			 -1  if p1 equals referencePoint or its polar angle with respect to referencePoint
     *               is less than that of p2.
     *            1  otherwise.
     */
    public int comparePolarAngle(Point p1, Point p2) {

        //Creates a new point that is point 1 distance away from the reference point
//        Point p1dis = new Point(p1.getX() - referencePoint.getX(), p1.getY() - referencePoint.getY());
//
//        //Creates a new point that is point 2 distance away from reference point
//        Point p2dis = new Point(p2.getX() - referencePoint.getX(), p2.getY() - referencePoint.getY());


        //if  greater than 0 ==> p1 is less than p2
        if (crossProduct(p1, p2) == 0) {
            return 0;
        } if (areSamePoint(p1,referencePoint) || crossProduct(p1, p2) > 0) {
            return -1;
        } else {
            return 1;
        }

    }


    /**
     * Compare the distances of two points p1 and p2 to referencePoint.  Use dot products. 
     * Do not take square roots. 
     *
     * Ought to be private but made public for testing purpose.
     *
     * @param p1
     * @param p2
     * @return 0   if p1 and p2 are equidistant to referencePoint
     * 			 -1   if p1 is closer to referencePoint 
     *            1   otherwise (i.e., if p2 is closer to referencePoint)
     */
    public int compareDistance(Point p1, Point p2) {

        // find normalized1 (p1 - referencePoint)
        // find normalized2 (p2 - referencePoint)
        // return the sign of (normalized1.x^2 + normalized1.y^2) - (normalized2.x^2 + normalized2.y^2)

        //Creates a new point that is point 1 distance away from the reference point
        //Point p1dis = new Point(p1.getX() - referencePoint.getX(), p1.getY() - referencePoint.getY());

        //Creates a new point that is point 2 distance away from reference point
        //Point p2dis = new Point(p2.getX() - referencePoint.getX(), p2.getY() - referencePoint.getY());

        //find which distance is bigger
//        if (((p1dis.getX() * p1dis.getX()) + p1dis.getY() * p1dis.getY()) > ((p2dis.getX() * p2dis.getX()) + p2dis.getY() * p2dis.getY())) {
//            return -1;
//        } else if ((((p1dis.getX() * p1dis.getX()) + p1dis.getY() * p1dis.getY()) < ((p2dis.getX() * p2dis.getX()) + p2dis.getY() * p2dis.getY()))) {
//            return 1;
//        } else {
//            return 0;
//        }
    	
    	if(dotProduct(p1, p1) == dotProduct(p2, p2)) {
    		return 0;
    	}
    	if(dotProduct(p1, p1)< dotProduct(p2, p2)) {
    		return -1;
    	}
    	else {
    		return 1;
    	}

    }


    /**
     *
     * @param p1
     * @param p2
     * @return cross product of two vectors p1 - referencePoint and p2 - referencePoint
     */

    private int crossProduct(Point p1, Point p2) {
        //cross product formula:  x1y2-x2y1
    
        return ((p1.getX() - referencePoint.getX()) * (p2.getY() - referencePoint.getY())) - ((p2.getX() - referencePoint.getY()) * (p1.getY() - referencePoint.getY()));
    }

    /**
     *
     * @param p1
     * @param p2
     * @return dot product of two vectors p1 - referencePoint and p2 - referencePoint
     */
    private int dotProduct(Point p1, Point p2) {
        //dot product formula: x1x1+y1y2
    		int x = (p1.getX() * p2.getX()) - referencePoint.getX();
    		int y = (p1.getY() * p2.getY())-referencePoint.getX();
    		return x+y;
    		
        //return (p1.getX() - referencePoint.getX()) * (p2.getX() - referencePoint.getX()) + (p1.getY() - referencePoint.getY()) * (p2.getY() - referencePoint.getY());
    }

    /**
     * Checks to see if the points are equal by seeing if they have the same distance and polar angle
     * @param p1
     * @param p2
     * @return If both points have the same polar angle and distance then they are the same point and this returns true
     */
    private boolean areSamePoint(Point p1, Point p2) {

       if (p1.equals(p2)) {
            return true;
        }
        return false;
    }
}
