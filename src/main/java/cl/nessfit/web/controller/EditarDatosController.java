package cl.nessfit.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.nessfit.web.model.Rol;
import cl.nessfit.web.model.Usuario;
import cl.nessfit.web.service.CUsuarioService;

@Controller
public class EditarDatosController {

	@Autowired
    CUsuarioService usuarioService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
	
    @GetMapping("/EditarDatos")
    public String Editar(Model model) {
	Usuario usuario = usuarioService.buscarPorRut(SecurityContextHolder.getContext().getAuthentication().getName());
	model.addAttribute("usuario", usuario);
	return "EditarDatos";
    }

    @PostMapping("/EditarDatos")
    public String perfilForm(@Valid Usuario usuario, BindingResult result, Model model) {

	Usuario usuarioAuth = usuarioService
		.buscarPorRut(SecurityContextHolder.getContext().getAuthentication().getName());

	// Paso 1.-Validaciones
	Usuario existe = usuarioService.buscarPorEmail(usuario.getEmail());

    if (existe != null && !(existe.equals(usuarioAuth))) {
    	result.rejectValue("email", null, "El correo electrónico ya existe en el sistema");
    }
	
	if (result.hasErrors()) {
	    return "/EditarDatos";
	}

	// Paso 2.- Set´s
	usuarioAuth.setNombre(usuario.getNombre());
	usuarioAuth.setApellido(usuario.getApellido());
	usuarioAuth.setEmail(usuario.getEmail());
	usuarioAuth.setTelefono(usuario.getTelefono());

	// Paso 3.- Persistencia
	usuarioService.guardar(usuarioAuth);

	// Paso 4.- Redireccion
	return "redirect:/MenuPrincipal";
    }

    @ModelAttribute("rutUser")
    public String auth() {
    	Usuario usuario = usuarioService.buscarPorRut(SecurityContextHolder.getContext().getAuthentication().getName());
    	return usuario.getNombre();
    }
}
