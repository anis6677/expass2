package no.hvl.dat250.expass2.controllers;

import no.hvl.dat250.expass2.Manager.DomainManager;
import no.hvl.dat250.expass2.exceptions.Message;
import no.hvl.dat250.expass2.models.Poll;
import no.hvl.dat250.expass2.models.User;
import no.hvl.dat250.expass2.models.VoteOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    DomainManager domainManager;

    @GetMapping("/init")
    public ResponseEntity<Object> init() {
        User user = new User("Anita", "anita@gmail.com");
        VoteOption voteOption1 = new VoteOption("Øl", 10);
        VoteOption voteOption2 = new VoteOption("Pineapple vodka", 15);
        Poll poll =  new Poll("Hvilken drink er best på byen?", user, List.of(voteOption1, voteOption2) );
        user = domainManager.addUser(user);
        poll = domainManager.addPoll(poll);

        if(user == null || poll == null){
            //TODO
        }
        return new ResponseEntity<>(new Message("databasen er inisjiert"), HttpStatus.OK);

    }


    /*
    Http metode for å hente alle brukere
     */
    @GetMapping("/users")
    public ResponseEntity<Object> getUser() {
        Map<Integer, User> users = domainManager.getUsers();
        if(users.isEmpty()) {
            return new ResponseEntity<>(new Message("no users"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(List.copyOf(users.values()) , HttpStatus.OK);
    }
    /*
    http metode for å opprette et bruke-objekt med manageren
    opprette en Uri til objektet
    funksjonen returnerer uri-en og brukerobjektet
     */
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User created = domainManager.addUser(user);
        if(created == null) {
            return new ResponseEntity<>(new Message("could not create user"), HttpStatus.BAD_REQUEST);
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }




}
