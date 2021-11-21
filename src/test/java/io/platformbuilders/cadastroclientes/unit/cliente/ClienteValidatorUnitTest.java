package io.platformbuilders.cadastroclientes.unit.cliente;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.platformbuilders.cadastroclientes.api.v1.model.CreateClienteDTO;
import io.platformbuilders.cadastroclientes.api.v1.model.UpdateClienteDTO;
import io.platformbuilders.cadastroclientes.core.BaseUnitTest;
import io.platformbuilders.cadastroclientes.utils.TestConstants;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClienteValidatorUnitTest extends BaseUnitTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidateWhenCreateClienteDtoIsValid() {
        CreateClienteDTO createClienteDTO = CreateClienteDTO.builder()
            .nome(TestConstants.DEFAULT_CLIENTE_NOME).build();

        Set<ConstraintViolation<CreateClienteDTO>> violations = validator.validate(createClienteDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testValidateWhenCreateClienteDtoNomeIsMissing() {
        CreateClienteDTO createClienteDTO = CreateClienteDTO.builder()
            .nome("").build();

        Set<ConstraintViolation<CreateClienteDTO>> violations = validator.validate(createClienteDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testValidateWhenCreateClienteDtoNomeIsNull() {
        CreateClienteDTO createClienteDTO = CreateClienteDTO.builder()
            .nome(null).build();

        Set<ConstraintViolation<CreateClienteDTO>> violations = validator.validate(createClienteDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testValidateWhenUpdateClienteDtoIsValid() {
        UpdateClienteDTO updateClienteDTO = UpdateClienteDTO.builder()
            .nome(TestConstants.DEFAULT_CLIENTE_NOME).build();

        Set<ConstraintViolation<UpdateClienteDTO>> violations = validator.validate(updateClienteDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testValidateWhenUpdateClienteDtoNomeIsMissing() {
        UpdateClienteDTO updateClienteDTO = UpdateClienteDTO.builder()
            .nome("").build();

        Set<ConstraintViolation<UpdateClienteDTO>> violations = validator.validate(updateClienteDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testValidateWhenUpdateClienteDtoNomeIsNull() {
        UpdateClienteDTO updateClienteDTO = UpdateClienteDTO.builder()
            .nome(null).build();

        Set<ConstraintViolation<UpdateClienteDTO>> violations = validator.validate(updateClienteDTO);
        assertFalse(violations.isEmpty());
    }

}
