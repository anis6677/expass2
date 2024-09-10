package no.hvl.dat250.expass2.models;


import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class User {


    public User(String username, String email){
        this.username = username;
        this.email = email;
    }


    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String email;

    @Nullable
    @OneToMany(mappedBy = "poll")
    private List<Poll> polls;

    @Nullable
    @OneToMany(mappedBy = "vote")
    private List<Vote> votes;
}
