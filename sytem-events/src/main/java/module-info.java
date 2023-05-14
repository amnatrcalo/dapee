module com.example.sytemevents {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sytemevents to javafx.fxml;
    exports com.example.sytemevents;
}