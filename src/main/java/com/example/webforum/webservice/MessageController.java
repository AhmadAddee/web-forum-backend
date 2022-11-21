package com.example.webforum.webservice;

import com.example.webforum.business.IMessageService;
import com.example.webforum.business.bo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@CrossOrigin(origins = "http://localhost:3000")
public class MessageController {
    @Autowired
    private final IMessageService iMessageService;

    public MessageController(IMessageService postService) {
        this.iMessageService = postService;
    }

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.CREATED)
    public String sendMessage(@RequestBody Message message) {
        return this.iMessageService.sendMessage(message);
    }

    @GetMapping("/get")
    public Message getMessageById(@RequestParam(value = "id", required = false)int id) {
        return this.iMessageService.getMessageById(id);
    }

    @GetMapping("/inbox")
    public List<Message> getMyInbox(@RequestParam(value = "username", required = false)String username) {
        return this.iMessageService.findMessageByReceiver(username);
    }
}
