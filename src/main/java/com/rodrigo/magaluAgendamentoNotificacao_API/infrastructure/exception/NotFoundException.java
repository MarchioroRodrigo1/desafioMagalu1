package com.rodrigo.magaluAgendamentoNotificacao_API.infrastructure.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
