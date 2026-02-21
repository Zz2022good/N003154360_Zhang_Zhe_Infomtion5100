package Exercises.ex0;

public class WaterBottle {

    String id;
    String brand;
    String color;
    double Liters;
    String material;
    boolean empty;
    boolean hasStraw;
    int temperature;
    double bottleheight;

    // Constructor
    public WaterBottle(String id, String brand, String color,
                       double Liters, String material,
                       boolean empty, boolean hasStraw,
                       int temperature, double bottleheight) {

        this.id = id;
        this.brand = brand;
        this.color = color;
        this.Liters = Liters;
        this.material = material;
        this.empty = empty;
        this.hasStraw = hasStraw;
        this.temperature = temperature;
        this.bottleheight = bottleheight;


        System.out.println("Create WaterBottle instance with id: " + id);
    }

    // Method 1
    public void drink() {
        System.out.println(id + " drinking from water bottle.");
    }

    // Method 2
    public void refill() {
        System.out.println(id + " refilling water bottle.");
    }

    // Method 3
    public void describe() {
        System.out.println("WaterBottle " + id + ": " + brand +
                ", material=" + material +
                ", capacity=" + Liters + "L");
    }
}