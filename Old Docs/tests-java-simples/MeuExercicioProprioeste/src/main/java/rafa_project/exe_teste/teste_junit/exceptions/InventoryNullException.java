package rafa_project.exe_teste.teste_junit.exceptions;

public class InventoryNullException extends Exception {
    private String message;

    public InventoryNullException(){

    }
    public InventoryNullException(String errorDescription) {
        this.message = errorDescription;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
