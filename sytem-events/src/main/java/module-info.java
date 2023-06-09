module com.example.sytemevents {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.protobuf;
    requires grpc.api;
    requires java.annotation;
    requires grpc.stub;
    requires grpc.protobuf;
    requires com.google.common;
    requires lombok;
    requires hibernate.jpa;
    requires spring.data.jpa;
    requires spring.beans;
    requires spring.web;
    requires grpc.server.spring.boot.autoconfigure;


    opens com.example.sytemevents to javafx.fxml;
    exports com.example.sytemevents;
}