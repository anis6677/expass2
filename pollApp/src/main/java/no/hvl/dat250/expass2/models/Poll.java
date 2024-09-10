package no.hvl.dat250.expass2.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;


@Getter
@Setter
public class Poll {



    public Poll(String question, User creater, List<VoteOption> muligheter){
        this.question = question;
        this.creator = creater;
        this.publishedAt = Instant.now();
        this.validUntil = Instant.MAX;
        this.voteOptions = muligheter;
    }

    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String question;

    private Instant publishedAt;

    private Instant validUntil;

    @JsonIgnore
    private User creator;

    private List<VoteOption> voteOptions;

}
