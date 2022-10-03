package com.example.demo.services;

import com.example.demo.repositories.CandidateRepository;
import com.example.demo.repositories.entities.Candidate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CandidatesService {

    private final CandidateRepository repository;

    public Candidate save(final Candidate candidate) {
        return repository.save(candidate);
    }

    public Candidate getByExIdentifier(final Long exIdentifier) {
        return repository.findByExIdentifier(exIdentifier);
    }

    public Page<Candidate> findAll(final Pageable pageableWithSortedByName) {
        return repository.findAll(pageableWithSortedByName);
    }
}
