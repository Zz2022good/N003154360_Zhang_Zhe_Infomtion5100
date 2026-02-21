package Exercises.ex0;

public class MyClass {

    // properties.
    String studentName;
    String id;
    int studentNumber;
    int age;
    String professorName;
    String taName;
    String ipad;
    int year;
    int date;

    // Constructor
    public MyClass(String id, String studentName, int studentNumber, int age,
                   String professorName, String taName, String ipad,
                   int year, int date) {

        this.id = id;
        this.studentName = studentName;
        this.studentNumber = studentNumber;
        this.age = age;
        this.professorName = professorName;
        this.taName = taName;
        this.ipad = ipad;
        this.year = year;
        this.date = date;

        System.out.println("Create MyClass instance: " + id);
    }

    // Method 1
    public void student() {

        System.out.println("Function for student " + studentName);
    }

    // Method 2
    public void course() {

        System.out.println("Info 5100");
    }

    // Method 3
    public void describe() {

        System.out.println("The student name is "+ studentName + " with id" + studentNumber +" with professor" + professorName);
    }

    class Schedule {
        String courseName;
        String day;
        String time;

        public Schedule(String courseName, String day, String time) {
            this.courseName = courseName;
            this.day = day;
            this.time = time;

            System.out.println("Schedule for " + id);
        }

        public void showSchedule() {

            System.out.println("Class on " + day + " at " + time);
        }
    }


    class Grade {
        String checkmark;
        int score;

        public Grade(String checkmark, int score) {
            this.checkmark = checkmark;
            this.score = score;

            System.out.println("Create Grade for " + id);
        }

        public void showGrade() {

            System.out.println("Grade: " + checkmark + " (" + score + ")");
        }
    }
}

