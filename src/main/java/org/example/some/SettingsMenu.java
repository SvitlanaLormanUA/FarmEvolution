package org.example.some;

import javafx.scene.Node;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SettingsMenu {
    private Pane root;
    public ImageView close;

    SettingsMenu(AnchorPane anchorPane) {
        addPane(anchorPane);
        addCLosingForMenu(anchorPane);



    }
    private void addPane(AnchorPane anchorPane) {
        root = new Pane();
        root.setStyle("-fx-background-color: rgba(0,0,0,0.63); -fx-background-radius: 10; -fx-background-insets: 0; -fx-padding: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.63), 10, 0, 0, 0);");
        root.setPrefSize(307, 507);
        root.setLayoutX(500);
        root.setLayoutY(100);

    }
    public void addCLosingForMenu(AnchorPane anchorPane) {
        close = new ImageView("file:src/main/resources/images/menuSettings/closingForMenu.png");
        close.setFitWidth(30);
        close.setFitHeight(30);
        close.setLayoutX(270);
        close.setLayoutY(10);
        root.getChildren().add(close);
        close.setOnMouseClicked(event -> anchorPane.getChildren().remove(root));

    }
   /* void applyBlur(boolean apply, AnchorPane anchorPane) {
        GaussianBlur blur = apply ? new GaussianBlur(20) : null;
        for (Node node : anchorPane.getChildren()) {
            if (!node.equals(this.getRoot())) {
                node.setEffect(blur);
            }
        }
    }
*/

    public Pane getRoot() {
        return root;
    }
}
