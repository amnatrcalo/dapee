module com.example.sytemevents {
    requires com.google.protobuf;
    requires grpc.api;
    //requires java.annotation;
    requires grpc.stub;
    requires grpc.protobuf;
    requires com.google.common;
    requires lombok;
    //requires hibernate.jpa;
    requires spring.data.jpa;
    requires spring.beans;
    requires spring.web;
    requires grpc.server.spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires jakarta.annotation;
    //requires spring.cglib;


    opens com.example.sytemevents;
    //reads unnamed module @0x5be6e01c;
    //opens com.example.sytemevents to org.springframework.cglib.core.ReflectUtils;
    //opens com.example.sytemevents;
    //opens com.example.sytemevents to javafx.fxml;
    exports com.example.sytemevents;
}