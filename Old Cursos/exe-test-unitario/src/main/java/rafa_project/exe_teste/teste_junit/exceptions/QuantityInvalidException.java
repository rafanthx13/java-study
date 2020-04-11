package rafa_project.exe_teste.teste_junit.exceptions;

public class QuantityInvalidException extends Exception{

    private String message;

    public QuantityInvalidException(String errorDescription) {
        this.message = errorDescription;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
