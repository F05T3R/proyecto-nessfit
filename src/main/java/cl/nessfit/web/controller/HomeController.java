package cl.nessfit.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.nessfit.web.model.Solicitud;
import cl.nessfit.web.model.Usuario;
import cl.nessfit.web.service.CSolicitudService;
import cl.nessfit.web.service.CUsuarioService;

@Controller
public class HomeController {

	
	@Autowired
    CUsuarioService usuarioService;
	
	@Autowired
	CSolicitudService solicitudService;
	
	@Autowired
    BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/MenuPrincipal")
	public String MenuPrincipal() {
		return "MenuPrincipal";
	}
	
	@RequestMapping("/listarSolicitudes")
	public String listar(Model model) {
		Usuario usuario = usuarioService.buscarPorRut(SecurityContextHolder.getContext().getAuthentication().getName());
		List<Solicitud> listaSol = solicitudService.buscarRut(usuario.getRut());
		model.addAttribute("lista", listaSol);
		return "cliente/listarSolicitudes";
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
