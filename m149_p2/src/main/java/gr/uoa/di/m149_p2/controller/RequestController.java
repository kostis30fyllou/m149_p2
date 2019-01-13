package gr.uoa.di.m149_p2.controller;

import gr.uoa.di.m149_p2.dto.NewIncident;
import gr.uoa.di.m149_p2.models.Request;
import gr.uoa.di.m149_p2.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/save/")
public class RequestController {

    @Autowired
    RequestService requestService;

    @PostMapping(value = "/newIncident")
    public ResponseEntity<?> newIncident(@RequestBody NewIncident newIncident) {
        try {
            Request request = requestService.addRequest(newIncident);
            if(request != null) {
                return new ResponseEntity<>(request, HttpStatus.OK);
            }
            else return new ResponseEntity<>(Object.class, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(Object.class, HttpStatus.BAD_REQUEST);
        }

    }
}


