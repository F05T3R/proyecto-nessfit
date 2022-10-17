package cl.nessfit.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.nessfit.web.service.CUsuarioService;

@Controller
public class InicioSesionController {

	@Autowired
    CUsuarioService usuarioService;
	
	@Autowired
    BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping(value = {"/InicioSesion",""}, method = RequestMethod.GET)
	public String InicioSesion() {
		return "InicioSesion";
	}
	
	@GetMapping("/logout")
    public String logout(HttpServletRequest request) {
	SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
	logoutHandler.logout(request, null, null);
	return "redirect:/";
    }
	
	@ModelAttribute("rolUser")
    public String rol() {
    return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst().get()
        .getAuthority();
    }
	
	
	
}
