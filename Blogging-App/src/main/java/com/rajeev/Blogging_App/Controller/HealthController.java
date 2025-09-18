package com.rajeev.Blogging_App.Controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@Tag(name = "Comment API's")
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<String> checkHealth(){
        return new ResponseEntity<>("health is good" , HttpStatus.OK);
    }
}
