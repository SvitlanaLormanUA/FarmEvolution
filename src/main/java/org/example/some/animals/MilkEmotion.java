package org.example.some.animals;

import javafx.scene.image.ImageView;

public class MilkEmotion {
    private ImageView thought;
    private ImageView milk;

    public MilkEmotion(double x, double y) {
        addThought(x, y);
        addMilk(x + 10, y + 10);
    }

    private void addThought(double x, double y) {
        thought = new ImageView("file:src/main/resources/images/reaction.png");
        thought.setFitWidth(50);
        thought.setFitHeight(50);
        thought.setX(x);
        thought.setY(y);
        AbstractAnimal.root.getChildren().add(thought);
    }

    private void addMilk(double x, double y) {
        milk = new ImageView("file:src/main/resources/images/firstLevel/products/milk.png");
        milk.setFitWidth(30);
        milk.setFitHeight(30);
        milk.setX(x);
        milk.setY(y);
        AbstractAnimal.root.getChildren().add(milk);
    }

    public void updatePosition(double x, double y) {
        thought.setX(x);
        thought.setY(y);
        milk.setX(x + 10);
        milk.setY(y + 10);
    }
}