package gr.uoa.di.m149_p2.controller;

import gr.uoa.di.m149_p2.error.CustomError;
import gr.uoa.di.m149_p2.models.queries.MostActiveUsers;
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
