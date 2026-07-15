package br.org.edu.ifrn.lojacarro.exceptions;

public class CarroNaoEncontradoException extends RuntimeException {

    public CarroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}