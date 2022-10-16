package cl.nessfit.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import cl.nessfit.web.model.Usuario;
import cl.nessfit.web.service.CUsuarioService;

@Controller
public class HomeController {

	
	@Autowired
    CUsuarioService usuarioService;
	
	@Autowired
    BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/MenuPrincipal")
	public String MenuPrincipal() {
		return "MenuPrincipal";
	}
	
	@ModelAttribute("rolUser")
    public String rol() {
    return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst().get()
        .getAuthority();
    }
	@ModelAttribute("rutUser")
    public String auth() {
    	Usuario usuario = usuarioService.buscarPorRut(SecurityContextHolder.getContext().getAuthentication().getName());
    	return usuario.getNombre();
    }
}
