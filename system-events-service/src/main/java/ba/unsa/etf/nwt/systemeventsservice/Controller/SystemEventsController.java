package ba.unsa.etf.nwt.systemeventsservice.Controller;

import ba.unsa.etf.nwt.systemeventsservice.Model.SystemEvent;
import ba.unsa.etf.nwt.systemeventsservice.Repository.SystemEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class SystemEventsController {
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
    Iterable<SystemEvent> getAllSystemEvents(){
        return systemEventRepository.findAll();
    }
}
