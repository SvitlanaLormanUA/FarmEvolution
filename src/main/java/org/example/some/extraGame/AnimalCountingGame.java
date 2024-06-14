package org.example.some.extraGame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.example.some.FirstLevel;

import static org.example.some.FirstLevel.coins;

public class AnimalCountingGame  {
    public static final int COINS = 85;
    StackPane root;
    Stage primaryStage;
    public void start( Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Animal Counting Game");

        // Load the image
        Image image = new Image("file:src/main/resources/images/extraGame/AnimalCountingGame.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(500);
        imageView.setPreserveRatio(true);

        // Canvas to draw the image and rectangles
        Canvas canvas = new Canvas(550, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, 0, 0, 550, 400);

        // Draw rectangles for input fields
        double[][] positions = {
                {470, 20},  // Butterfly
                {470, 70}, // Elephant
                {470, 130}, // Snake
                {470, 185}, // Penguin
                {470, 240}, // Octopus
                {470, 295}, // Crab
                {470, 350}  // Bear
        };

        // Create ComboBoxes for each animal
        ComboBox<Integer> butterflyInput = new ComboBox<>();
        fillComboBox(butterflyInput);
        butterflyInput.setLayoutX(positions[0][0]);
        butterflyInput.setLayoutY(positions[0][1]);

        ComboBox<Integer> elephantInput = new ComboBox<>();
        fillComboBox(elephantInput);
        elephantInput.setLayoutX(positions[1][0]);
        elephantInput.setLayoutY(positions[1][1]);

        ComboBox<Integer> snakeInput = new ComboBox<>();
        fillComboBox(snakeInput);
        snakeInput.setLayoutX(positions[2][0]);
        snakeInput.setLayoutY(positions[2][1]);

        ComboBox<Integer> penguinInput = new ComboBox<>();
        fillComboBox(penguinInput);
        penguinInput.setLayoutX(positions[3][0]);
        penguinInput.setLayoutY(positions[3][1]);

        ComboBox<Integer> octopusInput = new ComboBox<>();
        fillComboBox(octopusInput);
        octopusInput.setLayoutX(positions[4][0]);
        octopusInput.setLayoutY(positions[4][1]);

        ComboBox<Integer> crabInput = new ComboBox<>();
        fillComboBox(crabInput);
        crabInput.setLayoutX(positions[5][0]);
        crabInput.setLayoutY(positions[5][1]);

        ComboBox<Integer> bearInput = new ComboBox<>();
        fillComboBox(bearInput);
        bearInput.setLayoutX(positions[6][0]);
        bearInput.setLayoutY(positions[6][1]);

        // Pane to hold the canvas and ComboBoxes
        Pane pane = new Pane();
        pane.getChildren().addAll(canvas, butterflyInput, elephantInput, snakeInput, penguinInput, octopusInput, crabInput, bearInput);

        // Button to check answers
        Button checkButton = new Button("Перевірити");
        checkButton.setOnAction(e -> checkAnswers(
                butterflyInput,
                elephantInput,
                snakeInput,
                penguinInput,
                octopusInput,
                crabInput,
                bearInput
        ));

        // Button to close the application
        Button closeButton = new Button("Закрити гру");
        closeButton.setOnAction(e -> primaryStage.close());

        // Layout
        HBox hbox = new HBox(10, checkButton, closeButton);
        hbox.setPadding(new Insets(10));
        hbox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(10, pane, hbox);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);

        // Instruction overlay
        StackPane instructionOverlay = createInstructionOverlay();

        // Main layout with instruction overlay
        root = new StackPane();
        root.getChildren().addAll(vbox, instructionOverlay);

        Scene scene = new Scene(root, 550, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void fillComboBox(ComboBox<Integer> comboBox) {
        for (int i = 0; i <= 10; i++) {
            comboBox.getItems().add(i);
        }
        comboBox.setValue(0);
    }

    private void checkAnswers(ComboBox<Integer> butterflyInput, ComboBox<Integer> elephantInput, ComboBox<Integer> snakeInput, ComboBox<Integer> penguinInput, ComboBox<Integer> octopusInput, ComboBox<Integer> crabInput, ComboBox<Integer> bearInput) {
        // Replace the correct numbers below with the actual counts
        int correctButterflies = 7;
        int correctElephants = 6;
        int correctSnakes = 4;
        int correctPenguins = 5;
        int correctOctopuses = 6;
        int correctCrabs = 3;
        int correctBears = 4;

        boolean allCorrect = true;

        allCorrect &= checkAnswer(butterflyInput, correctButterflies);
        allCorrect &= checkAnswer(elephantInput, correctElephants);
        allCorrect &= checkAnswer(snakeInput, correctSnakes);
        allCorrect &= checkAnswer(penguinInput, correctPenguins);
        allCorrect &= checkAnswer(octopusInput, correctOctopuses);
        allCorrect &= checkAnswer(crabInput, correctCrabs);
        allCorrect &= checkAnswer(bearInput, correctBears);

        if (!allCorrect) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Результат");
            alert.setHeaderText(null);
            alert.setContentText("Спробуйте ще раз");
            alert.showAndWait();

            resetComboBoxes(butterflyInput, elephantInput, snakeInput, penguinInput, octopusInput, crabInput, bearInput);
        }
        if (allCorrect) {
            addCoins();
        }

    }

    private void addCoins() {
        coins+=COINS;
        FirstLevel.wallet.income(COINS);
        FirstLevel.wallet.setCoins(coins);
        FirstLevel.saveCoins();
    }
    private boolean checkAnswer(ComboBox<Integer> input, int correctValue) {
        if (input.getValue() == correctValue) {
            input.setStyle("-fx-background-color: green;");
            return true;
        } else {
            input.setStyle("-fx-background-color: red;");
            return false;
        }
    }

    private void resetComboBoxes(ComboBox<Integer>... comboBoxes) {
        for (ComboBox<Integer> comboBox : comboBoxes) {
            comboBox.setValue(0);
            comboBox.setStyle("");  // Reset style to default
        }
    }

    private StackPane createInstructionOverlay() {
        // Create a transparent background
        Rectangle bg = new Rectangle(550, 450, Color.BLACK);
        bg.setOpacity(0.8);


        Label instructions = new Label(
                "Вітаємо в грі 'Animal Counting Game'!\n\n" +
                        "Ваше завдання - правильно вказати кількість кожної тварини, зображеної на малюнку.\n" +
                        "Для цього використовуйте випадаючі списки праворуч від кожної тварини.\n" +
                        "Натисніть 'Перевірити', щоб побачити результат.\n" +
                        "Якщо відповідь неправильна, випадаючий список підсвітиться червоним.\n" +
                        "Якщо відповідь правильна, випадаючий список підсвітиться зеленим.\n" +
                        "Якщо хоча б одна відповідь неправильна, усі відповіді буде скинуто, і ви зможете спробувати ще раз.\n\n" +
                        "Удачі!"
        );
        instructions.setTextFill(Color.WHITE);
        instructions.setStyle("-fx-font-size: 16px;");
        instructions.setWrapText(true);

        VBox instructionBox = new VBox(instructions);
        instructionBox.setAlignment(Pos.CENTER);
        instructionBox.setPadding(new Insets(20));
        instructionBox.setSpacing(10);

        StackPane instructionOverlay = new StackPane(bg, instructionBox);
        instructionOverlay.setAlignment(Pos.CENTER);


        Button closeButton = new Button("Почати");
        closeButton.setOnAction(e -> instructionOverlay.setVisible(false));
        VBox closeBox = new VBox(closeButton);
        closeBox.setAlignment(Pos.BOTTOM_CENTER);
        closeBox.setPadding(new Insets(10));
        StackPane.setAlignment(closeButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(closeButton, new Insets(20));

        instructionOverlay.getChildren().add(closeBox);
        return instructionOverlay;
    }
}