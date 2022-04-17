package br.com.hroauth.exception;

public class FindUserByEmailException extends RuntimeException {

    public FindUserByEmailException (String message) {
        super(message);
    }
}