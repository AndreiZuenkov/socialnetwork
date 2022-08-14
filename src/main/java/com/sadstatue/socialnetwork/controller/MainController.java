package com.sadstatue.socialnetwork.controller;

import com.sadstatue.socialnetwork.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MainController {

    private int counter = 4;

    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<>() {{
            put("id", "1");
            put("text", "first message");
        }});
        add(new HashMap<>() {{
            put("id", "2");
            put("text", "second message");
        }});
        add(new HashMap<>() {{
            put("id", "3");
            put("text", "third message");
        }});
    }};


    @GetMapping()
    public List<Map<String, String>> get() {

        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getMessage(@PathVariable String id) {

        return findMessage(id);

    }

    @PostMapping()
    public Map<String, String> createMessage(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(counter++));

        messages.add(message);

        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> updateMessage(@RequestBody Map<String, String> message, @PathVariable String id) {
        Map<String, String> messageFromList = findMessage(id);

        messageFromList.putAll(message);
        messageFromList.put("id", id);


        return messageFromList;
    }

    private Map<String, String> findMessage(String id) {

        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);

    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable String id) {

        Map<String, String> messageFromList = findMessage(id);

        messages.remove(messageFromList);

    }

}
