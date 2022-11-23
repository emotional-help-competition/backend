package com.epam.emotionalhelp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HalloController {
    @GetMapping("/hallo")
    public String hallo(){
        return "Hallo World";
    }


}
