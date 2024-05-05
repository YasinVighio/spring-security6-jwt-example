package com.jwtexample.springsecurity6jwtexample.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtAuthResponse {
    private boolean isSuccessful;
    private String token;
}
