package Exercises.ex0;

public class Quiz {

    String id;
    String courseName;
    String quizNumber;
    int Questions;
    int Points;
    String date;
    int time;
    String instructorName;
    String topic;

    // Constructor
    public Quiz(String id, String courseName, String quizNumber,
                int Questions, int Points, String date,
                int time, String instructorName, String topic) {

        this.id = id;
        this.courseName = courseName;
        this.quizNumber = quizNumber;
        this.Questions = Questions;
        this.Points = Points;
        this.date = date;
        this.time = time;
        this.instructorName = instructorName;
        this.topic = topic;

        System.out.println("Create Quiz instance with id: " + id);
    }

    // Method 1
    public void startQuiz() {
        System.out.println(id + " quiz started.");
    }

    // Method 2
    public void submit() {
        System.out.println(id + " quiz is submitted.");
    }

    // Method 3
    public void describe() {
        System.out.println("Quiz " + id + ": " + quizNumber +
                " for " + courseName +
                ", number of questions=" + Questions + ",topic is" + topic);
    }
}