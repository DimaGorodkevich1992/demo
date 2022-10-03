package com.example.demo.endpoints.rest;


import com.example.demo.endpoints.converter.CustomMapper;
import com.example.demo.endpoints.dto.VoterDto;
import com.example.demo.endpoints.dto.VoterResponseDto;
import com.example.demo.repositories.entities.Voter;
import com.example.demo.services.VoterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RequestMapping(value = "/voters")
@RestController
@RequiredArgsConstructor
public class VoterController {

    private final VoterService service;
    private final CustomMapper customMapper;

    @PostMapping
    public ResponseEntity<VoterResponseDto> save(@RequestBody @Valid final VoterDto voterRequest) {
        var voterResponse = service.save(customMapper.getMapper().map(voterRequest, Voter.class));
        return ResponseEntity.ok(customMapper.fullMapToVoterResponse(voterResponse));
    }

    @GetMapping("/{exIdentifier}")
    public ResponseEntity<VoterResponseDto> getByExIdentifier(@PathVariable final @NotNull Long exIdentifier) {
        var voterResponse = service.getByExIdentifier(exIdentifier);
        return ResponseEntity.ok(customMapper.fullMapToVoterResponse(voterResponse));
    }

    @GetMapping
    public ResponseEntity<Page<VoterResponseDto>> findAll(@PageableDefault
                                                          @SortDefault.SortDefaults({
                                                                  @SortDefault(sort = "name", direction = Sort.Direction.ASC)
                                                          }) final Pageable pageable,
                                                          final @RequestParam(value = "hasVote", required = false) Boolean hasVote) {
        var voterResponse = service.findAll(pageable, hasVote);
        return ResponseEntity.ok(voterResponse.map(customMapper::fullMapToVoterResponse));
    }
}
