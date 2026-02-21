package Exercises.ex0;

public class Phone {
    // properties
    String id;
    String brand;
    String model;
    String color;
    int storage;
    int batteryPercent;
    boolean ios;
    boolean hasCase;

    public Phone(String id, String brand, String model, String color,
                 int storage, int batteryPercent, boolean ios, boolean hasCase) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.storage = storage;
        this.batteryPercent = batteryPercent;
        this.ios = ios;
        this.hasCase = hasCase;

        System.out.println("Create Phone: " + id);
    }

    public void powerOn() {
        System.out.println(id + " power on."); }
    public void powerOff() {
        System.out.println(id + " power off."); }
    public void describe() {
        System.out.println("Phone " + id + " (" + brand + " " + model + ")" );
    }
}
