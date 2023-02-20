package ru.kata.restApp.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UsersErrorResponse {
    private String message;
    private Long timeStamp;
}
