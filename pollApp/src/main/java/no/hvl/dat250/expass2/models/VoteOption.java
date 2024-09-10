package no.hvl.dat250.expass2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class VoteOption {


    public VoteOption(String caption, int numOfVotes) {
        this.caption = caption;
        this.numOfVotes = numOfVotes;
    }

    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String caption;
    private int presentationOrder;
    private int numOfVotes;

    public void castVote(){
        //numberOfVotes + 1
        numOfVotes++;
    }

    public void removeVote(){
        //numberOfVotes -1
        numOfVotes--;
    }


}
