package com.example.demo.endpoints.converter;

import com.example.demo.endpoints.dto.CandidateResponseDto;
import com.example.demo.endpoints.dto.VoterResponseDto;
import com.example.demo.repositories.entities.Candidate;
import com.example.demo.repositories.entities.Voter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Component
@Getter
@Setter
@RequiredArgsConstructor
public class CustomMapper {
    private final Mapper mapper;

    @Transactional
    public VoterResponseDto fullMapToVoterResponse(final Voter voter) {
        return VoterResponseDto.of(voter.getExIdentifier(), voter.getName(), Objects.nonNull(voter.getCandidate()));
    }

    public CandidateResponseDto fullMapToCandidateResponse(final Candidate candidate) {
        return CandidateResponseDto.of(candidate.getExIdentifier(),
                candidate.getName(),
                Objects.isNull(candidate.getVoters()) ? 0 : candidate.getVoters().size());
    }
}
