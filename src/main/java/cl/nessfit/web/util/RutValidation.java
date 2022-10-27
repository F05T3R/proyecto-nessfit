package cl.nessfit.web.util;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import cl.nessfit.web.model.Usuario;

public class RutValidation implements Validator {

	@Override
    public boolean supports(Class<?> clazz) {
	return Usuario.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
	Usuario usuario = (Usuario) target;

	// lógica para validar

	// errors.rejectValue("rut", null, "rut no válido");

    }
}
