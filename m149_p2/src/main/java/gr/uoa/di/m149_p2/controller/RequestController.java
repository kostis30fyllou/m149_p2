package gr.uoa.di.m149_p2.controller;

import gr.uoa.di.m149_p2.dto.NewIncident;
import gr.uoa.di.m149_p2.error.CustomError;
import gr.uoa.di.m149_p2.models.Request;
import gr.uoa.di.m149_p2.models.queries.AvgRequestCompletion;
import gr.uoa.di.m149_p2.models.queries.DailyRequests;
import gr.uoa.di.m149_p2.models.queries.LeastCommonWards;
import gr.uoa.di.m149_p2.models.queries.TotalTypeRequests;
import gr.uoa.di.m149_p2.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests/")
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
                return new ResponseEntity<>(error, error.getStatus());
            }
        } catch (Exception e) {
            CustomError error = new CustomError(e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(error, error.getStatus());
        }

    }

    @GetMapping(value = "/getTotalTypeRequests")
    public ResponseEntity<?> getTotalTypeRequests(@RequestParam String startDate, @RequestParam String endDate) {
        try {
            List<TotalTypeRequests> results = requestService.getTotalTypeRequests(startDate, endDate);
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            CustomError error = new CustomError(e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(error, error.getStatus());
        }

    }

    @GetMapping(value = "/getDailyRequests")
    public ResponseEntity<?> getDailyRequests(@RequestParam String type, @RequestParam String startDate, @RequestParam String endDate) {
        try {
            List<DailyRequests> results = requestService.getDailyRequests(type, startDate, endDate);
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            CustomError error = new CustomError(e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(error, error.getStatus());
        }

    }

    @GetMapping(value = "/getLeastCommonWards")
    public ResponseEntity<?> getLeastCommonWards(@RequestParam String type) {
        try {
            List<LeastCommonWards> results = requestService.getLeastCommonWards(type);
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            CustomError error = new CustomError(e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(error, error.getStatus());
        }

    }

    @GetMapping(value = "/getAvgRequestCompletion")
    public ResponseEntity<?> getAvgRequestCompletion(@RequestParam String startDate, @RequestParam String endDate) {
        try {
            List<AvgRequestCompletion> results = requestService.getAvgRequestCompletion(startDate, endDate);
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            CustomError error = new CustomError(e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(error, error.getStatus());
        }

    }
}


