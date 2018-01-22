package com.zozo.calculator.controller;

import com.zozo.calculator.model.Bignum;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/calculate")
@RestController
public class CalculatorController {

    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> multiply(@RequestBody String expression) {
        System.out.println(expression);
        String[] parts = expression.split("\\*");
        Bignum a = new Bignum(parts[0]);
        Bignum b = new Bignum(parts[1]);
        try {
            Bignum result = a.multiPly(b);
            return new ResponseEntity<>(result.toString(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Can not calculate", HttpStatus.OK);
    }
}
