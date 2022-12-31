package com.example.exceptions;

public class UsernameDuplicatedException extends Exception {

    public UsernameDuplicatedException(String msg) {
        super(msg);
    }
}