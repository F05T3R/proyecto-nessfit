package cl.nessfit.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.nessfit.web.model.InstalacionDeportiva;
import cl.nessfit.web.service.CInstalacionDeportivaService;

@Controller
@RequestMapping(value="/administrativo")
public class RegistrarInstalacionDeportivaController {
	
	@Autowired
	CInstalacionDeportivaService InstalacionDeportivaService;
	
	@RequestMapping(value="/RegistarInstalacionDeportiva", method=RequestMethod.GET)
    public String RegistrarInstalacionDeportiva(InstalacionDeportiva Instalacion) {
    	return "administrativo/RegistarInstalacionDeportiva";
    }
	
	@RequestMapping(value="/RegistarInstalacionDeportiva", method=RequestMethod.POST)
	public String formCrearInstalacion(@Valid InstalacionDeportiva instalacion, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
		    return "/administrativo/MenuPrincipal";
		}
		
		instalacion.setEstado(1);
		
		InstalacionDeportivaService.guardar(instalacion);
		
		
		return "redirect:RegistarInstalacionDeportiva";
	}
	
}
