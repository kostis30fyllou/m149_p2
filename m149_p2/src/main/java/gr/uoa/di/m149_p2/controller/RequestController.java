package gr.uoa.di.m149_p2.controller;

import gr.uoa.di.m149_p2.dto.GeoPass;
import gr.uoa.di.m149_p2.dto.NewIncident;
import gr.uoa.di.m149_p2.error.CustomError;
import gr.uoa.di.m149_p2.models.Request;
import gr.uoa.di.m149_p2.models.queries.*;
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

    @GetMapping(value = "/getMostCommonTypes")
    public ResponseEntity<?> getMostCommonTypes(@RequestParam String date) {
        try {
            List<MostCommonTypes> results = requestService.getMostCommonTypes(date);
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

    @GetMapping(value = "/getMostCommonRequest")
    public ResponseEntity<?> getMostCommonRequest(@RequestBody GeoPass gp) {
        try {
            TotalTypeRequests result = requestService.getMostCommonRequest(gp.getDate(), gp.getP1(), gp.getP2());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            CustomError error = new CustomError(e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(error, error.getStatus());
        }

    }

    @GetMapping(value = "/getMostVotedRequests")
    public ResponseEntity<?> getMostVotedRequests(@RequestParam String date) {
        try {
            List<MostVotedRequests> results = requestService.getMostVotedRequests(date);
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            CustomError error = new CustomError(e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(error, error.getStatus());
        }
    }

}


