package br.ce.wcaquino.exceptions;

public class FilmeInexistenteException extends Exception {

    private String message;

    public FilmeInexistenteException(String s) {
        this.message = s;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
