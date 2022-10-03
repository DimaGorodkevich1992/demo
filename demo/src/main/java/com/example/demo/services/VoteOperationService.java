package com.example.demo.services;


import com.example.demo.repositories.CandidateRepository;
import com.example.demo.repositories.VoterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class VoteOperationService {

    private final VoterRepository voterRepository;
    private final CandidateRepository candidateRepository;

    @Transactional
    public void apply(final Long voterExIdentifier, final Long candidateExIdentifier) {
        var voter = voterRepository.findByExIdentifier(voterExIdentifier);

        if (Objects.isNull(voter)) {
            throw new EntityNotFoundException(String.format("Voter - %s doesn't exist ", voterExIdentifier));
        }

        if (Objects.nonNull(voter.getCandidate())) {
            throw new DataIntegrityViolationException(String.format("Voter - %s has been voting ", voterExIdentifier));
        }

        var candidate = candidateRepository.findByExIdentifier(candidateExIdentifier);
        if (Objects.isNull(candidate)) {
            throw new EntityNotFoundException(String.format("Candidate - %s doesn't exist ", candidateExIdentifier));
        }
        voter.setCandidate(candidate);

    }
}
