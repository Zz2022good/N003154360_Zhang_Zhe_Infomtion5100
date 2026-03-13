package Exercises.ex2;

public abstract class Shape { // abstract class
    abstract double calculateArea();
    abstract double calculatePerimeter();  // abstract methods,the subclasses circle,triangle,rectangle,square will implement them.

    static String color = "red";
    void ShapeType(){
        return;
    }
}

class Triangle extends Shape {
    double base;
    double height;
    double side_01;
    double side_02;
    double side_03;

    Triangle(double base, double height, double side_01, double side_02, double side_03){
        this.base = base;
        this.height = height;
        this.side_01 = side_01;
        this.side_02 = side_02;
        this.side_03 = side_03;
    }

    double calculateArea(){
        return base*height*0.5;  // overriding
    }

    double calculatePerimeter(){
        return side_01 + side_02 + side_03;  // overriding
    }

    void ShapeType() {
        System.out.println("Triangle");
    }
}

class Rectangle extends Shape {
    double length;
    double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }


    double calculateArea() {
        return length * width;
    }


    double calculatePerimeter() {
        return 2 * (length + width);
    }


    void ShapeType() {
        System.out.println("Rectangle");
    }
}


class Circle extends Shape {
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }


    double calculateArea() {
        return Math.PI * radius * radius;
    }


    double calculatePerimeter() {
        return 2 *radius *  Math.PI;  // pi*d = pi * 2r
    }


    void ShapeType() {
        System.out.println("Circle");
    }
}

class Square extends Shape {
    double side; // all four sides are the same

    Square(double side) {
        this.side = side;
    }


    double calculateArea() {
        return side * side;
    }


    double calculatePerimeter() {
        return 4 * side;
    }


    void ShapeType() {
        System.out.println("Square");
    }
}


