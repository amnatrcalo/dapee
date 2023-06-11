package com.example.sytemevents;
//import com.example.sytemevents.SystemEventModel;
//import com.example.sytemevents.SystemEventRepository;
import com.example.sytemevents.grpc.SystemEventRequest;
import com.example.sytemevents.grpc.SystemEventResponse;
import com.example.sytemevents.grpc.SystemEventsServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class SystemEventService extends SystemEventsServiceGrpc.SystemEventsServiceImplBase{
    //@Autowired
    //private SystemEventRepository systemEventRepository;



    @Override
    public void log(SystemEventRequest request, StreamObserver<SystemEventResponse> responseObserver) {
        System.out.println("LOG");

        //SystemEventModel systemEvent = new SystemEventModel();
      /*  systemEvent.setTimestamp(request.getTimestamp());
        systemEvent.setMicroservice(request.getMicroservice());
        systemEvent.setUser(request.getUser());
        systemEvent.setAction(request.getAction());
        systemEvent.setResource(request.getResource());
        systemEvent.setResponseType(request.getResponseType());

       */

        //systemEventRepository.save(systemEvent);
        System.out.println(request.getTimestamp());
        System.out.println(request.getMicroservice());
        System.out.println(request.getUser());
        System.out.println(request.getAction());
        System.out.println(request.getResource());
        System.out.println(request.getResponseType());

        SystemEventResponse response = SystemEventResponse.newBuilder()
                .setResponsemessage("logged!")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
