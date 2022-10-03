package com.example.demo.endpoints.handlers;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse implements Serializable {
    private static final long serialVersionUID = 5275468177120416035L;

    @JsonAlias({"error_description", "message"})
    private String message;

    private int code;

    private List<ValidationResponse> fields;

    private String status;

    public static ErrorResponse of(final String message, final int code, final String status) {
        var errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setCode(code);
        errorResponse.setStatus(status);
        return errorResponse;
    }
}
