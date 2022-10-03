package com.example.demo.repositories;

import com.example.demo.repositories.entities.Voter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VoterRepository extends JpaRepository<Voter, Integer> {

    Voter findByExIdentifier(Long exIdentifier);

    Page<Voter> findByCandidateIsNull(Pageable pageable);

    Page<Voter> findByCandidateIsNotNull(Pageable pageable);
}
