package Exercises.ex0;

public class Marker {

    String id;
    String brand;
    String color;
    double length;
    String shape;
    double tipSize;
    boolean refillable;
    String new_marker;
    String being_used;

    // Constructor
    public Marker(String id, String brand, String color, double length, String shape,
                  double tipSize, boolean refillable, String new_marker,
                  String being_used) {

        this.id = id;
        this.brand = brand;
        this.color = color;
        this.length = length;
        this.shape = shape;
        this.tipSize = tipSize;
        this.refillable = refillable;
        this.new_marker = new_marker;
        this.being_used= being_used;


        System.out.println("Create Marker instance with id: " + id);
    }

    // Method 1
    public void write() {
        System.out.println(id + " Professor is using this marker to write.");
    }

    // Method 2
    public void marker_color() {
        System.out.println(id + " What's the color of the marker?");
    }

    // Method 3
    public void describe() {
        System.out.println("Marker " + id + ": " + brand +
                ", color=" + color +
                ", length=" + length);
    }
}