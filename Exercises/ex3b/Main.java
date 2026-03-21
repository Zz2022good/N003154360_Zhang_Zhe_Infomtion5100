package Exercises.ex3b;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Shape s1 = new Triangle(2.5, 4, 2.5, 4, 5);
        Shape s2 = new Rectangle(2, 3);
        Shape s3 = new Circle(5);
        Shape s4 = new Square(10);



       // serialization
        try {
            FileOutputStream fileOut = new FileOutputStream("shapes.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(s1);
            out.writeObject(s2);
            out.writeObject(s3);
            out.writeObject(s4);
            // serialization, the serialized data is saved in shape.ser.
            out.close();
            fileOut.close();

            System.out.println("Serialized data is saved in shapes.ser\n");

        } catch (IOException i) {
            i.printStackTrace();
        }

        // deserialization
        Shape d1 = null;
        Shape d2 = null;
        Shape d3 = null;
        Shape d4 = null;

        try {
            FileInputStream fileIn = new FileInputStream("shapes.ser"); // opens this file
            ObjectInputStream in = new ObjectInputStream(fileIn);

            d1 = (Shape) in.readObject();
            d2 = (Shape) in.readObject();
            d3 = (Shape) in.readObject();
            d4 = (Shape) in.readObject();  // reads objects

            in.close();
            fileIn.close();

        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Shape class not found");
            c.printStackTrace();
            return;
        }


        // Printing outputs

        System.out.println("Deserialized objects...\n");

        d1.ShapeType();
        System.out.println("Area: " + d1.calculateArea());
        System.out.println("Perimeter: " + d1.calculatePerimeter());
        System.out.println();

        d2.ShapeType();
        System.out.println("Area: " + d2.calculateArea());
        System.out.println("Perimeter: " + d2.calculatePerimeter());
        System.out.println();

        d3.ShapeType();
        System.out.println("Area: " + d3.calculateArea());
        System.out.println("Perimeter: " + d3.calculatePerimeter());
        System.out.println();

        d4.ShapeType();
        System.out.println("Area: " + d4.calculateArea());
        System.out.println("Perimeter: " + d4.calculatePerimeter());
        System.out.println();

        // static field check
        System.out.println("Color: " + Shape.color);
    }
}
