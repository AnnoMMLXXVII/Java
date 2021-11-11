package io.eclipse.springbootstarter.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

    @Autowired // marks this as a need to have dependecy injection
    private TopicService topServ;

    // USING THE GET REQUEST
    @RequestMapping("/topics")
    public List<Topic> getOmnesTopic() {
        return topServ.getAllTopics(); // Converts return to JSON AUTOMATICALLY as a HTTPS RESPONSE
    }

    // USING THE GET REQUEST
    @RequestMapping("/topics/{id}") // {variable} portion
    // {Variable} with @PathVariable will link the variable and the Parameter to be the same. if {Variable} = 1 -> Param = 1;
    // Names are the same thus allows it to work
    // IF NAMES !SAME -> use @PathVariable("{Variable}") ; ex --> "/topic/{foo}" -> @PathVairable("foo") String id ; NOT CONVENTIONAL THOUGH
    public Topic getTopic(@PathVariable String id) {
        return topServ.getTopicID(id);
    }

    // GOING TO ADD A NEW TOPIC USING THE POST REQUEST
    // CONFIGURE THE REQUEST MAPPING TO POST... since get is already using "/topics".. will conflict
    // REMAPS all post requests to THIS request NOT the above requests
    @RequestMapping(method = RequestMethod.POST, value = "/topics")
    public void addTopic(@RequestBody Topic top) {
        topServ.addTopic(top);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/topics/{id}")
    public void updateTopic(@RequestBody Topic top, @PathVariable String id) {
        topServ.updateTopic(id, top);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/topics/{id}")
    public void deleteTopic(@PathVariable String id) {
        topServ.deleteTopic(id);
    }


}
