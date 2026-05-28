package helpdesk;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/module1")
    public String hello() {
        return "Hello, Help Desk!";
    }
}