package com.zozo.calculator.controller;

import com.zozo.calculator.model.Bignum;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class MainController {

    @RequestMapping("/")
    public String multiply(Model model) {
        model.addAttribute("name", "");
        return "index.html";
    }
}
