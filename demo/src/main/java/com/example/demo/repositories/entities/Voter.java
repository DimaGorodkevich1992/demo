package com.example.demo.repositories.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long exIdentifier;
    private String name;
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
}
