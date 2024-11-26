package vttp.batch5.ssf.day17_lecture.controllers;

import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@RestController
@RequestMapping
public class TimeController {

    @GetMapping(path="/time", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTime() {

        JsonObject resp = Json.createObjectBuilder()
            .add("time", (new Date()).toString())
            .build();

        return ResponseEntity.ok(resp.toString());
    }

    @GetMapping(path="/time", produces= MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getTimeAsText() {

        String time = (new Date().toString());

        return ResponseEntity.ok(time);
    }

    @PostMapping(
        path="/customer", 
        produces = MediaType.APPLICATION_JSON_VALUE, 
        consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postCustomer(
        @RequestBody String payload) 
    {
        
        return ResponseEntity.accepted()
            .header("X-MyHeader", "abc123")
            .body("{}");
    }

   // POST /customer
   // Content-Type: application/x-www-form-urlencoded
   // Accept: text/html
   @PostMapping(path="/customer", produces = MediaType.TEXT_HTML_VALUE
         , consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
   public ResponseEntity<String> postCustomerAsHTML (@RequestBody MultiValueMap<String, String> form) {

      System.out.printf(">>> form: %s\n", form);

      String html = "<h1> %s has been registered </h1>".formatted(form.getFirst("name"));
       
      return ResponseEntity.ok(html);
   }
 }
