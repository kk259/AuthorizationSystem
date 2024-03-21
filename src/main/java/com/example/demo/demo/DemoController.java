package com.example.demo.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {
/*
    @GetMapping("/adminOrManager/sayHello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Admin or manager says hello");
    }

    @GetMapping("/sayHello")
    public ResponseEntity<String> sayHelloToEveryone() {
        return ResponseEntity.ok("Hello to everyone");
    }

    @PostMapping("/admin/adminSaysHelloTo")
    public ResponseEntity<String> sayHelloTo(@RequestParam String name) {
        return ResponseEntity.ok("Admin says hello to " + name);
    }
*/
    @GetMapping("/greet/admin")
    public ResponseEntity<String> greetAdmin() {
        return ResponseEntity.ok("Hello, Admin!");
    }

    // Generic greeting endpoint for all users
    @GetMapping("/greetVisitor")
    public ResponseEntity<String> greet() {
        return ResponseEntity.ok("Hello, Visitor!");
    }

    // Endpoint for Admin to greet a specific user
    @GetMapping("/greet/adminOrUser")
    public ResponseEntity<String> greetUser() {
        return ResponseEntity.ok("Hello, User Or Admin!");
    }
}
