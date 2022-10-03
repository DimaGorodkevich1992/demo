package com.example.demo.endpoints.rest;


import com.example.demo.endpoints.dto.VoteOperationDto;
import com.example.demo.services.VoteOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RequestMapping(value = "/vote")
@RestController
@RequiredArgsConstructor
public class VoteOperationController {

    private final VoteOperationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid final VoteOperationDto voteOperation) {
        service.apply(voteOperation.getVoterExIdentifier(), voteOperation.getCandidateExIdentifier());
    }
}
