module com.example.grpcservice {
    requires javafx.controls;
    requires javafx.fxml;
    requires grpc.server.spring.boot.autoconfigure;


    opens com.example.grpcservice to javafx.fxml;
    exports com.example.grpcservice;
}