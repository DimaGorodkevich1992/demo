package com.example.demo.endpoints.rest;

import com.example.demo.endpoints.converter.CustomMapper;
import com.example.demo.endpoints.dto.CandidateDto;
import com.example.demo.endpoints.dto.CandidateResponseDto;
import com.example.demo.repositories.entities.Candidate;
import com.example.demo.services.CandidatesService;
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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RequestMapping(value = "/candidates")
@RestController
@RequiredArgsConstructor
public class CandidatesController {

    private final CandidatesService service;
    private final CustomMapper customMapper;

    @PostMapping
    public ResponseEntity<CandidateResponseDto> save(@RequestBody @Valid final CandidateDto candidateRequest) {
        var candidateResponse = service.save(customMapper.getMapper().map(candidateRequest, Candidate.class));
        return ResponseEntity.ok(customMapper.fullMapToCandidateResponse(candidateResponse));
    }

    @GetMapping("/{exIdentifier}")
    public ResponseEntity<CandidateResponseDto> getByExIdentifier(@PathVariable @NotNull final Long exIdentifier) {
        var candidateResponse = service.getByExIdentifier(exIdentifier);
        return ResponseEntity.ok(customMapper.fullMapToCandidateResponse(candidateResponse));
    }

    @GetMapping
    public ResponseEntity<Page<CandidateResponseDto>> findAll(@PageableDefault
                                                              @SortDefault.SortDefaults({
                                                                      @SortDefault(sort = "name", direction = Sort.Direction.ASC)
                                                              }) final Pageable pageable) {

        var candidatesResponse = service.findAll(pageable);
        return ResponseEntity.ok(candidatesResponse.map(customMapper::fullMapToCandidateResponse));
    }
}
