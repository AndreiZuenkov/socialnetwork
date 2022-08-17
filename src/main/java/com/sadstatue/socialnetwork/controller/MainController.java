package com.sadstatue.socialnetwork.controller;

import com.sadstatue.socialnetwork.exceptions.NotFoundException;
import com.sadstatue.socialnetwork.persistence.model.Message;
import com.sadstatue.socialnetwork.persistence.repository.IMessageRepo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("message")
public class MainController {

    private IMessageRepo iMessageRepo;

    public MainController(IMessageRepo iMessageRepo) {
        this.iMessageRepo = iMessageRepo;
    }

    @GetMapping()
    public List<Message> get() {

        List<Message> list = new ArrayList<>();

        iMessageRepo.findAll().forEach(list::add);

        return list;
    }

    @GetMapping("{id}")
    public Message getMessage(@PathVariable String id) {

        return iMessageRepo.findById(Long.parseLong(id)).orElseThrow(NotFoundException::new);

    }

    @PostMapping()
    public Message createMessage(@RequestBody Message message) {


        return iMessageRepo.save(new Message(message.getText(), LocalDateTime.now()));
    }

    @PutMapping("{id}")
    public Message updateMessage(@RequestBody Message message, @PathVariable String id) {


        Message updatedMessage = iMessageRepo.findById(Long.parseLong(id))
                .orElseThrow(NotFoundException::new);

        updatedMessage.setText(message.getText());


        return iMessageRepo.save(updatedMessage);
    }


    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable String id) {

        iMessageRepo.deleteById(Long.parseLong(id));

    }

}
