package gr.uoa.di.m149_p2.controller;

import gr.uoa.di.m149_p2.error.CustomError;
import gr.uoa.di.m149_p2.models.queries.MostActiveUsers;
import gr.uoa.di.m149_p2.models.queries.TopUsersByWards;
import gr.uoa.di.m149_p2.models.queries.VotedWards;
import gr.uoa.di.m149_p2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/")
public class UserController {

    @Autowired
    UserService userService;


    /**
     *
     * Up vote a request, which is not already up voted by the same user
     *
     * @param name
     * @param telephone
     * @param id
     * @return
     */
    @PostMapping(value = "/upVoteRequest")
    public ResponseEntity<?> upVoteRequest(@RequestParam String name, @RequestParam String telephone, @RequestParam long id) {
        if(userService.upVoteRequest(name, telephone, id)) {
            return new ResponseEntity<>(new CustomError("Up vote confirmed", HttpStatus.OK), HttpStatus.OK);
        }
        else return new ResponseEntity<>(new CustomError("Already up voted", HttpStatus.NOT_ACCEPTABLE), HttpStatus.NOT_ACCEPTABLE);
    }


    /**
     *  8th query: Returns the fifty most active citizens, with regard to the total number of up votes.
     *
     * @return
     */
    @GetMapping(value = "/getMostActiveUsers")
    public ResponseEntity<?> getMostActiveUsers() {
        try {
            List<MostActiveUsers> results = userService.getMostActiveUsers();
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            CustomError error = new CustomError(e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(error, error.getStatus());
        }
    }

    /**
     * 9th query: Returns the top fifty citizens, with regard to the total number of wards for which they have
     * up voted an incidents.
     *
     * @return
     */
    @GetMapping(value = "/getTopUsersByWards")
    public ResponseEntity<?> getTopUsersByWards() {
        try {
            List<TopUsersByWards> results = userService.getTopUsersByWards();
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            CustomError error = new CustomError(e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(error, error.getStatus());
        }
    }

    /**
     * 10th query: Returns all incident ids for which the same telephone number has been used for more than one
     * names.
     *
     *  @return
     */
    @GetMapping(value = "/getIdsWithCommonPhone")
    public ResponseEntity<?> getIdsWithCommonPhone(){
        try{
            List<Long> results = userService.getUpVotedFromMultiTelephones();
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            CustomError error = new CustomError(e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(error, error.getStatus());
        }
    }

    /**
     * 11th query: Returns all the wards in which a given name has casted a vote for an incident taking place in it.
     *
     * @param name
     * @return
     */
    @GetMapping(value = "/getVotedWards")
    public ResponseEntity<?> getVotedWards(@RequestParam String name) {
        try{
            List<VotedWards> results = userService.getVotedWards(name);
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            CustomError error = new CustomError(e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(error, error.getStatus());
        }
    }

}
