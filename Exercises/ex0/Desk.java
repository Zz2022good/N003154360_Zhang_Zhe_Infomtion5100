package Exercises.ex0;

public class Desk {

    String id;
    String brand;
    String color;
    String material;
    double width;
    double length;
    double height;
    boolean hasDrawers;
    boolean wooden;

    // Constructor
    public Desk(String id, String brand, String color, String material,
                double width, double length, double height,
                boolean hasDrawers, boolean wooden) {

        this.id = id;
        this.brand = brand;
        this.color = color;
        this.material = material;
        this.width = width;
        this.length = length;
        this.height = height;
        this.hasDrawers = hasDrawers;
        this.wooden = wooden;


        System.out.println("Create Desk instance with id: " + id);
    }

    // Method 1
    public void made_of_wood() {
        System.out.println(id + " Desks are made of wood");
    }

    // Method 2
    public void clean() {
        System.out.println(id + " Desks are clean.");
    }

    // Method 3
    public void describe() {
        System.out.println("Desk " + id + ": " + brand +
                ", material=" + material +
                ", length=" +length);
    }
}