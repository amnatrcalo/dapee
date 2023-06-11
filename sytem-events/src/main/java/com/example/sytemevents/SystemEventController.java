package com.example.sytemevents;
//import com.example.sytemevents.SystemEventModel;
//import com.example.sytemevents.SystemEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
/*
@RestController
@RequestMapping(path = "/api")
public class SystemEventController {
    @Value("${my.greeting}")
    private String greetingMessage;

    @Autowired
    private SystemEventRepository systemEventRepository;

    @GetMapping(path = "/greeting")
    public String greeting(){
        return "my. greeting: "+greetingMessage;
    }

    @GetMapping(path = "/system-events")
    public  @ResponseBody
    Iterable<SystemEventModel> getAllSystemEvents(){
        return systemEventRepository.findAll();
    }
}*/

