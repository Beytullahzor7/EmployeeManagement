package com.example.security.jwt;

import java.io.Serializable;

public class _07_JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;

    private final String jwtToken;

    public _07_JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return this.jwtToken;
    }
}
