package org.example.some.extraGame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.example.some.FirstLevel;

import java.util.Random;

import static org.example.some.FirstLevel.coins;

public class PuzzleGame extends Application {

    Random random = new Random();
    static PuzzlePiece[] pieces;
    private static final int COINS = 95;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Puzzle Game");

        StackPane root = new StackPane();

        // Додавання рамки
        ImageView background = new ImageView();
        background.setFitWidth(450);
        background.setFitHeight(450);

        GridPane gridPane = new GridPane();

        String baseUrl = "file:src/main/resources/images/extraGame/";
        pieces = new PuzzlePiece[]{
                new PuzzlePiece(baseUrl + "piece6.png", 0, 0),
                new PuzzlePiece(baseUrl + "piece9.png", 0, 1),
                new PuzzlePiece(baseUrl + "piece3.png", 0, 2),
                new PuzzlePiece(baseUrl + "piece7.png", 1, 0),
                new PuzzlePiece(baseUrl + "piece1.png", 1, 1),
                new PuzzlePiece(baseUrl + "piece4.png", 1, 2),
                new PuzzlePiece(baseUrl + "piece8.png", 2, 0),
                new PuzzlePiece(baseUrl + "piece2.png", 2, 1),
                new PuzzlePiece(baseUrl + "piece5.png", 2, 2)
        };

        for (PuzzlePiece piece : pieces) {
            setRandom(piece);
            gridPane.add(piece, piece.getCorrectCol(), piece.getCorrectRow());
        }

        root.getChildren().addAll(background, gridPane);

        // Додавання інструкційного оверлею
        StackPane instructionOverlay = createInstructionOverlay(root);
        root.getChildren().add(instructionOverlay);

        Scene scene = new Scene(root, 450, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setRandom(PuzzlePiece piece) {
        int n = random.nextInt(1, 4);
        int angle = 0;
        for (int i = 1; i <= n; i++) {
            angle += 90;
        }
        if (angle >= 360) {
            angle = 0;
        }
        piece.rotationAngle = angle;
        piece.setRotate(angle);
    }

    private StackPane createInstructionOverlay(Pane mainPane) {
        // Створення прозорого фону
        Rectangle bg = new Rectangle(450, 450, Color.BLACK);
        bg.setOpacity(0.8);

        // Текст інструкції
        Label instructions = new Label("Інструкція:\n\n" +
                "1. Натисніть на частину пазла, щоб обернути її.\n" +
                "2. Частини пазла потрібно обернути правильно, щоб зібрати пазл.\n" +
                "3. Натисніть на кнопку 'Почати' щоб почати гру.");
        instructions.setTextFill(Color.WHITE);
        instructions.setStyle("-fx-font-size: 16px;");
        instructions.setWrapText(true);

        VBox instructionBox = new VBox(instructions);
        instructionBox.setAlignment(Pos.CENTER);
        instructionBox.setPadding(new Insets(20));
        instructionBox.setSpacing(10);

        StackPane instructionOverlay = new StackPane(bg, instructionBox);
        instructionOverlay.setAlignment(Pos.CENTER);

        // Кнопка для закриття інструкції
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

    // Внутрішній клас PuzzlePiece
    public static class PuzzlePiece extends ImageView {
        private final int correctRow;
        private final int correctCol;
        private double rotationAngle = 0;

        public PuzzlePiece(String imageUrl, int correctRow, int correctCol) {
            super(new Image(imageUrl));
            this.correctRow = correctRow;
            this.correctCol = correctCol;

            setFitWidth(150);
            setFitHeight(150);

            setOnDragDetected(event -> startFullDrag());

            setOnMouseDragged(this::handleMouseDragged);

            setOnMouseReleased(event -> handleMouseReleased());

            setOnMouseClicked(this::handleMouseClicked);

        }

        private void handleMouseDragged(MouseEvent event) {
            setLayoutX(event.getSceneX() - getFitWidth() / 2);
            setLayoutY(event.getSceneY() - getFitHeight() / 2);
        }

        private void handleMouseReleased() {
            double x = getLayoutX() + getFitWidth() / 2;
            double y = getLayoutY() + getFitHeight() / 2;

            int col = (int) (x / getFitWidth());
            int row = (int) (y / getFitHeight());

            if (isInCorrectPosition(row, col)) {
                GridPane.setColumnIndex(this, col);
                GridPane.setRowIndex(this, row);
            } else {
                setLayoutX(correctCol * getFitWidth());
                setLayoutY(correctRow * getFitHeight());
            }
        }

        private void handleMouseClicked(MouseEvent event) {
            if (event.getClickCount() == 1) {
                rotationAngle += 90;
                if (rotationAngle >= 360) {
                    rotationAngle = 0;
                }
                setRotate(rotationAngle);
            }
            check();
        }

        private void check() {
            double n = 0;
            for (PuzzlePiece piece : pieces) {
                n += piece.rotationAngle;
            }
            if (n == 0) {
                showAlert();
            }
        }

        private void showAlert() {
            Stage alertStage = new Stage();

            Label message = new Label("Пазл зібрано!");
            Button closeButton = new Button("Закрити гру");
            closeButton.setOnAction(e -> {
                addCoins();
                alertStage.close();
                Stage primaryStage = (Stage) getScene().getWindow();
                primaryStage.close();

            });

            VBox vbox = new VBox(10, message, closeButton);
            vbox.setAlignment(Pos.CENTER);
            vbox.setPrefSize(300, 100);

            Scene alertScene = new Scene(vbox);
            alertStage.setScene(alertScene);
            alertStage.setTitle("Гру закінчено");
            alertStage.show();
        }

        public boolean isInCorrectPosition(int row, int col) {
            return row == correctRow && col == correctCol && rotationAngle % 360 == 0;
        }

        public int getCorrectRow() {
            return correctRow;
        }

        public int getCorrectCol() {
            return correctCol;
        }

        private void addCoins() {
            coins+=COINS;
            FirstLevel.wallet.income(COINS);
            FirstLevel.wallet.setCoins(coins);
            FirstLevel.saveCoins();
        }
    }

}
