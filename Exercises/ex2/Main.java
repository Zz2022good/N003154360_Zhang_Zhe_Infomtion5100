package Exercises.ex2;

public class Main {
    public static void main(String[] args){
        Shape shape1 = new Triangle(2.5, 4, 2.5, 4, 5);
        Shape shape2 = new Rectangle(2, 3);
        Shape shape3 = new Circle(5);
        Shape shape4 = new Square(10);
        Shape shape5 = new Circle(10);

        shape1.ShapeType();
        System.out.println("Area: " + shape1.calculateArea());
        System.out.println("Perimeter: " + shape1.calculatePerimeter());
        System.out.println();

        shape2.ShapeType();
        System.out.println("Area: " + shape2.calculateArea());
        System.out.println("Perimeter: " + shape2.calculatePerimeter());
        System.out.println();

        shape3.ShapeType();
        System.out.println("Area: " + shape3.calculateArea());
        System.out.println("Perimeter: " + shape3.calculatePerimeter());
        System.out.println();

        shape4.ShapeType();
        System.out.println("Area: " + shape4.calculateArea());
        System.out.println("Perimeter: " + shape4.calculatePerimeter());
        System.out.println();

        shape5.ShapeType();
        System.out.println("Area: " + shape5.calculateArea());
        System.out.println("Perimeter: " + shape5.calculatePerimeter());
        System.out.println();




        System.out.println("color of the class: " + Shape.color);
    }
}




