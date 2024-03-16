package com.chiru.springsecurity.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Chiranjeevi
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAuthDTO {
    private String userName;
    private String password;
}
