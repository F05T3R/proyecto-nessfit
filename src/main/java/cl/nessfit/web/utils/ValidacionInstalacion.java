package cl.nessfit.web.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import cl.nessfit.web.model.InstalacionDeportiva;

import cl.nessfit.web.service.CInstalacionDeportivaService;

public class ValidacionInstalacion implements Validator{

	@Autowired
	CInstalacionDeportivaService InstalacionDeportiva;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		InstalacionDeportiva instalacion = (InstalacionDeportiva) target;
		
	}
	
}
