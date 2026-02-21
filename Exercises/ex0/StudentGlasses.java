package Exercises.ex0;

public class StudentGlasses {
// properties
    String id;
    String brand;
    String frameColor;
    String lensType;
    String prescription;
    double leftPower;
    double rightPower;
    String frameMaterial;
    String color;

    public StudentGlasses(String id, String brand, String frameColor,
                          String lensType, String prescription,
                          double leftPower, double rightPower,
                          String frameMaterial, String color) {

        this.id = id;
        this.brand = brand;
        this.frameColor = frameColor;
        this.lensType = lensType;
        this.prescription = prescription;
        this.leftPower = leftPower;
        this.rightPower = rightPower;
        this.frameMaterial = frameMaterial;
        this.color = color;


        System.out.println("Create StudentGlasses instance with id: " + id);
    }

    // Method 1
    public void wear() {
        System.out.println(id + " glasses are being worn.");
    }

    // Method 2
    public void prescription() {
        System.out.println(id + " glasses require prescription");
    }

    // Method 3
    public void describe() {
        System.out.println("Glasses " + id + ": " + brand +
                ", color=" + frameColor +
                ", lens=" + lensType);
    }
}