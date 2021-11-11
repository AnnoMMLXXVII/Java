package io.eclipse.springbootstarter.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//ANNOTATION -- REST CONTROLLER


@RestController

// ALLOWS FOR METHODS THAT CAN MAKE THE APPROPIRATE ACTIONS BASED ON USER REQUESTS
public class HelloController {

    @RequestMapping("/hello")
    public String getPrompt() {
        return "Hello";
    }
}
