package Exercises.ex1;
import java.util.Arrays;

class Student {
    String name;
    int[] quizzes; // 15 quiz scores
    // constructor
    public Student(String name) {
        this.name = name;
        this.quizzes = new int[15];
        System.out.println("Create Student: " + name);
    }

    public double averageQuizScore() {
        int sum = 0;
        for (int score : quizzes) {
            sum += score;
        }
        return sum / 15.0;
    }  // this is a calculation of average on 15 quizzes.


}

class PartTimeStudent extends Student {
    public PartTimeStudent(String name) {
        super(name);
        System.out.println(" PartTimeStudent: " + name);
    }
}  // part time students

class FullTimeStudent extends Student {
    double exam1;
    double exam2;

    public FullTimeStudent(String name, double exam1, double exam2) {
        super(name);
        this.exam1 = exam1;
        this.exam2 = exam2;
        System.out.println(" FullTimeStudent: " + name);   // full time students with extra 2 exams
    }
}

class Session {
    Student[] students; //20 students

    public Session() {
        students = new Student[20];
    }  // 20 students in total

    public void printAverageQuizScoresPerStudent() {
        System.out.println(" Average Quiz Scores ");
        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i].name + ": " + students[i].averageQuizScore());

        }
    }   // method that calculates the average quiz score per student.




    public void printQuizScoresAscending() {
        System.out.println(" Quiz Scores Ascending ");
        for (Student student : students) {
                Arrays.sort(student.quizzes);
                System.out.println(student.name + ": " + Arrays.toString(student.quizzes));

        }  // method that make the score ascending rank.
    }

    public void printPartTimeStudentNames() {
        System.out.println(" Part-Time Student Names ");
        for (int i = 0; i < students.length; i++) {
            if (students[i] instanceof PartTimeStudent) {
                System.out.println(students[i].name);
            }  // method that prints names of the part-time students.
        }
    }

    public void printFullTimeExamScores() {
        System.out.println(" Full-Time Exam Scores ");
        for (int i = 0; i < students.length; i++) {
            if (students[i] instanceof FullTimeStudent) {
                FullTimeStudent f = (FullTimeStudent) students[i];
                System.out.println(f.name + " -> Exam1: " + f.exam1 + ", Exam2: " + f.exam2);
            }  // method that prints full-time student exam scores.
        }
    }
}


