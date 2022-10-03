package com.example.demo.repositories;

import com.example.demo.repositories.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

    Candidate findByExIdentifier(Long exIdentifier);
}
