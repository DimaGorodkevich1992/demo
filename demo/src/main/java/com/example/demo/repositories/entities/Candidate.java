package com.example.demo.repositories.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long exIdentifier;
    private String name;

    @OneToMany(mappedBy = "candidate")
    private List<Voter> voters;
}
