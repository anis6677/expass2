package no.hvl.dat250.expass2.Manager;


import no.hvl.dat250.expass2.models.Poll;
import no.hvl.dat250.expass2.models.User;
import no.hvl.dat250.expass2.models.Vote;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
Component som jobber med dataen som DomainManager holder på

 */

@Component
public class DomainManager {


    private HashMap<Integer, Poll> polls;

    private HashMap<Integer, User> users;

    public DomainManager() {
        polls = new HashMap<>();
        users = new HashMap<>();
    }
    /*
    Definerer en funksjon med navn addUser
    som skal returnere et User-objekt
    og som tar inn et user-objekt som argument
     */
    public User addUser(User user) {
        if(users.containsValue(user)) {
            return null;
        }
        users.put(user.getId(), user);
        return user;
    }

    public User removeUser(User user) {
        if(users.containsValue(user)) {
           users.remove(user.getId());
        }
        return user;
    }

    public Poll addPoll(Poll poll) {
        User creator = users.get(poll.getCreator().getId());
        if(creator == null) {
            return null;
        }
        creator.getPolls().add(poll);
        polls.put(poll.getId(), poll);
        return poll;

    }

    public Poll removePoll(Poll poll) {
        Poll deletedPoll = polls.remove(poll.getId());
        if(deletedPoll != null) {
            deletedPoll.getCreator().getPolls().remove(poll);
        }
        return deletedPoll;
    }

    public User updateUser(int id, User newUser) {
        User updatedUser = users.get(id);
        if(updatedUser == null) {
            return null;
        }
        updatedUser.setEmail(newUser.getEmail());
        updatedUser.setUsername(newUser.getUsername());
        return updatedUser;
    }


    public User updatedCastedVotes(int id, List<Vote> votes){
        User updatedUser = users.get(id);
        if(updatedUser == null) {
            return null;
        }
        updatedUser.setVotes(votes);
        return updatedUser;
    }

    public Vote castVote(Vote vote) {
        Vote casted = new Vote(vote.getCaster(), vote.getOption());
        casted.getCaster().getVotes().add(casted);
        /*
        oppdater antall stemmer for dette alternativet
         */
        vote.getOption().castVote();
        return casted;
    }

    /*
    slette stemmen fra stemme listen til en bruker
    slette antall stemmer fra valgmulighetem til selve stemmen
     */
    public Vote removeVote(Vote vote) {
        if(!users.get(vote.getCaster().getId()).getVotes().remove(vote)){
         return null;
        }
        vote.getOption().removeVote();
        return vote;
    }


    public Map<Integer, User> getUsers(){
        return users;
    }


    public Map<Integer, Poll> getPolls(){
        return polls;
    }

}
