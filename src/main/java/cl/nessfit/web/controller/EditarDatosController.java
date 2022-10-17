package cl.nessfit.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import cl.nessfit.web.model.Rol;
import cl.nessfit.web.model.Usuario;
import cl.nessfit.web.service.CUsuarioService;
import cl.nessfit.web.utils.validacionUsuario;

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
    if(String.valueOf(usuario.getTelefono()).length() < 11 || String.valueOf(usuario.getTelefono()).length() > 16) {
    	result.rejectValue("telefono", null, "El teléfono móvil ingresado no es válido");
    }
    
    if(usuario.getNombre().length() < 3 ) {
    	result.rejectValue("nombre", null, "Los nombres o apellidos deben tener más de 2 caracteres");
    }
    if(usuario.getApellido().length() < 3) {
    	result.rejectValue("apellido", null, "Los nombres o apellidos deben tener más de 2 caracteres");
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
    
    @ModelAttribute("rolUser")
    public String rol() {
    return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst().get()
        .getAuthority();
    }
}
