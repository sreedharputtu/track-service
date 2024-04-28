package com.trackservice.controller.user;

import com.trackservice.dto.UserDto;
import com.trackservice.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Slf4j
@Controller
public class UserController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestParam Map<String,String> body, Model model){
        log.info("Registering user: {}", body);
        UserDto userDto = modelMapper.map(body, UserDto.class);
        userService.saveUser(userDto);
        return "html/login.html";
    }
}
