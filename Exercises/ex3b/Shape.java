package Exercises.ex3b;

import java.io.Serializable;


public abstract class Shape implements Serializable {
    private static final long serialVersionUID = 1L;

    // Abstract methods and static color
    abstract double calculateArea();
    abstract double calculatePerimeter();
    static String color = "red";

    void ShapeType() {
        return;
    }
}


class Triangle extends Shape {
    private static final long serialVersionUID = 1L;

    double base;
    double height;
    double side_01;
    double side_02;
    double side_03;

    Triangle(double base, double height, double side_01, double side_02, double side_03) {
        this.base = base;
        this.height = height;
        this.side_01 = side_01;
        this.side_02 = side_02;
        this.side_03 = side_03;
    }

    double calculateArea() {
        return base * height * 0.5;  // overriding
    }

    double calculatePerimeter() {
        return side_01 + side_02 + side_03;  // overriding
    }

    void ShapeType() {
        System.out.println("Triangle");
    }
}


class Rectangle extends Shape {
    private static final long serialVersionUID = 1L;

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
    private static final long serialVersionUID = 1L;

    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    double calculateArea() {
        return Math.PI * radius * radius;
    }

    double calculatePerimeter() {
        return 2 * radius * Math.PI;  // pi*d = pi * 2r
    }

    void ShapeType() {
        System.out.println("Circle");
    }
}


class Square extends Shape {
    private static final long serialVersionUID = 1L;

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
