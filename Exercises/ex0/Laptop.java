package Exercises.ex0;

public class Laptop {
    // properties
    String id;
    String brand;
    String model;
    int ram;
    int storage;
    double screenSize;
    String color;
    boolean macbook;
    String operatingSystem;

    // Constructor
    public Laptop(String id, String brand, String model, int ram,
                  int storage, double screenSize,
                  String color, boolean macbook, String operatingSystem) {

        this.id = id;
        this.brand = brand;
        this.model = model;
        this.ram = ram;
        this.storage = storage;
        this.screenSize = screenSize;
        this.color = color;
        this.macbook = macbook;
        this.operatingSystem = operatingSystem;


        System.out.println("Create Laptop instance with id: " + id);
    }

    // Method 1
    public void powerOn() {
        System.out.println(id + " laptop power is on.");
    }

    // Method 2
    public void powerOff() {
        System.out.println(id + " laptop power is off.");
    }

    // Method 3
    public void describe() {
        System.out.println("Laptop " + id + ": " + brand + " " + model +
                ", RAM=" + ram + "system " + operatingSystem);
    }
}