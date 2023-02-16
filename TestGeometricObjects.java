/* Zakaria Kortam - 2/6/2023 
 * Professor Estrada 
 * 
 * 1. User inputs the sides, colour, and filled property. 
 * Triangle class extends GeometricObject.
 * 2. It must check if the triangle is valid.
 * 3. A circular and rectangular object should also be made. 
 * 4. display the shapes.
 * 5. Extra: Check for the colours and catch the inputMismatch exception for the sides input.
 */

import java.util.*;

public class TestGeometricObjects {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double a = 0, b = 0, c = 0;
        String colour = "blank";
        boolean isFilled = false;

        System.out.print("Please enter the three sides of the triangle: ");
        try {
            a = in .nextDouble();
            b = in .nextDouble();
            c = in .nextDouble();
        } catch (InputMismatchException ex) {
            System.out.print("Sides invalid. You may only enter numbers");
        }
        if (isValid(a, b, c)) {
            System.out.print("Enter colour: ");
            colour = in .nextLine();
            colour = in .nextLine(); // Break nextLine glitch.
            if (!isValidColour(colour)) {
                while (!isValidColour(colour)) {
                    System.out.print("Invalid Colour. Try again: ");
                    colour = in .nextLine();
                }
            }
            System.out.print("Will the triangle be filled? (y/n) --> ");
            char response = in .next().charAt(0);
            if (response == 'y' || response == 'Y') {
                isFilled = true;
            }
        }
        Triangle t = new Triangle(a, b, c, colour, isFilled);
        Circle circ = new Circle(10, "Red", true);
        Rectangle rect = new Rectangle(5, 10, "Blue", false);

        System.out.println("\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(">>>>       Results      >>>> ");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
        System.out.println(circ.toString() + "\n");
        System.out.println(rect.toString() + "\n");
        if (isValid(a, b, c)) {
            System.out.println(t.toString());
        } else {
            System.out.println("Triangle is invalid. Data cannot be displayd.");
        }
    }

    public static boolean isValid(double a, double b, double c) {
        double greatestnumber = 0;
        boolean isTriangleValid = true;
        double ab = a + b;
        double ac = a + c;
        double bc = b + c;

        if (c >= ab || b >= ac) {
            isTriangleValid = false;
        }
        if (a >= bc) {
            isTriangleValid = false;
        }

        return isTriangleValid;
    }

    public static boolean isValidColour(String colour) {
        boolean isColourValid = false;
        if (
            colour.equals("Red") ||
            colour.equals("Orange") ||
            colour.equals("Yellow") ||
            colour.equals("Green") ||
            colour.equals("Blue") ||
            colour.equals("Purple") ||
            colour.equals("Violet") ||
            colour.equals("Indigo") ||
            colour.equals("Cyan") ||
            colour.equals("Pink") ||
            colour.equals("White") ||
            colour.equals("Black") ||
            colour.equals("Grey") ||
            colour.equals("Gray") ||
            colour.equals("red") ||
            colour.equals("orange") ||
            colour.equals("yellow") ||
            colour.equals("green") ||
            colour.equals("blue") ||
            colour.equals("purple") ||
            colour.equals("violet") ||
            colour.equals("indigo") ||
            colour.equals("cyan") ||
            colour.equals("pink") ||
            colour.equals("white") ||
            colour.equals("black") ||
            colour.equals("grey") ||
            colour.equals("gray")
        ) {
            return true;
        } else return false;

    }
}

abstract class GeometricObject {
    protected String colour = "blank";
    protected boolean filled;
    protected Date dateCreated;

    public GeometricObject() {
        dateCreated = new java.util.Date();
    }
    public GeometricObject(String colour, boolean filled) {
        dateCreated = new Date();
        this.colour = colour;
        this.filled = filled;
    }

    //Accessors
    public String getColour() {
        return colour;
    }
    public Date getDateCreated() {
        return dateCreated;
    }
    public boolean isFilled() {
        return filled;
    }

    //Mutators
    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }

    //Misc
    public String toString() {
        return String.format("Creation Date" + dateCreated + "%ncolour: %s%n Is Filled: %b", colour, filled);
    }
    public abstract double getArea();
    public abstract double getPerimeter();
}

class Triangle extends GeometricObject {
    private double a, b, c;

    public Triangle(double a, double b, double c, String colour, boolean filled) {
        super(colour, filled);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    //Accessors
    public String getColour() {
        return colour;
    }
    public Date getDateCreated() {
        return dateCreated;
    }
    public boolean isFilled() {
        return filled;
    }
    public double getA() {
        return a;
    }
    public double getB() {
        return b;
    }
    public double getC() {
        return c;
    }
    @Override
    public double getArea() {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
    @Override
    public double getPerimeter() {
        return a + b + c;
    }

    //Mutators
    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    public void setA(int a) {
        this.a = a;
    }
    public void setB(int b) {
        this.b = b;
    }
    public void setC(int c) {
        this.c = c;
    }

    //To String
    @Override
    public String toString() {
        return String.format("Triangle: %n Area: %.2f %n Perimeter: %.2f %n Colour: %s %n isFilled: %b", getArea(), getPerimeter(), getColour(), isFilled());
    }
}

class Circle extends GeometricObject {
    private double r; //radius

    public Circle(double r, String colour, boolean filled) {
        this.r = r;
        this.colour = colour;
        this.filled = filled;
    }
    public double getRadius() {
        return r;
    }
    public void setRadius(double r) {
        this.r = r;
    }

    @Override
    public double getArea() {
        return Math.pow(r, 2) * Math.PI;
    }
    public double getDiameter() {
        return 2 * r;
    }
    public double getPerimeter() {
        return 2 * Math.PI * r;
    }
    @Override
    public String toString() {
        return String.format("Circle: %n Area: %.2f %n Perimeter: %.2f %n Colour: %s %n Filled: " + isFilled(), getArea(), getPerimeter(), getColour(), isFilled());
    }
}

class Rectangle extends GeometricObject {
    private double w;
    private double l;

    public Rectangle(double w, double l, String colour, boolean filled) {
        this.w = w;
        this.l = l;
        this.colour = colour;
        this.filled = filled;
    }
    public double getW() {
        return w;
    }
    public void setW(double w) {
        this.w = w;
    }
    public double getL() {
        return l;
    }
    public void setL(double l) {
        this.l = l;
    }

    @Override
    public double getArea() {
        return l * w;
    }
    @Override
    public double getPerimeter() {
        return 2 * (l + w);
    }
    @Override
    public String toString() {
        return String.format("Rectangle: %n Area: %.2f %n Perimeter: %.2f %n Colour: %s %n isFilled: %b", getArea(), getPerimeter(), getColour(), isFilled());
    }
}

// End of Program
