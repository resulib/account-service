package com.resul.accountservice.exception;

public class AccountNotFoundException extends NotFoundException{
    public AccountNotFoundException(String message) {
        super(message);
    }
}
