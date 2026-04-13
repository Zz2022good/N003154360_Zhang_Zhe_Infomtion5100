package edu.info5100.javafx;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecipeManagerApp extends Application {

    private final ObservableList<Recipe> recipes = FXCollections.observableArrayList();
    private final ObservableList<String> groceryItems = FXCollections.observableArrayList();

    private final ComboBox<Recipe> monBreakfast = new ComboBox<>();
    private final ComboBox<Recipe> monLunch = new ComboBox<>();
    private final ComboBox<Recipe> monDinner = new ComboBox<>();

    private final ComboBox<Recipe> tueBreakfast = new ComboBox<>();
    private final ComboBox<Recipe> tueLunch = new ComboBox<>();
    private final ComboBox<Recipe> tueDinner = new ComboBox<>();

    private final ComboBox<Recipe> wedBreakfast = new ComboBox<>();
    private final ComboBox<Recipe> wedLunch = new ComboBox<>();
    private final ComboBox<Recipe> wedDinner = new ComboBox<>();

    private final ComboBox<Recipe> thuBreakfast = new ComboBox<>();
    private final ComboBox<Recipe> thuLunch = new ComboBox<>();
    private final ComboBox<Recipe> thuDinner = new ComboBox<>();

    private final ComboBox<Recipe> friBreakfast = new ComboBox<>();
    private final ComboBox<Recipe> friLunch = new ComboBox<>();
    private final ComboBox<Recipe> friDinner = new ComboBox<>();

    private final ComboBox<Recipe> satBreakfast = new ComboBox<>();
    private final ComboBox<Recipe> satLunch = new ComboBox<>();
    private final ComboBox<Recipe> satDinner = new ComboBox<>();

    private final ComboBox<Recipe> sunBreakfast = new ComboBox<>();
    private final ComboBox<Recipe> sunLunch = new ComboBox<>();
    private final ComboBox<Recipe> sunDinner = new ComboBox<>();

    private PieChart cuisinePieChart;
    private StackedBarChart<String, Number> weeklyChart;

    @Override
    public void start(Stage stage) {
        TabPane tabPane = new TabPane();

        Tab recipesTab = new Tab("Recipes", createRecipesPane());
        recipesTab.setClosable(false);

        Tab plannerTab = new Tab("Meal Planner", createMealPlannerPane());
        plannerTab.setClosable(false);

        Tab groceryTab = new Tab("Grocery List", createGroceryPane());
        groceryTab.setClosable(false);

        Tab nutritionTab = new Tab("Nutrition Lookup", createNutritionPane());
        nutritionTab.setClosable(false);

        Tab cuisineTab = new Tab("Cuisine Explorer", createCuisineExplorerPane());
        cuisineTab.setClosable(false);

        Tab summaryTab = new Tab("Weekly Summary", createWeeklySummaryPane());
        summaryTab.setClosable(false);

        tabPane.getTabs().addAll(
                recipesTab,
                plannerTab,
                groceryTab,
                nutritionTab,
                cuisineTab,
                summaryTab
        );

        Scene scene = new Scene(tabPane, 1100, 700);
        stage.setTitle("Project B - Recipe Manager & Meal Planner");
        stage.setScene(scene);
        stage.show();
    }

    private Pane createRecipesPane() {
        Label titleLabel = new Label("Recipe Manager");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label nameLabel = new Label("Recipe Name:");
        TextField nameField = new TextField();
        nameField.setPromptText("e.g. Chicken Fried Rice");

        Label cuisineLabel = new Label("Cuisine:");
        ComboBox<String> cuisineBox = new ComboBox<>();
        cuisineBox.getItems().addAll("Chinese", "Italian", "Mexican", "American", "Indian", "Japanese", "Korean");
        cuisineBox.setPromptText("Select cuisine");

        Label ingredientsLabel = new Label("Ingredients:");
        TextArea ingredientsArea = new TextArea();
        ingredientsArea.setPromptText("Enter one ingredient per line.\nExample:\n2 eggs\n1 cup rice\n200 g chicken");
        ingredientsArea.setPrefRowCount(8);

        Label stepsLabel = new Label("Steps:");
        TextArea stepsArea = new TextArea();
        stepsArea.setPromptText("Enter one step per line.");
        stepsArea.setPrefRowCount(6);

        Label caloriesLabel = new Label("Calories:");
        TextField caloriesField = new TextField();
        caloriesField.setPromptText("e.g. 450");

        Label proteinLabel = new Label("Protein (g):");
        TextField proteinField = new TextField();
        proteinField.setPromptText("e.g. 30");

        Label carbsLabel = new Label("Carbs (g):");
        TextField carbsField = new TextField();
        carbsField.setPromptText("e.g. 40");

        Label fatLabel = new Label("Fat (g):");
        TextField fatField = new TextField();
        fatField.setPromptText("e.g. 15");

        Label resultLabel = new Label("Add a recipe to begin.");

        ListView<Recipe> recipeListView = new ListView<>(recipes);
        recipeListView.setPrefWidth(320);

        Button addButton = new Button("Add Recipe");
        addButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            String cuisine = cuisineBox.getValue();
            String ingredientText = ingredientsArea.getText().trim();
            String stepsText = stepsArea.getText().trim();

            if (name.isEmpty() || cuisine == null || ingredientText.isEmpty() || stepsText.isEmpty()) {
                resultLabel.setText("Error: Please fill in all recipe fields.");
                return;
            }

            double calories;
            double protein;
            double carbs;
            double fat;

            try {
                calories = parseNumber(caloriesField.getText().trim());
                protein = parseNumber(proteinField.getText().trim());
                carbs = parseNumber(carbsField.getText().trim());
                fat = parseNumber(fatField.getText().trim());
            } catch (NumberFormatException ex) {
                resultLabel.setText("Error: Nutrition fields must be numbers.");
                return;
            }

            List<Ingredient> ingredientList = parseIngredients(ingredientText);
            List<String> stepsList = parseLines(stepsText);

            Recipe recipe = new Recipe(name, cuisine, ingredientList, stepsList, calories, protein, carbs, fat);
            recipes.add(recipe);

            refreshAllRecipeBoxes();
            refreshCuisineChart();
            refreshWeeklySummaryChart();

            resultLabel.setText("Recipe added: " + name);

            nameField.clear();
            cuisineBox.getSelectionModel().clearSelection();
            ingredientsArea.clear();
            stepsArea.clear();
            caloriesField.clear();
            proteinField.clear();
            carbsField.clear();
            fatField.clear();
        });

        Button deleteButton = new Button("Delete Selected");
        deleteButton.setOnAction(e -> {
            Recipe selected = recipeListView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                resultLabel.setText("Error: Please select a recipe to delete.");
                return;
            }

            recipes.remove(selected);
            refreshAllRecipeBoxes();
            refreshCuisineChart();
            refreshWeeklySummaryChart();

            resultLabel.setText("Deleted recipe: " + selected.getName());
        });

        Button viewButton = new Button("View Selected");
        viewButton.setOnAction(e -> {
            Recipe selected = recipeListView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                resultLabel.setText("Error: Please select a recipe to view.");
                return;
            }

            StringBuilder details = new StringBuilder();
            details.append("Recipe: ").append(selected.getName()).append("\n");
            details.append("Cuisine: ").append(selected.getCuisine()).append("\n\n");
            details.append("Ingredients:\n");
            for (Ingredient ingredient : selected.getIngredients()) {
                details.append("- ").append(ingredient).append("\n");
            }

            details.append("\nSteps:\n");
            for (int i = 0; i < selected.getSteps().size(); i++) {
                details.append(i + 1).append(". ").append(selected.getSteps().get(i)).append("\n");
            }

            details.append("\nNutrition:\n");
            details.append("Calories: ").append(selected.getCalories()).append("\n");
            details.append("Protein: ").append(selected.getProtein()).append(" g\n");
            details.append("Carbs: ").append(selected.getCarbs()).append(" g\n");
            details.append("Fat: ").append(selected.getFat()).append(" g\n");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Recipe Details");
            alert.setHeaderText(selected.getName());
            alert.setContentText(details.toString());
            alert.showAndWait();
        });

        HBox buttonBox = new HBox(10, addButton, deleteButton, viewButton);
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        GridPane formPane = new GridPane();
        formPane.setHgap(10);
        formPane.setVgap(12);

        formPane.add(nameLabel, 0, 0);
        formPane.add(nameField, 1, 0);

        formPane.add(cuisineLabel, 0, 1);
        formPane.add(cuisineBox, 1, 1);

        formPane.add(ingredientsLabel, 0, 2);
        formPane.add(ingredientsArea, 1, 2);

        formPane.add(stepsLabel, 0, 3);
        formPane.add(stepsArea, 1, 3);

        GridPane nutritionPane = new GridPane();
        nutritionPane.setHgap(10);
        nutritionPane.setVgap(10);
        nutritionPane.add(caloriesLabel, 0, 0);
        nutritionPane.add(caloriesField, 1, 0);
        nutritionPane.add(proteinLabel, 0, 1);
        nutritionPane.add(proteinField, 1, 1);
        nutritionPane.add(carbsLabel, 2, 0);
        nutritionPane.add(carbsField, 3, 0);
        nutritionPane.add(fatLabel, 2, 1);
        nutritionPane.add(fatField, 3, 1);

        formPane.add(new Label("Nutrition:"), 0, 4);
        formPane.add(nutritionPane, 1, 4);

        formPane.add(buttonBox, 1, 5);
        formPane.add(resultLabel, 1, 6);

        VBox leftPane = new VBox(12, titleLabel, formPane);
        leftPane.setPadding(new Insets(20));

        VBox rightPane = new VBox(10, new Label("Saved Recipes"), recipeListView);
        rightPane.setPadding(new Insets(20));
        rightPane.setPrefWidth(320);

        BorderPane root = new BorderPane();
        root.setLeft(leftPane);
        root.setCenter(rightPane);

        return root;
    }

    private Pane createMealPlannerPane() {
        Label titleLabel = new Label("Weekly Meal Planner");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        grid.add(new Label("Day"), 0, 0);
        grid.add(new Label("Breakfast"), 1, 0);
        grid.add(new Label("Lunch"), 2, 0);
        grid.add(new Label("Dinner"), 3, 0);

        addPlannerRow(grid, 1, "Monday", monBreakfast, monLunch, monDinner);
        addPlannerRow(grid, 2, "Tuesday", tueBreakfast, tueLunch, tueDinner);
        addPlannerRow(grid, 3, "Wednesday", wedBreakfast, wedLunch, wedDinner);
        addPlannerRow(grid, 4, "Thursday", thuBreakfast, thuLunch, thuDinner);
        addPlannerRow(grid, 5, "Friday", friBreakfast, friLunch, friDinner);
        addPlannerRow(grid, 6, "Saturday", satBreakfast, satLunch, satDinner);
        addPlannerRow(grid, 7, "Sunday", sunBreakfast, sunLunch, sunDinner);

        Label resultLabel = new Label("Assign recipes to each meal.");

        for (ComboBox<Recipe> box : getAllPlannerBoxes()) {
            box.setOnAction(e -> refreshWeeklySummaryChart());
        }

        Button clearButton = new Button("Clear Planner");
        clearButton.setOnAction(e -> {
            clearPlannerSelections();
            refreshWeeklySummaryChart();
            resultLabel.setText("Meal planner cleared.");
        });

        VBox root = new VBox(12, titleLabel, grid, clearButton, resultLabel);
        root.setPadding(new Insets(20));
        return root;
    }

    private Pane createGroceryPane() {
        Label titleLabel = new Label("Grocery List");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ListView<String> groceryListView = new ListView<>(groceryItems);
        groceryListView.setPrefHeight(420);

        Label resultLabel = new Label("Click the button to generate grocery items from your meal plan.");

        Button generateButton = new Button("Generate Grocery List");
        generateButton.setOnAction(e -> {
            generateGroceryList();
            resultLabel.setText("Grocery list generated.");
        });

        Button clearButton = new Button("Clear Grocery List");
        clearButton.setOnAction(e -> {
            groceryItems.clear();
            resultLabel.setText("Grocery list cleared.");
        });

        HBox buttonBox = new HBox(10, generateButton, clearButton);

        VBox root = new VBox(12, titleLabel, buttonBox, groceryListView, resultLabel);
        root.setPadding(new Insets(20));
        return root;
    }

    private Pane createNutritionPane() {
        Label titleLabel = new Label("Nutrition Lookup");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label inputLabel = new Label("Ingredient:");
        TextField ingredientField = new TextField();
        ingredientField.setPromptText("e.g. 1 egg");

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setWrapText(true);
        resultArea.setPrefRowCount(16);
        resultArea.setText("Enter an ingredient and click Lookup Nutrition.");

        Button lookupButton = new Button("Lookup Nutrition");
        lookupButton.setOnAction(e -> {
            String ingredient = ingredientField.getText().trim();
            if (ingredient.isEmpty()) {
                resultArea.setText("Please enter an ingredient.");
                return;
            }
            lookupNutrition(ingredient, resultArea);
        });

        VBox root = new VBox(12, titleLabel, inputLabel, ingredientField, lookupButton, resultArea);
        root.setPadding(new Insets(20));
        return root;
    }

    private Pane createCuisineExplorerPane() {
        Label titleLabel = new Label("Cuisine Explorer");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        cuisinePieChart = new PieChart();
        cuisinePieChart.setTitle("Recipe Counts by Cuisine");

        Button refreshButton = new Button("Refresh Cuisine Chart");
        refreshButton.setOnAction(e -> refreshCuisineChart());

        VBox root = new VBox(12, titleLabel, refreshButton, cuisinePieChart);
        root.setPadding(new Insets(20));

        refreshCuisineChart();
        return root;
    }

    private Pane createWeeklySummaryPane() {
        Label titleLabel = new Label("Weekly Nutrition Summary");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Week");
        yAxis.setLabel("Nutrition");

        weeklyChart = new StackedBarChart<>(xAxis, yAxis);
        weeklyChart.setTitle("Aggregated Weekly Nutrition Summary");

        Button refreshButton = new Button("Refresh Weekly Summary");
        refreshButton.setOnAction(e -> refreshWeeklySummaryChart());

        VBox root = new VBox(12, titleLabel, refreshButton, weeklyChart);
        root.setPadding(new Insets(20));

        refreshWeeklySummaryChart();
        return root;
    }

    private void addPlannerRow(GridPane grid, int row, String day,
                               ComboBox<Recipe> breakfast,
                               ComboBox<Recipe> lunch,
                               ComboBox<Recipe> dinner) {
        breakfast.setPrefWidth(180);
        lunch.setPrefWidth(180);
        dinner.setPrefWidth(180);

        breakfast.setItems(recipes);
        lunch.setItems(recipes);
        dinner.setItems(recipes);

        grid.add(new Label(day), 0, row);
        grid.add(breakfast, 1, row);
        grid.add(lunch, 2, row);
        grid.add(dinner, 3, row);
    }

    private void refreshAllRecipeBoxes() {
        for (ComboBox<Recipe> box : getAllPlannerBoxes()) {
            box.setItems(recipes);
        }
    }

    private void clearPlannerSelections() {
        for (ComboBox<Recipe> box : getAllPlannerBoxes()) {
            box.getSelectionModel().clearSelection();
        }
    }

    private ComboBox<Recipe>[] getAllPlannerBoxes() {
        return new ComboBox[]{
                monBreakfast, monLunch, monDinner,
                tueBreakfast, tueLunch, tueDinner,
                wedBreakfast, wedLunch, wedDinner,
                thuBreakfast, thuLunch, thuDinner,
                friBreakfast, friLunch, friDinner,
                satBreakfast, satLunch, satDinner,
                sunBreakfast, sunLunch, sunDinner
        };
    }

    private List<Ingredient> parseIngredients(String text) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (String line : text.split("\\n")) {
            String value = line.trim();
            if (!value.isEmpty()) {
                ingredients.add(new Ingredient(value));
            }
        }
        return ingredients;
    }

    private List<String> parseLines(String text) {
        List<String> lines = new ArrayList<>();
        for (String line : text.split("\\n")) {
            String value = line.trim();
            if (!value.isEmpty()) {
                lines.add(value);
            }
        }
        return lines;
    }

    private double parseNumber(String text) {
        if (text.isEmpty()) {
            return 0;
        }
        return Double.parseDouble(text);
    }

    private void generateGroceryList() {
        groceryItems.clear();

        Map<String, Integer> counts = new LinkedHashMap<>();

        for (ComboBox<Recipe> box : getAllPlannerBoxes()) {
            Recipe recipe = box.getValue();
            if (recipe == null) {
                continue;
            }

            for (Ingredient ingredient : recipe.getIngredients()) {
                String name = ingredient.getText();
                counts.put(name, counts.getOrDefault(name, 0) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            groceryItems.add(entry.getValue() + " x " + entry.getKey());
        }
    }

    private void refreshCuisineChart() {
        if (cuisinePieChart == null) {
            return;
        }

        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();
        Map<String, Integer> counts = new LinkedHashMap<>();

        for (Recipe recipe : recipes) {
            String cuisine = recipe.getCuisine();
            counts.put(cuisine, counts.getOrDefault(cuisine, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            chartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        cuisinePieChart.setData(chartData);
    }

    private void refreshWeeklySummaryChart() {
        if (weeklyChart == null) {
            return;
        }

        weeklyChart.getData().clear();

        double totalCalories = 0;
        double totalProtein = 0;
        double totalCarbs = 0;
        double totalFat = 0;

        for (ComboBox<Recipe> box : getAllPlannerBoxes()) {
            Recipe recipe = box.getValue();
            if (recipe != null) {
                totalCalories += recipe.getCalories();
                totalProtein += recipe.getProtein();
                totalCarbs += recipe.getCarbs();
                totalFat += recipe.getFat();
            }
        }

        XYChart.Series<String, Number> caloriesSeries = new XYChart.Series<>();
        caloriesSeries.setName("Calories");
        caloriesSeries.getData().add(new XYChart.Data<>("Week Total", totalCalories));

        XYChart.Series<String, Number> proteinSeries = new XYChart.Series<>();
        proteinSeries.setName("Protein");
        proteinSeries.getData().add(new XYChart.Data<>("Week Total", totalProtein));

        XYChart.Series<String, Number> carbsSeries = new XYChart.Series<>();
        carbsSeries.setName("Carbs");
        carbsSeries.getData().add(new XYChart.Data<>("Week Total", totalCarbs));

        XYChart.Series<String, Number> fatSeries = new XYChart.Series<>();
        fatSeries.setName("Fat");
        fatSeries.getData().add(new XYChart.Data<>("Week Total", totalFat));

        weeklyChart.getData().addAll(caloriesSeries, proteinSeries, carbsSeries, fatSeries);
    }

    private void lookupNutrition(String ingredient, TextArea resultArea) {
        String apiKey = "b324949fd093484b9ad1b73f5139c7d6";

        try {
            String encoded = URLEncoder.encode(ingredient, StandardCharsets.UTF_8);

            String url = "https://api.spoonacular.com/recipes/parseIngredients"
                    + "?apiKey=" + apiKey
                    + "&servings=1"
                    + "&includeNutrition=true"
                    + "&ingredientList=" + encoded;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString("ingredientList=" + encoded))
                    .build();

            resultArea.setText("Loading nutrition data...");

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(responseBody ->
                            Platform.runLater(() -> {
                                String calories = extractValue(responseBody, "calories");
                                String protein  = extractValue(responseBody, "protein");
                                String fat      = extractValue(responseBody, "fat");
                                String carbs    = extractValue(responseBody, "carbohydrates");
                                resultArea.setText(
                                        "Ingredient: " + ingredient + "\n\n" +
                                                "Calories: " + calories + "\n" +
                                                "Protein: " + protein + "\n" +
                                                "Carbs: " + carbs + "\n" +
                                                "Fat: " + fat
                                );
                            }))
                    .exceptionally(ex -> {
                        Platform.runLater(() -> resultArea.setText("Error:\n" + ex.getMessage()));
                        return null;
                    });

        } catch (Exception ex) {
            resultArea.setText("Error: " + ex.getMessage());
        }
    }

    private String extractValue(String body, String nutrientName) {
        // Matches "name":"calories","amount":72.0 or similar
        Pattern p = Pattern.compile(
                "\"name\"\\s*:\\s*\"" + nutrientName + "\"\\s*,\\s*\"amount\"\\s*:\\s*([0-9]+\\.?[0-9]*)",
                Pattern.CASE_INSENSITIVE
        );
        Matcher m = p.matcher(body);
        if (m.find()) {
            return m.group(1);
        }
        return "N/A";
    }

    public static void main(String[] args) {
        launch(args);
    }

    static class Recipe {
        private final String name;
        private final String cuisine;
        private final List<Ingredient> ingredients;
        private final List<String> steps;
        private final double calories;
        private final double protein;
        private final double carbs;
        private final double fat;

        public Recipe(String name, String cuisine, List<Ingredient> ingredients, List<String> steps,
                      double calories, double protein, double carbs, double fat) {
            this.name = name;
            this.cuisine = cuisine;
            this.ingredients = ingredients;
            this.steps = steps;
            this.calories = calories;
            this.protein = protein;
            this.carbs = carbs;
            this.fat = fat;
        }

        public String getName() {
            return name;
        }

        public String getCuisine() {
            return cuisine;
        }

        public List<Ingredient> getIngredients() {
            return ingredients;
        }

        public List<String> getSteps() {
            return steps;
        }

        public double getCalories() {
            return calories;
        }

        public double getProtein() {
            return protein;
        }

        public double getCarbs() {
            return carbs;
        }

        public double getFat() {
            return fat;
        }

        @Override
        public String toString() {
            return name + " (" + cuisine + ")";
        }
    }

    static class Ingredient {
        private final String text;

        public Ingredient(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return text;
        }
    }
