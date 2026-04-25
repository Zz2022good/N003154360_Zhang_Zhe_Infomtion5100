# JavaFX Demo Code Walkthrough
## Student Presentation Version

> Course: INFO5100  
> Topic: JavaFX code walkthrough (3 demos)  
> Format: Each `##` section = one slide

---

## Slide 1 - Today we build real JavaFX apps

### Learning goals

- Understand JavaFX app skeleton (`Application -> start -> Scene -> Stage`)
- Read and explain event-driven code
- Reuse patterns to create your own app

### Demos today

1. `HelloJavaFXApp` (basic interaction)
2. `CalculatorApp` (state + event routing)
3. `StudentFormApp` (form + validation)

---

## Slide 2 - Common skeleton in all 3 demos

```java
public class XxxApp extends Application {
    @Override
    public void start(Stage stage) {
        // build UI
        stage.setScene(new Scene(root, w, h));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

### Key message

- `main` starts JavaFX runtime
- `start` is where UI is created
- Same pattern works for almost all beginner apps

---

## Slide 3 - Demo A: `HelloJavaFXApp` overview

### Goal

- Take user input (`TextField`)
- Click button
- Show feedback (`Label`)

### Why first

- Smallest complete JavaFX app
- Easy to see event flow from click to UI update

---

## Slide 4 - `HelloJavaFXApp`: UI creation

### Main controls

- `Label prompt`
- `TextField nameField`
- `Button helloButton`
- `Label greeting`

### Layout

- `VBox` stacks controls top to bottom
- `setAlignment(Pos.CENTER)` centers content
- `setPadding(new Insets(...))` adds spacing

---

## Slide 5 - `HelloJavaFXApp`: event logic

```java
helloButton.setOnAction(e -> {
    String name = nameField.getText().trim();
    greeting.setText(name.isEmpty() ? "Please enter a name." : "Hello, " + name + "!");
});
```

### Explain to students

- Button click triggers callback
- Read input -> validate -> update output label
- This is event-driven programming

### Ask students

- What happens if input is empty?
- Why use `trim()`?

---

## Slide 6 - Demo A recap

- `Stage`: window
- `Scene`: what window shows
- `Node`: every UI element
- Event handler connects user action to behavior

Transition: next demo adds **state** across multiple clicks.

---

## Slide 7 - Demo B: `CalculatorApp` overview

### Goal

- Build a functional calculator UI
- Handle numbers, operators, equals, clear

### New concepts

- App-level state variables
- Routing all button input through one method
- Guard conditions (divide-by-zero)

---

## Slide 8 - `CalculatorApp`: state variables

```java
private final TextField display = new TextField("0");
private double storedValue = 0;
private String pendingOperation = "";
private boolean startNewNumber = true;
```

### Why this matters

- Calculator needs memory between clicks
- Local variables inside methods are not enough

---

## Slide 9 - `CalculatorApp`: building keypad from data

```java
String[][] keys = {
    {"7", "8", "9", "/"},
    {"4", "5", "6", "*"},
    {"1", "2", "3", "-"},
    {"0", ".", "=", "+"},
    {"C"}
};
```

### Teaching point

- Data-driven UI generation avoids repeated code
- Students can easily add new buttons later

---

## Slide 10 - `CalculatorApp`: event routing center

```java
private void handleInput(String input) {
    if (input.matches("[0-9]")) {
        appendDigit(input);
        return;
    }
    switch (input) { ... }
}
```

### Why design it this way

- Every button uses same entry point
- Cleaner than putting full logic in each button callback
- Easier to debug and extend

---

## Slide 11 - `CalculatorApp`: operation workflow

### Flow

1. User enters number
2. User selects operator -> store state
3. User enters second number
4. User clicks `=` -> calculate
5. Show formatted result

### Edge case

- If dividing by zero, display `"Error"` and reset operation state

---

## Slide 12 - `CalculatorApp` concepts demonstrated

- Event-driven programming
- State management
- Layout containers (`BorderPane`, `GridPane`)
- Input validation
- Separation of UI and logic

Transition: next demo applies same ideas to a real form.

---

## Slide 13 - Demo C: `StudentFormApp` overview

### Goal

- Build a registration-like form
- Validate required inputs before submit

### Controls used

- `TextField` (name, student ID)
- `ComboBox<String>` (course)
- `Label` (status)
- `Button` (submit, clear)

---

## Slide 14 - `StudentFormApp`: validation first

```java
if (name.isEmpty() || studentId.isEmpty() || course == null) {
    resultLabel.setText("Error: Name, Student ID, and Course are required.");
    return;
}
```

### Key teaching point

- Validate before processing
- Use early return for clear control flow

---

## Slide 15 - `StudentFormApp`: clear/reset behavior

### Clear button responsibilities

- Clear all text fields
- Clear combo box selection
- Restore default hint message

### Why important

- Good UX
- Teaches "reset state to initial value" pattern

---

## Slide 16 - Layout comparison across demos

| Demo | Layout | Best use |
|---|---|---|
| `HelloJavaFXApp` | `VBox` | quick vertical UI |
| `CalculatorApp` | `BorderPane` + `GridPane` | app shell + keypad |
| `StudentFormApp` | `GridPane` | form alignment |

---

## Slide 17 - Run configs students should create

### VM options (reuse for all JavaFX apps)

macOS:

```text
--module-path ~/javafx/javafx-sdk-22.0.2/lib --add-modules javafx.controls
```

Windows:

```text
--module-path C:\javafx\javafx-sdk-22.0.2\lib --add-modules javafx.controls
```

### Main classes

- `edu.info5100.javafx.HelloJavaFXApp`
- `edu.info5100.javafx.CalculatorApp`
- `edu.info5100.javafx.StudentFormApp`

Only main class changes.

---

## Slide 18 - In-class live coding plan

### Suggested timing (60 min)

- 10 min: Hello app walkthrough + run
- 25 min: Calculator architecture + key methods
- 15 min: Student form + validation
- 10 min: Students modify one demo and run

---

## Slide 19 - Student mini tasks

1. `HelloJavaFXApp`: add a `Clear` button
2. `CalculatorApp`: add `%` operator or backspace
3. `StudentFormApp`: validate student ID format (regex)

Stretch:

- Move result text style to CSS

---

## Slide 20 - End-of-class checklist

- I can explain `start(Stage)` purpose
- I can add an event handler using lambda
- I can manage app state across clicks
- I can validate user input before submit
- I can run JavaFX app in IntelliJ with correct VM options

---

## Slide 21 - Q&A and next step

Next class preview:

- `TableView`
- Scene switching
- Basic MVC refactor practice
