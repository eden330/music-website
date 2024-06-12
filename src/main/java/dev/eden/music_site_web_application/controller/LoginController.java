package dev.eden.music_site_web_application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/music")
public class LoginController {
    @GetMapping("/showMyLoginPage")
    public String showMyLoginPanel() {
        return "pages-for-album/login-panel";
    }
}
