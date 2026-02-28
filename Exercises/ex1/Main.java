package Exercises.ex1;

public class Main {
    public static void main(String[] args) {

        Session session = new Session();

        // Part-time students
        for (int i = 0; i < 10; i++) {
            PartTimeStudent p = new PartTimeStudent("part_time_random_student_0-9" + i);

            //quiz scores for fifteen quizzes
            for (int j = 0; j < 15; j++) {
                p.quizzes[j] = 81 + j;
            }

            session.students[i] = p;
        }

        // Full-time students
        for (int i = 10; i < 20; i++) {
            FullTimeStudent f =
                    new FullTimeStudent("Full_time_student_10-20" + i, 88.5, 93.2);

            // quiz score
            for (int j = 0; j < 15; j++) {
                f.quizzes[j] = 84 + j;
            }

            session.students[i] = f;
        }

        // calling all the method
        session.printAverageQuizScoresPerStudent();
        session.printQuizScoresAscending();
        session.printPartTimeStudentNames();
        session.printFullTimeExamScores();
    }
}

