package Exercises.ex0;
public class Main {
    public static void main(String[] args) {

        //Class 1: MyClass
        MyClass mc1 = new MyClass("class1", "Zhe", 154, 24, "Prof Oza", "Jackie", "using ipad", 2026, 221);
        mc1.describe();
        MyClass mc2 = new MyClass("class2", "Jack", 155, 23, "Prof Oza", "Jackie", "not using ipad", 2026, 221);
        mc2.describe();
        MyClass mc3 = new MyClass("class3", "Joan", 156, 22, "Prof oza", "Jackie", "using ipad", 2026, 221);
        mc3.describe();

        //Class 2: Phone
        Phone p1 = new Phone("P1", "Apple", "Iphone 14", "Black", 128, 100, true, true);
        p1.describe();
        Phone p2 = new Phone("P2", "Samsung", "Galaxy S23", "Silver", 256, 10, false, false);
        p2.describe();
        Phone p3 = new Phone("P3", "Apple", "Iphone 17", "Orange", 256, 50, false, true);
        p3.describe();

        //Class 3: Laptop
        Laptop l1 = new Laptop("L1", "Apple", "MacBook Pro", 16, 512, 14.0, "Gray", true, "macOS");
        l1.describe();
        Laptop l2 = new Laptop("L2", "Dell", "Alienware", 32, 1000, 17.0, "White", false, "Windows");
        l2.describe();
        Laptop l3 = new Laptop("L3", "Lenovo", "Legion", 32, 512, 16.0, "Black", false, "Windows");
        l3.describe();

        //Class 4: StudentGlasses
        StudentGlasses g1 = new StudentGlasses("G1", "RayBan", "Black", "Clear", "Yes",
                -2.25, -2.00, "Plastic", "Transparent");
        g1.describe();
        StudentGlasses g2 = new StudentGlasses("G2", "RayBan", "Silver", "Blue Light", "Yes",
                -2.00, -1.75, "Metal", "Transparent");
        g2.describe();
        StudentGlasses g3 = new StudentGlasses("G3", "Gucci", "Black", "Blue light", "No",
                0.0, 0.0, "Plastic", "Transparent");
        g3.describe();

        //Class 5: WaterBottle
        WaterBottle w1 = new WaterBottle("WB1", "Kirkland Costco", "Water have no color.", 0.5,
                "plastic", false, false, 15, 25.0);
        w1.describe();
        WaterBottle w2 = new WaterBottle("WB2", "Dasani", "Water have no color", 0.5,
                "Plastic", true, false, 16, 25.0);
        w2.describe();
        WaterBottle w3 = new WaterBottle("WB3", "Stanley", "Silver", 2.0,
                "Steel", false, true, 50, 40.0);
        w3.describe();

        //Class 6: Chair
        Chair c1 = new Chair("C1", "Staples", "Black", "Plastic", true,
                "Medium", 275, 18.0, "2024");
        c1.describe();
        Chair c2 = new Chair("C2", "IKEA", "Wooden", "wood", false,
                "Low", 220, 17.5, "2020");
        c2.describe();
        Chair c3 = new Chair("C3", "Herman Miller", "Gray", "Plastic", true,
                "High", 300, 19.0, "2026");
        c3.describe();

        //Class 7: Whiteboard
        Whiteboard wb1 = new Whiteboard("W1", "Unknown Brand", "White",
                120.0, 90.0, true, "Dry-erase", true, 50.0);
        wb1.describe();
        Whiteboard wb2 = new Whiteboard("W2", "Unknown Brands", "White",
                90.0, 60.0, false, "Dry-erase", false, 40);
        wb2.describe();
        Whiteboard wb3 = new Whiteboard("W3", "Amazon", "White",
                150.0, 100.0, true, "Unknown Surface Type", true, 60);
        wb3.describe();

        //Class 8: Desk
        Desk d1 = new Desk("D1", "IKEA", "Wooden", "Wood",
                120.0, 60.0, 75.0, true, true);
        d1.describe();
        Desk d2 = new Desk("D2", "Unknow Brand", "Black", "Metal",
                140.0, 70.0, 72.0, false, false);
        d2.describe();
        Desk d3 = new Desk("D3", "Amazon", "Brown", "Wood",
                110.0, 55.0, 74.0, false, true);
        d3.describe();

        //Class 9: Quiz
        Quiz q1 = new Quiz("Q1", "INFO5100", "Quiz 1",
                10, 100, "2026", 20, "Prof Oza", "Java");
        q1.describe();
        Quiz q2 = new Quiz("Q2", "INFO5100", "Quiz 2",
                10, 80, "2026", 20, "Prof Oza", "Java");
        q2.describe();
        Quiz q3 = new Quiz("Q3", "INFO5100", "Quiz 3",
                12, 120, "2026", 20, "Prof Oza", "Java");
        q3.describe();

        //Class 10: Marker
        Marker m1 = new Marker("M1", "Unknown Brand", "Black", 14.0,
                "Cylinder", 1.5, false, "Yes", "Yes");
        m1.describe();
        Marker m2 = new Marker("M2", "Sharpie", "Blue", 13.0,
                "Cylinder", 0.8, false, "No", "No");
        m2.describe();
        Marker m3 = new Marker("M3", "Sharpie", "Red", 15.0,
                "Cylinder", 2.0, true, "Yes", "No");
        m3.describe();
    }
}