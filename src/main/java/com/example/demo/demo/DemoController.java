package com.example.demo.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {
    @GetMapping("/greet/admin")
    public ResponseEntity<String> greetAdmin() {
        return ResponseEntity.ok("Hello, Admin!");
    }

    @GetMapping("/greetVisitor")
    public ResponseEntity<String> greet() {
        return ResponseEntity.ok("Hello, Visitor!");
    }

    @GetMapping("/greet/adminOrUser")
    public ResponseEntity<String> greetUser() {
        return ResponseEntity.ok("Hello, User Or Admin!");
    }
}
