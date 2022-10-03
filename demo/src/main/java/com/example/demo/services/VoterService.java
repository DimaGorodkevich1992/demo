package com.example.demo.services;

import com.example.demo.repositories.VoterRepository;
import com.example.demo.repositories.entities.Voter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class VoterService {

    private final VoterRepository repository;

    public Voter save(final Voter voter) {
        return repository.save(voter);
    }

    public Voter getByExIdentifier(final Long exIdentifier) {
        return repository.findByExIdentifier(exIdentifier);
    }

    public Page<Voter> findAll(final Pageable pageableWithSortedByName, final Boolean hasVote) {
        if (Objects.isNull(hasVote)) {
            return repository.findAll(pageableWithSortedByName);
        }

        return hasVote
                ? repository.findByCandidateIsNotNull(pageableWithSortedByName)
                : repository.findByCandidateIsNull(pageableWithSortedByName);
    }
}
