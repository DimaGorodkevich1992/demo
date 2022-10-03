package com.example.demo.endpoints.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class VoteOperationDto {
    private Long voterExIdentifier;
    private Long candidateExIdentifier;

}
