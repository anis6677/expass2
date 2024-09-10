package no.hvl.dat250.expass2.controllers;


import no.hvl.dat250.expass2.Manager.DomainManager;
import no.hvl.dat250.expass2.exceptions.Message;
import no.hvl.dat250.expass2.models.Poll;
import no.hvl.dat250.expass2.models.VoteOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/vi")
public class PollController {

    @Autowired
    DomainManager domainManager;

    @GetMapping("/polls")
    public ResponseEntity<Object> getPolls(){
        Map<Integer, Poll> polls = domainManager.getPolls();
        if(polls == null){
            return new ResponseEntity<>(new Message("Det finnes ingen polls"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(polls, HttpStatus.OK);
    }
    @PostMapping("/polls/{id}")
    public ResponseEntity<Object> createPoll(@PathVariable("id") int id, @RequestBody Poll poll){
        /*
        TODO- legge til poll for brukeren også
         */
        Poll added = domainManager.addPoll(poll);
        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }


    @DeleteMapping("/polls/{id}")
    public ResponseEntity<Object> deletePoll(@PathVariable("id") int id){
        Poll deleted = domainManager.getPolls().get(id);
        if(deleted == null){
            return new ResponseEntity<>(new Message("Det finnes ingen poll"), HttpStatus.NOT_FOUND);
        }
        List<VoteOption> muligheter = deleted.getVoteOptions();
        /*
        TODO- slette stemmer fra alle brukere
         */

        domainManager.getPolls().remove(id);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }


}
