package com.zozo.calculator.controller;

import com.zozo.calculator.model.Bignum;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;

@RequestMapping("/calculate")
@RestController
public class CalculatorController {

    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> multiply(@RequestBody String expression) {
        System.out.println(expression);
        String[] parts = expression.split("\\*");
        Bignum a = new Bignum(new BigDecimal(parts[0].toUpperCase()).toBigInteger().toString());
        Bignum b = new Bignum(new BigDecimal(parts[1].toUpperCase()).toBigInteger().toString());
        try {
            Bignum result = a.multiPly(b);
            return new ResponseEntity<>(this.formatResult(result.toString()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Can not calculate", HttpStatus.OK);
    }

    public static String formatResult(String bignum) {
        String formatedString = "";
        for (int i = 0; i < bignum.length(); i++) {
            formatedString+= bignum.charAt(i);
            if (i > 0 && i % 3 == 0) {
                formatedString += " ";
            }
        }
        return formatedString;
    }
}
