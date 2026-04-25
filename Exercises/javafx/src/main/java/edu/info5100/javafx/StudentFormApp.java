package edu.info5100.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StudentFormApp extends Application {

    @Override
    public void start(Stage stage) {
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter full name");

        Label idLabel = new Label("Student ID:");
        TextField idField = new TextField();
        idField.setPromptText("e.g. N12345678");

        Label courseLabel = new Label("Course:");
        ComboBox<String> courseBox = new ComboBox<>();
        courseBox.getItems().addAll("INFO5100", "INFO6150", "INFO6205");
        courseBox.setPromptText("Select a course");

        Label resultLabel = new Label("Please fill the form and click Submit.");

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            String studentId = idField.getText().trim();
            String course = courseBox.getValue();

            if (name.isEmpty() || studentId.isEmpty() || course == null) {
                resultLabel.setText("Error: Name, Student ID, and Course are required.");
                return;
            }

            resultLabel.setText("Registered: " + name + " (" + studentId + ") - " + course);
        });

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> {
            nameField.clear();
            idField.clear();
            courseBox.getSelectionModel().clearSelection();
            resultLabel.setText("Please fill the form and click Submit.");
        });

        GridPane root = new GridPane();
        root.setPadding(new Insets(20));
        root.setHgap(10);
        root.setVgap(12);

        root.add(nameLabel, 0, 0);
        root.add(nameField, 1, 0);
        root.add(idLabel, 0, 1);
        root.add(idField, 1, 1);
        root.add(courseLabel, 0, 2);
        root.add(courseBox, 1, 2);
        root.add(submitButton, 0, 3);
        root.add(clearButton, 1, 3);
        root.add(resultLabel, 0, 4, 2, 1);

        stage.setTitle("Student Form Demo");
        stage.setScene(new Scene(root, 460, 260));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
