package me.seunghui.springbootdeveloper.controller;

import lombok.extern.slf4j.Slf4j;
import me.seunghui.springbootdeveloper.domain.User;
import me.seunghui.springbootdeveloper.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@Slf4j
@RequestMapping("/chat")
public class ChatRoomController {

    private final UserService userService;

    public ChatRoomController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/room/{roomId}")
    public String chatRoom(@PathVariable Long roomId, Model model,
                           Principal principal) {
        log.info("principal {}", principal.getName());
        User user = userService.findByEmail(principal.getName());
        String nickname =user.getNickname();

        model.addAttribute("username", principal.getName());
        model.addAttribute("roomId", roomId);
        model.addAttribute("nickname", nickname);
        return "chatting/chatRoom";
    }

}