package cl.nessfit.web.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import cl.nessfit.web.model.InstalacionDeportiva;
import cl.nessfit.web.model.TipoInstalacion;
import cl.nessfit.web.service.CInstalacionDeportivaService;

@Component
public class ValidacionInstalacion implements Validator{

	@Autowired
	CInstalacionDeportivaService InstalacionDeportivaService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return InstalacionDeportiva.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		InstalacionDeportiva instalacion = (InstalacionDeportiva) target;
		
		InstalacionDeportiva existe = InstalacionDeportivaService.buscarPorNombre(instalacion.getNombre());
		if (existe != null) {
	    	errors.rejectValue("nombre", null, "La Instalacion ya existe en el sistema. ");
	    }
		
		if (instalacion.getNombre() == "") {
			errors.rejectValue("nombre", null, "Complete este campo ");
		}
		
		if (instalacion.getDireccion() == "") {
			errors.rejectValue("direccion", null, "Complete este campo ");
		}
		
		if(instalacion.getCostoArriendo() < 1000) {
			errors.rejectValue("costoArriendo", null, "El costo mínimo de arriendo debe ser $1.000 ");
		}
		
		if(instalacion.getEstado() != 1  && instalacion.getEstado() !=  0 ) {
			errors.rejectValue("estado", null, "Estado no válido");
		}
		
	}
	
}
