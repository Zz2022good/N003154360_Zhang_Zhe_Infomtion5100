package edu.info5100.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FourFunctionCalculatorApp extends Application {
    private final TextField display = new TextField("0");
    private double storedValue = 0;
    private String pendingOperation = "";
    private boolean startNewNumber = true;

    @Override
    public void start(Stage stage) {
        display.setEditable(false);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setFont(Font.font(24));
        display.setPrefHeight(60);

        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(10);
        buttonGrid.setVgap(10);
        buttonGrid.setPadding(new Insets(10));

        String[][] keys = {
            {"7", "8", "9", "/"},
            {"4", "5", "6", "*"},
            {"1", "2", "3", "-"},
            {"0", ".", "=", "+"},
            {"C"}
        };

        for (int row = 0; row < keys.length; row++) {
            for (int col = 0; col < keys[row].length; col++) {
                String key = keys[row][col];
                Button button = createButton(key);
                if ("C".equals(key)) {
                    buttonGrid.add(button, 0, row, 4, 1);
                } else {
                    buttonGrid.add(button, col, row);
                }
            }
        }

        BorderPane root = new BorderPane();
        root.setTop(display);
        BorderPane.setMargin(display, new Insets(10, 10, 0, 10));
        root.setCenter(buttonGrid);

        Scene scene = new Scene(root, 300, 360);
        stage.setTitle("Simple Calculator");
        stage.setScene(scene);
        stage.show();
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(60, 60);
        button.setFont(Font.font(18));
        button.setOnAction(event -> handleInput(text));
        return button;
    }

    private void handleInput(String input) {
        if (input.matches("[0-9]")) {
            appendDigit(input);
            return;
        }

        switch (input) {
            case ".":
                appendDecimalPoint();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                setOperation(input);
                break;
            case "=":
                calculateResult();
                break;
            case "C":
                clearCalculator();
                break;
            default:
                break;
        }
    }

    private void appendDigit(String digit) {
        if (startNewNumber || "Error".equals(display.getText())) {
            display.setText(digit);
            startNewNumber = false;
        } else {
            display.setText(display.getText() + digit);
        }
    }

    private void appendDecimalPoint() {
        if (startNewNumber || "Error".equals(display.getText())) {
            display.setText("0.");
            startNewNumber = false;
        } else if (!display.getText().contains(".")) {
            display.setText(display.getText() + ".");
        }
    }

    private void setOperation(String operation) {
        if (!pendingOperation.isEmpty() && !startNewNumber) {
            calculateResult();
        }

        if (!"Error".equals(display.getText())) {
            storedValue = Double.parseDouble(display.getText());
            pendingOperation = operation;
            startNewNumber = true;
        }
    }

    private void calculateResult() {
        if (pendingOperation.isEmpty() || "Error".equals(display.getText())) {
            return;
        }

        double currentValue = Double.parseDouble(display.getText());
        double result;

        switch (pendingOperation) {
            case "+":
                result = storedValue + currentValue;
                break;
            case "-":
                result = storedValue - currentValue;
                break;
            case "*":
                result = storedValue * currentValue;
                break;
            case "/":
                if (currentValue == 0) {
                    display.setText("Error");
                    pendingOperation = "";
                    startNewNumber = true;
                    return;
                }
                result = storedValue / currentValue;
                break;
            default:
                return;
        }

        display.setText(formatResult(result));
        storedValue = result;
        pendingOperation = "";
        startNewNumber = true;
    }

    private void clearCalculator() {
        display.setText("0");
        storedValue = 0;
        pendingOperation = "";
        startNewNumber = true;
    }

    private String formatResult(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
