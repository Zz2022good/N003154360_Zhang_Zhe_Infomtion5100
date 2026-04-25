# JavaFX Setup and Teaching Slides
## Student Presentation Version

> Course: INFO5100  
> Topic: JavaFX project setup in IntelliJ  
> Format: Each `##` section = one slide

---

## Slide 1 - What we are setting up today

- A clean JavaFX project structure in IntelliJ
- Reusable run configuration for all JavaFX apps
- A workflow that avoids common setup errors

Project structure used in class:

```text
javafx/
  README.md
  DEMO_CODE_WALKTHROUGH.md
  src/
    main/
      java/
        edu/
          info5100/
            javafx/
              HelloJavaFXApp.java
              CalculatorApp.java
              StudentFormApp.java
```

---

## Slide 2 - Core idea students must remember

- JavaFX runtime is configured through IntelliJ settings.
- You only set path/options once, then reuse for every app.
- Most student errors come from wrong folder/package/run config, not from code logic.

---

## Slide 3 - Import project in IntelliJ

1. Open IntelliJ
2. `File -> Open`
3. Select the `javafx` folder
4. Wait for indexing to finish

---

## Slide 4 - Project and module settings

1. `File -> Project Structure -> Project`
2. Set a valid JDK as `Project SDK`
3. `Project Structure -> Modules`
4. Confirm `src/main/java` is marked as Sources Root (blue folder)

---

## Slide 4.5 - What students MUST NOT change

Students should NOT:

- ❌ move `.java` files outside `src/main/java`
- ❌ rename package `edu.info5100.javafx`
- ❌ create Java files directly under project root
- ❌ randomly switch module SDK during class

All new app files should be created under:
`src/main/java/edu/info5100/javafx/`

---

## Slide 5 - Add JavaFX SDK library

1. Download JavaFX SDK from [https://openjfx.io/](https://openjfx.io/)
2. Unzip it to a local folder
3. IntelliJ: `File -> Project Structure -> Libraries`
4. Click `+` -> `Java`
5. Select the JavaFX SDK `lib` folder
6. Apply library to current module

---

## Slide 6 - Run configuration template (reuse)

Create one Application config per app:

1. `Run -> Edit Configurations -> + -> Application`
2. Set Main class
3. Use classpath of module: choose current module
4. Add VM options

macOS example:

```text
--module-path ~/javafx/javafx-sdk/lib --add-modules javafx.controls
```

Windows example:

```text
--module-path C:\javafx\javafx-sdk\lib --add-modules javafx.controls
```

Students only need to adjust local path once.  
Same VM options can be reused for all JavaFX apps.

---

## Slide 7 - Ready-to-run main classes

- `edu.info5100.javafx.HelloJavaFXApp`
- `edu.info5100.javafx.CalculatorApp`
- `edu.info5100.javafx.StudentFormApp`

Tip:
- Duplicate an existing run configuration
- Change Main class only

---

## Slide 8 - Create your own JavaFX app

Steps:

1. Right click package `edu.info5100.javafx`
2. `New -> Java Class`
3. Name: `MyFirstApp`
4. Paste template:

```java
package edu.info5100.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MyFirstApp extends Application {
    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(new Label("Hello INFO5100"), 300, 200));
        stage.setTitle("My First JavaFX App");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
```

---

## Slide 9 - If run fails, check in this order

1. Is Main class correct?
2. Is classpath module correct?
3. Is `src/main/java` blue (Sources Root)?
4. Does package line match folder path?
5. Is JavaFX `lib` added as Library?
6. Are VM options set (`--module-path ... --add-modules javafx.controls`)?

---

## Slide 10 - Common errors and quick fixes

- `ClassNotFoundException` / main class cannot load
  - Main class or classpath module is wrong.
- `package javafx... does not exist`
  - JavaFX library not attached to module.
- `JavaFX runtime components are missing`
  - VM options missing or wrong module path.
- Main class cannot be selected in IntelliJ
  - `src/main/java` is not marked as Sources Root.

---

## Slide 11 - Teaching flow suggestion

1. Run `HelloJavaFXApp` first (lifecycle + controls)
2. Run `CalculatorApp` second (events + state)
3. Run `StudentFormApp` third (form + validation)
4. Students create one new app with copied run config

---

## Slide 12 - End-of-class checklist

- I can explain where Java files should be created
- I can set JavaFX library in IntelliJ
- I can configure VM options once and reuse them
- I can run all three demo apps successfully
- I can create a new JavaFX class in the correct package
