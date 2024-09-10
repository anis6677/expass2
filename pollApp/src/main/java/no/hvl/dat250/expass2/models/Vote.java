package no.hvl.dat250.expass2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;



@Getter
@Setter

public class Vote {
    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    private Instant publishedAt;

    private User caster;

    private VoteOption option;

    public Vote(User caster, VoteOption option) {
        this.caster = caster;
        this.publishedAt = Instant.now();
        this.option = option;
    }
}

