package br.ce.wcaquino.exceptions;

public class LocadoraException extends Exception {

    private String message;

    public LocadoraException(String s) {
        this.message = s;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
