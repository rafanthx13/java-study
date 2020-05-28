package br.ce.wcaquino.exceptions;

public class FilmeSemEstoqueException extends Exception {

    private String message;

    public FilmeSemEstoqueException(String s) {
        this.message = s;
    }

    @Override
    public String getMessage(){
        return message;
    }
}

 /*
        Dessa forma, quando essa seção for lançada, eu sei que o
           motivo foi único, que foi por:
            FALTA DE ESTOQUE DO FILME

         Podemos expecificar que queremos encontrar essa exeçao por:
                @Test(excepected = FilmeSemEstoque.class)
            Assim, vai pegar da SUCCESS quando der essa EXEÇÂO
     */
