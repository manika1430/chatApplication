package com.learning.chat.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {
    
    @RequestMapping("/chatApp")
    public String getChatPage() {
        return "chat"; // This will load chat.jsp from /WEB-INF/jsp/
    }
}
