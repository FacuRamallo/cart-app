package com.backend.cartapp.domain.exceptions;

public class InvalidDescriptionException extends Throwable {
    public InvalidDescriptionException() {
        super("Product description must contain only alphanumerical characters");
    }
}
