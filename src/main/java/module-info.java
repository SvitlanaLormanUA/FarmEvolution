module org.example.some {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens org.example.some to javafx.fxml;
    exports org.example.some;
    exports org.example.some.animals;
    opens org.example.some.animals to javafx.fxml;
}