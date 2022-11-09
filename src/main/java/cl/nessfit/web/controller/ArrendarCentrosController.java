package cl.nessfit.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.nessfit.web.model.Usuario;
import cl.nessfit.web.service.CUsuarioService;

@Controller
public class ArrendarCentrosController {

	@Autowired
    CUsuarioService usuarioService;
	
	 @RequestMapping(value="/ArrendarCentro", method=RequestMethod.GET)
	    public String ArrendarCentro() {
	    	return "cliente/ArrendarCentros";
	    }
	 
	 @ModelAttribute("rutUser")
	    public String auth() {
	    	Usuario usuario = usuarioService.buscarPorRut(SecurityContextHolder.getContext().getAuthentication().getName());
	    	return usuario.getNombre();
	    }
	    
	    @ModelAttribute("rolUser")
	    public String rol() {
	    return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst().get()
	        .getAuthority();
	    }
}
