module org.example.some {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires javafx.media;

    opens org.example.some to javafx.fxml;
    exports org.example.some;
    exports org.example.some.animals;
    opens org.example.some.animals to javafx.fxml;
    exports org.example.some.otherGameObjects;
    opens org.example.some.otherGameObjects to javafx.fxml;
}