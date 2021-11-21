package io.platformbuilders.cadastroclientes.domain.exceptions;

public class ClienteNotFoundException extends EntityNotFoundException {

    public ClienteNotFoundException(String message) {
        super(message);
    }

    public ClienteNotFoundException(Long id) {
        this(String.format("Não existe um cadastro de cliente com código %d", id));
    }
}
