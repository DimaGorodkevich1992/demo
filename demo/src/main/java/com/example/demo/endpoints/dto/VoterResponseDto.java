package com.example.demo.endpoints.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class VoterResponseDto {
    private Long exIdentifier;
    private String name;
    private Boolean hasVote;
}
