package no.hvl.dat250.expass2.controllers;

import no.hvl.dat250.expass2.Manager.DomainManager;
import no.hvl.dat250.expass2.exceptions.Message;
import no.hvl.dat250.expass2.models.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/vi")
public class VoteController {

    @Autowired
    DomainManager domainManager;

    @GetMapping("/polls")
    public ResponseEntity<Object> getVotes(){
        List<Vote> allVotes = new ArrayList<>();
        domainManager.getUsers().values().stream().flatMap(v -> v.getVotes().stream()).forEach(v -> allVotes.add(v));

        if(allVotes.isEmpty()){
            return new ResponseEntity<>(new Message("ingen stemmer funnet"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allVotes, HttpStatus.OK);

    }
    @PostMapping("/polls/{id}")
    public ResponseEntity<Object> createPoll(@PathVariable("id") int id){
        return null;
    }


    @PutMapping("/polls/{id}")
    public ResponseEntity<Object> updateVotes(@PathVariable("id") int id, @RequestBody Vote vote ){
        Vote old = domainManager.getUsers().values().stream().flatMap(v -> v.getVotes().stream()).filter(v -> v.getId() == id).findFirst().orElseThrow( () -> new NoSuchElementException("ingen element funnet") );
        old.getOption().removeVote();
        vote.getOption().castVote();
        return new ResponseEntity<>(old, HttpStatus.OK);
    }
}
