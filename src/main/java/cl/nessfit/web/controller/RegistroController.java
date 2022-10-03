package cl.nessfit.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.nessfit.web.model.Usuario;
import cl.nessfit.web.service.CUsuarioService;
import cl.nessfit.web.util.RutValidation;
import cl.nessfit.web.model.Rol;

@RequestMapping("/RegistroCliente")
public class RegistroController {
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
    private CUsuarioService usuarioService;
	
	@Autowired
    private RutValidation validation;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
	binder.addValidators(validation);
    }
	
	@GetMapping("/Vista")
	public String RegistroForm(Usuario usuario) {
		return "Registrar";
	}
	
	@PostMapping("/RegistroGuardado")
	public String registrarForm(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
	        return "Registrar";
	    }
		
		usuario.setContrasena(passwordEncoder.encode(usuario.getRut()));
		usuario.setEstado(1);
		Rol rolCliente = new Rol();
		rolCliente.setId(3);
		usuario.setRol(rolCliente);
		System.out.println(usuario.toString());
		
		usuarioService.guardar(usuario);
		
		return "redirect:/Registrar";
	}
	
	@ModelAttribute("rutUser")
    public String auth() {
	// Usuario usuario =
	// usuarioService.buscarPorRut(SecurityContextHolder.getContext().getAuthentication().getName());

	return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
