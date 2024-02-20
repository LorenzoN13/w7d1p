package it.epicode.w7d1p.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException() {}
    public BadRequestException(String message) {
        super(message);
    }
}
