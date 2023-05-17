/*
 * Zakaria Kortam - Professor Estrada - COMSC 76
 * 15 March, 2023
 * 
 * 1. Create a class called "Point" that has an x and y field of type: double.
 * Implement a Comparable interface
 * 2. Create an Overridden compareTo function
 * that compares the point's x value to another point's.
 * 3. Then, create a class called CompareY that implements Comparator<Point>.
 * 4. Within it, there is a function that compares the Y values.
 * 5. For both compareTo() and compare(), it returns either a positive/
 * negative integer or 0 as an indication of the inequality's value.
 * 6. In the main function, a random number generator makes 100 x and 100 y
 * values and assigns them into the Point constructor to form a new point.
 * 7. Following the creation, the sort method is called, which by default 
 * calls the compareTo method, thus sorting x first. The sorted x is printed.
 * 8. Then, sort is called again but specifies the use of the CompareY() operator
 * as opposed to the default compareTo. This causes it to compare Y instead of x.
 * 9. The results are printed.
 */

import java.util.*;

public class zakaria_kortamLSQ {
    public static void main(String[] args) {
        header("Zakaria Kortam - COMSC 76");
        Random randomNo = new Random();

        // Point Generation
        Point[] points = new Point[100];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(randomNo.nextDouble() * 100, randomNo.nextDouble() * 100);
        }

        // X Sorted
        Arrays.sort(points);
        header("Sorted | Via X Coordinate");
        for (Point pt : points) {
            System.out.println("(" + pt.x + ", " + pt.y + ")");
        }

        // Y Sorted
        Arrays.sort(points, new CompareY());
        header("Sorted | Via Y Coordinate");
        for (Point pt : points) {
            System.out.println("(" + pt.x + ", " + pt.y + ")");
        }
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
    public int compare(Point one, Point two) {
        if (one.y == two.y) {
            return Double.compare(one.x, two.x);
        } else {
            return Double.compare(one.y, two.y);
        }
    }
}