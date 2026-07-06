package com.pramod.firstapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pramod.firstapi.dto.EmailRequest;
import com.pramod.firstapi.service.EmailService;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    public String sendEmail(
            @RequestBody EmailRequest request) {

        return emailService.sendEmail(
                request.getTo(),
                request.getSubject(),
                request.getMessage());
    }
}