package com.google.demologinpage.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class User {
    @Id
    @NotEmpty(message = "Email Field is Not Empty")
    @NotNull(message = "Email Field is Not Empty")
    private String email;
    @NotNull(message = "password Field is notEmpty")
    @NotEmpty(message = "password Field is notEmpty")
    private String password;
    @NotNull(message = "name Field is notEmpty")
    @NotEmpty(message = "name Field is notEmpty")
    private String name;
    @NotNull(message = "Family Field is notEmpty")
    @NotEmpty(message = "Family Filed is notEmpty")
    private String family;
    @NotNull(message = "number Field is notEmpty")
    @NotEmpty(message = "number Field is notEmpty")
    private String number;
}
