package edu.info5100.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloJavaFXApp extends Application {

    @Override
    public void start(Stage stage) {
        Label prompt = new Label("Enter your name:");
        TextField nameField = new TextField();
        nameField.setPromptText("Ada Lovelace");
        nameField.setMaxWidth(280);

        Label greeting = new Label("");
        greeting.setStyle("-fx-font-size: 16px;");

        Button helloButton = new Button("Say hello");
        helloButton.setDefaultButton(true);
        helloButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            greeting.setText(name.isEmpty() ? "Please enter a name." : "Hello, " + name + "!");
        });

        VBox root = new VBox(12, prompt, nameField, helloButton, greeting);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(24));

        Scene scene = new Scene(root, 360, 220);
        stage.setTitle("JavaFX Demo - Hello");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
