package gr.uoa.di.m149_p2.controller;

import gr.uoa.di.m149_p2.dto.NewIncident;
import gr.uoa.di.m149_p2.error.CustomError;
import gr.uoa.di.m149_p2.models.Request;
import gr.uoa.di.m149_p2.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            else {
                CustomError error = new CustomError("Cannot find this type of request", HttpStatus.NOT_ACCEPTABLE);
                return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            CustomError error = new CustomError(e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

    }


}


