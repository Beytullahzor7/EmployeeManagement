package com.example.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class _06_JwtRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;

    private String userName;
    private String password;
}
