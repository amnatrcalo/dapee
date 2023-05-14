package ba.unsa.etf.nwt.systemeventsservice.Service;

import ba.unsa.etf.nwt.systemeventsservice.Model.SystemEvent;
import ba.unsa.etf.nwt.systemeventsservice.Repository.SystemEventRepository;
import ba.unsa.etf.nwt.systemeventsservice.grpc.SystemEventRequest;
import ba.unsa.etf.nwt.systemeventsservice.grpc.SystemEventResponse;
import ba.unsa.etf.nwt.systemeventsservice.grpc.SystemEventsServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class SystemEventService extends SystemEventsServiceGrpc.SystemEventsServiceImplBase{
    @Autowired
    private SystemEventRepository systemEventRepository;



    @Override
    public void log(SystemEventRequest request, StreamObserver<SystemEventResponse> responseObserver) {
        System.out.println("LOG");

        SystemEvent systemEvent = new SystemEvent();
        systemEvent.setTimestamp(request.getTimestamp());
        systemEvent.setMicroservice(request.getMicroservice());
        systemEvent.setUser(request.getUser());
        systemEvent.setAction(request.getAction());
        systemEvent.setResource(request.getResource());
        systemEvent.setResponseType(request.getResponseType());

        System.out.println(systemEvent.getTimestamp());
        System.out.println(systemEvent.getMicroservice());
        System.out.println(systemEvent.getUser());
        System.out.println(systemEvent.getAction());
        System.out.println(systemEvent.getResource());
        System.out.println(systemEvent.getResponseType());

        systemEventRepository.save(systemEvent);

        SystemEventResponse response = SystemEventResponse.newBuilder()
                .setResponsemessage("logged!")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
