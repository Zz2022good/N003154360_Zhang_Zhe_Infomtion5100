package Exercises.ex0;

public class Chair {

    String id;
    String brand;
    String color;
    String material;
    boolean hasWheels;
    String Height;
    int maxWeight;
    double Inches;
    String manufacturer_date;


    public Chair(String id, String brand, String color, String material,
                 boolean hasWheels, String Height,
                 int maxWeight, double Inches, String manufacturer_date) {

        this.id = id;
        this.brand = brand;
        this.color = color;
        this.material = material;
        this.hasWheels = hasWheels;
        this.Height = Height;
        this.maxWeight = maxWeight;
        this.Inches = Inches;
        this.manufacturer_date = manufacturer_date;


        System.out.println("Created Chair instance with id: " + id);
    }

    // Method 1
    public void occupied() {
        System.out.println(id + " chair is not being sat on.");
    }

    // Method 2
    public void move() {
        System.out.println(id + " chair is being moved.");
    }

    // Method 3
    public void describe() {
        System.out.println("Chair " + id + ": " + brand +
                ", material=" + material +
                ", color=" + color);
    }
}