package com.example.demo.endpoints.handlers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ValidationResponse<T> implements Serializable {
    private static final long serialVersionUID = 639505685377147571L;

    private String fieldName;
    private T fieldValue;
    private String message;
}
