package ba.unsa.etf.nwt.systemeventsservice;

import ba.unsa.etf.nwt.systemeventsservice.Service.SystemEventService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SystemEventsServiceApplication {

    public static void main(String[] args) throws IOException, InterruptedException {

        SpringApplication.run(SystemEventsServiceApplication.class, args);
//        Server server = ServerBuilder
//                .forPort(8089)
//                .addService(new HelloService()).build();
//
//        server.start();
//        server.awaitTermination();
//        Server server = ServerBuilder
//                .forPort(8089)
//                .addService(new SystemEventService()).build();
//
//        server.start();
//        server.awaitTermination();
    }

}
