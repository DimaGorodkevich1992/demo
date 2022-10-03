package com.example.demo.endpoints.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class VoterDto {
    @NotNull
    private Long exIdentifier;
    @NotEmpty
    private String name;
}
