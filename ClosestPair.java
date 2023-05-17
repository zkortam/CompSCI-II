/*
 * Zakaria Kortam - COMSC 76 - Professor Estrada
 * Efficient Algorithms
 * 
 * 1. The program creates the Point class, which was imported from the previous assignment.
 * The point class implements Comparable<Point> and has the x,y fields, as well
 * as a function for point comparison.
 * 2.The points are randomly generated (100 of them) and then they are put into an array.
 * 3. The array is sorted using the Arrays.sort() function.
 * 4. The sorted array is sent into the closestPairDC() function, which
 * starts off by checking the number of points in existence. If less than 3,
 * the use of D&C is unnecessary and it'll just use the conventional means of sorting.
 * 5. If it's longer than 3, then it will implement divide and conquer.
 *   a. First, it will establish the middle of the total number of points.
 *   b. It will split the array into two smaller arrays. One for the left set
 *   of numbers that are less than the mid in index, and the right being the ones
 *   that are more than mid.
 *   c. The minimum distance is calculated
 *   d. The code creates a new array of points within a certain distance, based on the minimum, of the 
 *   mid-point of the original array, and sorts that new array based on the y-coordinate 
 *   of each point.
 *   e. The strip array is duplicated and then sorted based on Y.
 *   f. Then, a for loop is used to check the distance between the points and see which is closest, which is
 *   then returned.
 * 6. For refinement purposes, the closest pair is encapsulated in a new class called Pair.
 * 7. The results are printed.
 */

import java.util.*;

public class ClosestPair {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        header("Zakaria Kortam - COMSC 76");
        Point[] points = generatePoints(100);
        Arrays.sort(points);
        Point[] closest = closestPairDC(points);
        Pair closestPair = new Pair(closest[0], closest[1]);
        System.out.printf("Closest Pair: (%.2f, %.2f), (%.2f, %.2f)%n", closestPair.p1.x, closestPair.p1.y, closestPair.p2.x, closestPair.p2.y);
        System.out.printf("Distance between them: %.2f%n", closestPair.getDistance());
        
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Execution time: " + totalTime + " milliseconds");
    }

    public static Point[] generatePoints(int count) {
        Random r = new Random();
        Point[] points = new Point[count];
        for (int i = 0; i < count; i++) {
            points[i] = new Point(r.nextDouble() * 100, r.nextDouble() * 100);
        }
        return points;
    }

    public static Point[] closestPair(Point[] points) {
        Point[] closest = new Point[] { points[0], points[1] };
        double minDistance = distance(closest[0], closest[1]);
        Arrays.sort(points, new CompareY());
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length && points[j].y - points[i].y < minDistance; j++) {
                double dist = distance(points[i], points[j]);
                if (dist < minDistance) {
                    minDistance = dist;
                    closest[0] = points[i];
                    closest[1] = points[j];
                }
            }
        }
        return closest;
    }

    public static Point[] closestPairDC(Point[] points) {
        if (points.length <= 3) {
            return closestPair(points);
        }
        
        Arrays.sort(points);
        
        int mid = points.length / 2;
        Point[] closestLeft = closestPairDC(Arrays.copyOfRange(points, 0, mid));
        Point[] closestRight = closestPairDC(Arrays.copyOfRange(points, mid, points.length));
        
        double d = Math.min(distance(closestLeft[0], closestLeft[1]), distance(closestRight[0], closestRight[1]));
        
        Point[] strip = new Point[points.length];
        int j = 0;
        for (int i = 0; i < points.length; i++) {
            if (Math.abs(points[i].x - points[mid].x) < d) {
                strip[j] = points[i];
                j++;
            }
        }
        strip = Arrays.copyOfRange(strip, 0, j);
        Arrays.sort(strip, new CompareY());
        
        Point[] closest = new Point[2];
        closest[0] = closestLeft[0];
        closest[1] = closestLeft[1];
        
        for (int i = 0; i < strip.length; i++) {
            for (int k = i + 1; k < strip.length && (strip[k].y - strip[i].y) < d; k++) {
                double dist = distance(strip[i], strip[k]);
                if (dist < d) {
                    d = dist;
                    closest[0] = strip[i];
                    closest[1] = strip[k];
                }
            }
        }
        
        return closest;
    }
    
    
    public static double distance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    
    public static void header(String input) {
        System.out.println("\n\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.printf("<<<<<<< %s <<<<<<<<%n", input);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n\n");
    }
    
}

class Point implements Comparable<Point> {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point other) {
        if (this.x == other.x) {
            return Double.compare(this.y, other.y);
        } else {
            return Double.compare(this.x, other.x);
        }
    }
}

class CompareY implements Comparator<Point> {
    @Override
    public int compare(Point p1, Point p2) {
    return Double.compare(p1.y, p2.y);
    }
}

class Pair {
    public Point p1;
    public Point p2;
    public Pair(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
    public double getDistance() {
        return ClosestPair.distance(p1, p2);
    }
}
