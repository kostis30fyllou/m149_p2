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


    /**
     * Add a new incident request to request collection.
     *
     * @param newIncident
     * @return
     */
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

    /**
     * 1st query: Returns the total requests per type that were created within a specified time range and sort
     * them in a descending order.
     *
     * @param startDate
     * @param endDate
     * @return
     */
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


    /**
     * 2nd query: Returns the number of total requests per day for a specified request type and time range.
     *
     * @param type
     * @param startDate
     * @param endDate
     * @return
     */
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

    /**
     * 3rd query: Returns the three most common service requests per zip code for a specified day.
     *
     * @param date
     * @return
     */
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

    /**
     * 4th query: Returns the three least common wards with regards to a given service request type.
     *
     * @param type
     * @return
     */
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

    /**
     * 5th query: Returns the average completion time per service request for a specified date range.
     *
     * @param startDate
     * @param endDate
     * @return
     */
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


    /**
     * 6th query: Returns the most common service request in a specified bounding box for a specified day.
     *
     * @param gp
     * @return
     */
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


    /**
     * 7th query: Returns the fifty most up voted service requests for a specified day.
     *
     * @param date
     * @return
     */
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


