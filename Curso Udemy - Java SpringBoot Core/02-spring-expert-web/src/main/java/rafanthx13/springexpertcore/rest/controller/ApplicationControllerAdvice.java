package rafanthx13.springexpertcore.rest.controller;

import rafanthx13.springexpertcore.exception.PedidoNaoEncontradoException;
import rafanthx13.springexpertcore.exception.RegraNegocioException;
import rafanthx13.springexpertcore.rest.ApiErrors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice // COnsiguimos gerenciar todos os controllers
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class) // captura RegraNegocioException
    @ResponseStatus(HttpStatus.BAD_REQUEST) // vai retornar 404
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNotFoundException( PedidoNaoEncontradoException ex ){
        return new ApiErrors(ex.getMessage());
    }

}