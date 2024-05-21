module org.example.some {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens org.example.some to javafx.fxml;
    exports org.example.some;
}