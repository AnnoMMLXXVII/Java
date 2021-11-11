package io.eclipse.springbootstarter.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service // marks this class as Spring service

// when boot starts, it will find all classes with servce annotaiton and create an instance of it
public class TopicService {

    private List<Topic> topics = new ArrayList<>(Arrays.asList(
            new Topic("1", "Michael", "Regional Manager - Scranton"),
            new Topic("2", "Jim", "Assistant Regional Manager"),
            new Topic("3", "Dwight", "Assistant the Regional Manager"),
            new Topic("4", "Pam", "Receptionist"),
            new Topic("5", "Angela", "Accountant"),
            new Topic("6", "Stanley", "Sales Representative"),
            new Topic("7", "Kelly", "Customer Service"),
            new Topic("8", "Hank", "Security"),
            new Topic("9", "Darryl", "Houseware Manager"),
            new Topic("10", "Toby", "Human Resources")
    ));

    public List<Topic> getAllTopics() {
        return topics;
    }

    public Topic getTopicID(String param) {
        return topics.stream().filter(t -> t.getId().equals(param)).findFirst().get();
    }

    public Topic getTopicName(String name) {
        return topics.stream().filter(t -> t.getName().equals(name)).findFirst().get();
    }

    public Topic getTopicDesc(String description) {
        return topics.stream().filter(t -> t.getDescription().equals(description)).findFirst().get();
    }

    public void addTopic(Topic top) {
        topics.add(top);
    }

    public void updateTopic(String id, Topic top) {
        for (int i = 0; i < topics.size(); i++) {
            Topic t = topics.get(i);
            if (t.getId().equals(id)) {
                topics.set(i, top);
                return;
            } // endae IF
        } // endae FOR
    } // endae updateTopic

    public void deleteTopic(String id) {
        topics.removeIf(t -> t.getId().equals(id));
    } // endae updateTopic

}
