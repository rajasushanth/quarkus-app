package org.starkinc.quarkus.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/hello")
public class GreetingController {

    @GetMapping
    public ResponseEntity<Object> hello() {
        var map = new HashMap<String, String>();
        map.put("message", "hello");
        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<Object> helloWithName(@PathVariable("name") String name) {
        var map = new HashMap<String, String>();
        map.put("message", "hello " + name);
        return ResponseEntity.ok(map);
    }
}