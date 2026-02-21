package Exercises.ex0;

public class Whiteboard {

    String id;
    String brand;
    String color;
    double width;
    double height;
    boolean written;
    String surfaceType;
    boolean wallMounted;
    double length;

    // Constructor
    public Whiteboard(String id, String brand, String color,
                      double width, double height,
                      boolean written, String surfaceType,
                      boolean wallMounted, double length) {

        this.id = id;
        this.brand = brand;
        this.color = color;
        this.width = width;
        this.height = height;
        this.written = written;
        this.surfaceType = surfaceType;
        this.wallMounted = wallMounted;
        this.length = length;



        System.out.println("Create Whiteboard instance with id: " + id);
    }

    // Method 1
    public void write() {
        System.out.println(id + " professor Oza is writing on the whiteboard.");
    }

    // Method 2
    public void erase() {
        System.out.println(id + "professor Oza is erasing the whiteboard.");
    }

    // Method 3
    public void describe() {
        System.out.println("Whiteboard " + id + ": " + brand +
                ", size=" + width + "x" + height +
                " being written =" + written);
    }
}