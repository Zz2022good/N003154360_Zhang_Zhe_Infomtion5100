package edu.info5100.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView; //image,imageView are for displaying images.
import javafx.scene.layout.VBox; // label,button,Vbox are the UI components.
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;  //ImageIO, BufferedImage are for reading image file.
import java.io.File;

public class ImageToolApp extends Application {      //main program (GUI app).

    private ImageFile currentImage;  //Stores the uploaded image.
    private final ImageManager manager = ImageManager.getInstance(); //Gets a shared Object to store images.

    @Override
    public void start(Stage stage) {
        Label titleLabel = new Label("Image Management Tool");  // Creates a text label
        titleLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;"); // Makes the text bigger

        Label instructionLabel = new Label("Upload an image, view its properties, and convert it to another format.");

        ImageView imageView = new ImageView();
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);   //100*100 thumbnail.

        Label propertiesLabel = new Label("No image selected.");

        ComboBox<String> formatBox = new ComboBox<>();
        formatBox.getItems().addAll("png", "jpg", "bmp");
        formatBox.setPromptText("Select output format");      //Lets the user choose output format of the Image.

        Label resultLabel = new Label(""); //Show success or error message.

        Button uploadButton = new Button("Upload Image");
        uploadButton.setOnAction(e -> {   //Runs when button is clicked.
            try {
                FileChooser fileChooser = new FileChooser();   //Opens the file browser
                fileChooser.setTitle("Choose Image File");
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp")
                );

                File selectedFile = fileChooser.showOpenDialog(stage);  //User picks a file

                if (selectedFile == null) {
                    resultLabel.setText("No file selected.");
                    return;
                }   //User cancels

                BufferedImage bufferedImage = ImageIO.read(selectedFile); //Read the image,load it into memory.

                if (bufferedImage == null) {
                    resultLabel.setText("Error: This file is not a valid image.");
                    return;   //Checks exception.
                }

                currentImage = new ImageFile(
                        selectedFile.getAbsolutePath(),
                        selectedFile.getName(),
                        bufferedImage.getWidth(),
                        bufferedImage.getHeight()
                );  //Save image properties: File name,location,height,width.

                manager.addImage(currentImage);  //Save image in manager.

                Image image = new Image(selectedFile.toURI().toString());
                imageView.setImage(image);  //Displays Thumbnail.

                propertiesLabel.setText(currentImage.getFileInfo());
                resultLabel.setText("Image uploaded successfully.");

            } catch (Exception ex) {
                resultLabel.setText("Error uploading image: " + ex.getMessage());
            }  //If something breaks, show error message.
        });

        Button convertButton = new Button("Convert Image");
        convertButton.setOnAction(e -> {
            try {
                if (currentImage == null) {
                    resultLabel.setText("Error: Please upload an image first.");
                    return;
                }

                String format = formatBox.getValue();
                if (format == null) {
                    resultLabel.setText("Error: Please select an output format.");
                    return;
                }

                // Open a Save Dialog so user picks where to save
                FileChooser saveChooser = new FileChooser();
                saveChooser.setTitle("Save Converted Image");
                saveChooser.setInitialFileName("converted." + format);
                saveChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter(format.toUpperCase(), "*." + format)
                );

                File saveFile = saveChooser.showSaveDialog(stage);
                if (saveFile == null) {
                    resultLabel.setText("Save cancelled.");
                    return;
                }

                // Convert directly to the chosen location
                ImageAction converter = new ImageConverter();
                converter.process(currentImage, format, saveFile); // updated signature
                resultLabel.setText("Image saved to:\n" + saveFile.getAbsolutePath());

            } catch (Exception ex) {
                resultLabel.setText("Error converting image: " + ex.getMessage());
            }
        });

        Button clearButton = new Button("Clear");   // Clear Button
        clearButton.setOnAction(e -> {
            currentImage = null;    // remove image
            imageView.setImage(null);
            formatBox.getSelectionModel().clearSelection();
            propertiesLabel.setText("No image selected.");  // reset text
            resultLabel.setText("");
        });

        VBox root = new VBox(12,   // Arranges UI elements top to down.

                titleLabel,
                instructionLabel,
                uploadButton,
                imageView,
                propertiesLabel,
                formatBox,
                convertButton,
                clearButton,
                resultLabel
        );

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20)); // Adds space
        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);

        Scene scene = new Scene(scrollPane, 520, 650);  // Window size
        stage.setTitle("Image Management Tool");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);  // Starts JavaFX app.
    }
}

class MediaFile { // Parent Class
    private String filePath;    //Encapsulation
    private String fileName;

    public MediaFile(String filePath, String fileName) { // Constructor
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }
}


class ImageFile extends MediaFile {    // Inheritance,Child class, stores image information.
    private int width;
    private int height;

    public ImageFile(String filePath, String fileName, int width, int height) {
        super(filePath, fileName); // Calls parent constructor.
        this.width = width;
        this.height = height;
    }

    public String getFileInfo() {
        return "File Name: " + getFileName()
                + "\nPath: " + getFilePath()
                + "\nWidth: " + width
                + "\nHeight: " + height;
    }
}


 // Interface.ImageConverter implements this interface.
interface ImageAction {
    void process(ImageFile imageFile, String format, File outputFile) throws Exception;
}



class ImageConverter implements ImageAction {
    @Override
    public void process(ImageFile imageFile, String format, File outputFile) throws Exception {
            File inputFile = new File(imageFile.getFilePath());
            BufferedImage image = ImageIO.read(inputFile);
            ImageIO.write(image, format, outputFile);  // Read Images,convert format and save file.
        }
    }

class ImageManager {
    private static final ImageManager instance = new ImageManager();
    private ImageFile lastImage;

    private ImageManager() {
    }

    public static ImageManager getInstance() {
        return instance;
    }

    public void addImage(ImageFile imageFile) {
        lastImage = imageFile;
    }

    public ImageFile getLastImage() {
        return lastImage;
    }
}
